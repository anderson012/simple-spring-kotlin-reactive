package br.com.andersoncarlos.reactivetest.repository

import br.com.andersoncarlos.reactivetest.pojo.Hero
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux


interface HeroRepository : ReactiveCrudRepository<Hero?, Long?> {
    @Query("SELECT * FROM hero WHERE nickname = :nickname")
    fun findByNickname(nickname: String?): Flux<Hero?>?
}