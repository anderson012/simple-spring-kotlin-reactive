package br.com.andersoncarlos.reactivetest.rest

import br.com.andersoncarlos.reactivetest.pojo.Customer
import br.com.andersoncarlos.reactivetest.repository.CustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import java.time.Duration


@RestController
class CustomerRest {

    @Autowired
    private lateinit var repository: CustomerRepository;

    @GetMapping("/insert")
    fun insert(): String {
        var i = 1
        do {
            repository.saveAll(
                listOf(
                    Customer("Jack", "Bauer"),
                    Customer("Chloe", "O'Brian"),
                    Customer("Kim", "Bauer"),
                    Customer("David", "Palmer"),
                    Customer("Michelle", "Dessler")
                )
            )
                .blockLast(Duration.ofSeconds(10))
            println("Saved a list of customers")
            i++
        } while (i < 10)
        return "Saved a list of customers";
    }

    @GetMapping(path = ["/customers"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun get(): Flux<Customer?> {
        val flux: Flux<Customer?> = repository.findAll().delayElements(Duration.ofMillis(300));
        flux.subscribe {
            println("customer ${it?.firstName} found");
        }
        return flux;
    }
}