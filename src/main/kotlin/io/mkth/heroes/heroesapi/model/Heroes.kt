package io.mkth.heroes.heroesapi.model

import org.jetbrains.annotations.NotNull
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document
data class Heroes(@Id
                  val id: String? = null,
                  @Indexed(unique = true)
                  val userId: String? = null,
                  val name: String? = null,
                  val universe: String? = null,
                  val films: Int? = null)

data class HeroesDTO(val id: String? = null,
                     val userId: String? = null,
                     val name: String? = null,
                     val universe: String? = null,
                     val films: Int? = null)

data class UpdateHeroes(@field:NotNull val userId: String,
                        @field:NotNull val hero: HeroesDTO)