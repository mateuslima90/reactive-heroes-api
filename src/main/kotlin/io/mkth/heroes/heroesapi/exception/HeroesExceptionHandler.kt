package io.mkth.heroes.heroesapi.exception

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.Exception


@ControllerAdvice
class HeroesExceptionHandler {

    private val mapper: ObjectMapper = ObjectMapper()

    @ExceptionHandler(HeroesException::class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    fun serverExceptionHandler(ex: Exception): ResponseEntity<HeroesUnprocessableEntity> {
        val excetion = HeroesUnprocessableEntity(msg = ex.message!!, code = "422")
        return ResponseEntity.unprocessableEntity().body(excetion)
    }
}
