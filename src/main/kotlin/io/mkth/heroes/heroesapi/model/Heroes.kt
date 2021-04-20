package io.mkth.heroes.heroesapi.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Heroes(@Id
                  val id: String? = null,
                  @Indexed(unique = true)
                  val userId: String? = null,
                  val name: String? = null,
                  val universe: String? = null,
                  val films: Int? = null)