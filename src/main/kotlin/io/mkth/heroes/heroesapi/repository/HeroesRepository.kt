package io.mkth.heroes.heroesapi.repository

import io.mkth.heroes.heroesapi.model.Heroes
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Mono

interface HeroesRepository : ReactiveMongoRepository<Heroes, String> {

    fun findByUserId(userId: String) : Mono<Heroes>
}