package br.com.andersoncarlos.reactivetest.pojo

import org.springframework.data.annotation.Id

class Customer(var firstName: String, var lastName: String) {
    @Id
    var id: Long? = null

    override fun toString(): String {
        return "Customer[id=$id, firstName='$firstName', lastName='$lastName']";
    }
}