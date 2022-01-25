package br.com.andersoncarlos.reactivetest.socket

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.HandlerMapping
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping
import org.springframework.web.reactive.socket.WebSocketHandler

@Configuration
open class CustomerSocket {

    @Autowired
    private lateinit var webSocketHandler: WebSocketHandler

    @Bean
    public open fun webSocketHandlerMapping(): HandlerMapping? {
        println("Creating socket handler")
        val map: MutableMap<String, WebSocketHandler> = HashMap()
        map["/event-emitter"] = webSocketHandler
        val handlerMapping = SimpleUrlHandlerMapping()
        handlerMapping.order = 1
        handlerMapping.urlMap = map
        return handlerMapping
    }
}