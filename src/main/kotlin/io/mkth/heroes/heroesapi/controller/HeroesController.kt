package io.mkth.heroes.heroesapi.controller

import org.springframework.http.HttpStatus
import reactor.core.publisher.Mono
import io.mkth.heroes.heroesapi.model.Heroes
import io.mkth.heroes.heroesapi.model.UpdateHeroes
import io.mkth.heroes.heroesapi.service.HeroesService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux

@RestController
class HeroesController(private val heroesService: HeroesService) {

    @GetMapping("/heroes")
    @ResponseStatus(HttpStatus.OK)
    fun getAllItems(): Flux<Heroes> {
        return heroesService.getAllHeroes()
    }

    @GetMapping("/heroes/{userId}")
    fun findByIdHero(@PathVariable("userId") userId: String): Mono<ResponseEntity<Heroes>> {
        return heroesService.getHeroesByUserId(userId)
                .map { item -> ResponseEntity<Heroes>(item, HttpStatus.OK) }
                .defaultIfEmpty(ResponseEntity<Heroes>(HttpStatus.NOT_FOUND))
    }

    @PostMapping("/heroes")
    @ResponseStatus(HttpStatus.CREATED)
    fun createHero(@RequestBody heroes: Heroes): Mono<Heroes> {
        return heroesService.createHero(heroes)
    }

    @PutMapping("/heroes")
    fun updateHeroes(@RequestBody updateHero: UpdateHeroes): Mono<Heroes> {
        return heroesService.updateHero(updateHero.userId, updateHero.hero)
    }

    @DeleteMapping("/heroes/{id}")
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    fun deleteByIDHero(@PathVariable id: String): Mono<Void> {
        return heroesService.deletebyID(id)
    }
}