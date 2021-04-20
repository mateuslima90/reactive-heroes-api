package io.mkth.heroes.heroesapi.service

import io.mkth.heroes.heroesapi.exception.HeroesException
import io.mkth.heroes.heroesapi.model.Heroes
import io.mkth.heroes.heroesapi.repository.HeroesRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty

@Service
class HeroesService(private val heroesRepository: HeroesRepository) {

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

    fun updateHero(name: String, hero: Heroes): Mono<Heroes> {
        return getHeroesByUserId(name)
                .flatMap { heroesRepository.save(hero) }
    }

    fun deletebyID(id: String): Mono<Void> {
        return heroesRepository.findById(id)
                .flatMap { heroesRepository.deleteById(id) }
    }

    fun verifyHeroExists(userId: String): Mono<Heroes> {
        return getHeroesByUserId(userId)
                .switchIfEmpty { Mono.error(Exception()) }
    }
}