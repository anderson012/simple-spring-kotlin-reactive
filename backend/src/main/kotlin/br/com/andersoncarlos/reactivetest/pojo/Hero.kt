package br.com.andersoncarlos.reactivetest.pojo

import org.springframework.data.annotation.Id

data class Hero(var realName: String, var nickname: String, var superPower: String) {
    @Id
    var id: Long? = null
}