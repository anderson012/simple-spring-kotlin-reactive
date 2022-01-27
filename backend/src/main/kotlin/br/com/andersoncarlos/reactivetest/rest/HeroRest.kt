package br.com.andersoncarlos.reactivetest.rest

import br.com.andersoncarlos.reactivetest.pojo.Hero
import br.com.andersoncarlos.reactivetest.repository.HeroRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import java.time.Duration

/**
 * @author Anderson Carlos
 * Application based on https://www.baeldung.com/java-reactive-systems#reactive_programming
 */
@RestController
class HeroRest {

    @Autowired
    private lateinit var repository: HeroRepository;

    @GetMapping("/insert")
    fun insert(): Flux<String> {
        var i = 1
        var list = ArrayList<Hero>()
        do {
            list.addAll(
                listOf(
                    Hero("Steve Rogers", "Captain America", "Super human skills"),
                    Hero("Black Widow", "Natasha Romanoff", "Acrobatics, espionage, master of seduction"),
                    Hero("Iron Man", "Tony Stark", "Genius level intellect, with armor: a lot of things"),
                    Hero("Hulk", "Bruce Banner", "unlimited super strength, regeneration, invulnerability"),
                    Hero("Thor", "Thor Odin son", "God Skills, Divine Strength, Self-Sustain, Electrokinesis")
                )
            )
            i++
        } while (i < 10)
        return repository
             .saveAll(list)
            .delayElements(
                Duration.ofMillis(300)
            ).map { "${it.nickname} inserted" }

    }

    @GetMapping(path = ["/heroes"])
    fun getAll(): Flux<Hero?> {
        val flux: Flux<Hero?> = repository.findAll().delayElements(Duration.ofMillis(300));
        flux.subscribe {
            println("Hero ${it?.nickname} found");
        }
        return flux;
    }
}