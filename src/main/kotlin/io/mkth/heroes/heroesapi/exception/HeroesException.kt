package io.mkth.heroes.heroesapi.exception

data class HeroesException(override val message: String) : RuntimeException(message) {
}

data class HeroesUnprocessableEntity(val msg: String,
                                     val code: String)