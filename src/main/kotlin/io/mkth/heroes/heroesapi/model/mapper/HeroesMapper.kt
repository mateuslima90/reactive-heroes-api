package io.mkth.heroes.heroesapi.model.mapper

import io.mkth.heroes.heroesapi.model.Heroes
import io.mkth.heroes.heroesapi.model.HeroesDTO
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service

@Service
class HeroesMapper {

    fun toModel(heroesDTO: HeroesDTO): Heroes {
        return modelMapper.map(heroesDTO, Heroes::class.java)
    }

    fun toDTO(heroes: Heroes): HeroesDTO {
        return modelMapper.map(heroes, HeroesDTO::class.java)
    }

    companion object {
        private lateinit var heroesMapper: HeroesMapper
        private lateinit var modelMapper: ModelMapper
        val instance: HeroesMapper
            get() {
                if (heroesMapper == null) {
                    modelMapper = ModelMapper()
                    heroesMapper = HeroesMapper()
                }
                return heroesMapper
            }
    }
}
