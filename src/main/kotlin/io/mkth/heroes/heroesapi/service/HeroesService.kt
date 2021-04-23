package io.mkth.heroes.heroesapi.service

import io.mkth.heroes.heroesapi.exception.HeroesException
import io.mkth.heroes.heroesapi.model.Heroes
import io.mkth.heroes.heroesapi.model.HeroesDTO
import io.mkth.heroes.heroesapi.model.mapper.HeroesMapper
import io.mkth.heroes.heroesapi.repository.HeroesRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty

@Service
class HeroesService(private val heroesRepository: HeroesRepository,
                    private val heroesMapper: HeroesMapper) {

    fun getHeroesByUserId(userId: String) : Mono<Heroes> {
        return heroesRepository.findByUserId(userId)
    }

    fun getAllHeroes() : Flux<Heroes> {
        return heroesRepository.findAll()
    }

    fun createHero(hero: Heroes) : Mono<Heroes> {
        return heroesRepository.save(hero)
                .onErrorResume { Mono.error(HeroesException("Failed a creation")) }
    }

    fun updateHero(userId: String, updatehero: HeroesDTO): Mono<Heroes> {
        return getHeroesByUserId(userId)
                .map { mergeBetween(it, updatehero) }
                .flatMap { heroesRepository.save(it) }
                .switchIfEmpty { Mono.error(HeroesException("Hero not found")) }
                .onErrorResume { Mono.error(HeroesException("Failed update")) }
    }

    fun deletebyID(id: String): Mono<Void> {
        return heroesRepository.findById(id)
                .flatMap { heroesRepository.deleteById(id) }
    }

    private fun mergeBetween(savedHero: Heroes, updateHero: HeroesDTO) : Heroes {
        return Heroes(id = savedHero.id,
                userId = savedHero.userId,
                name = updateHero.name,
                universe = updateHero.universe,
                films = updateHero.films)
    }
}