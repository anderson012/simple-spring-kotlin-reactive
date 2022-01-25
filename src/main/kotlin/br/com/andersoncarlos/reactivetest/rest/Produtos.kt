package br.com.andersoncarlos.reactivetest.rest

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Produtos {

    @GetMapping("produtos")
    fun get(): String {
        return "lista de produtos";
    }
}