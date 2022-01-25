package br.com.andersoncarlos.reactivetest.socket

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Component
import org.springframework.web.reactive.socket.WebSocketHandler
import org.springframework.web.reactive.socket.WebSocketMessage
import org.springframework.web.reactive.socket.WebSocketSession
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.SynchronousSink
import java.time.Duration
import java.time.LocalDateTime
import java.util.UUID.randomUUID
import java.util.function.BiFunction

class Event(var eventId: String, var eventDt: String) {
    override fun toString(): String {
        return "[event=$eventId], eventDt=$eventDt"
    }
}

@Component
class CustomerSocketHandler: WebSocketHandler {
    private val json = ObjectMapper()

    private val eventFlux = Flux.generate { sink: SynchronousSink<String?> ->
        val event = Event(randomUUID().toString(), LocalDateTime.now().toString())
        try {
            sink.next(json.writeValueAsString(event))
        } catch (e: JsonProcessingException) {
            sink.error(e)
        }
    }
    private val intervalFlux = Flux.interval(Duration.ofMillis(1000L))
        .zipWith(eventFlux, BiFunction { _: Long?, event: Any? -> event })

    override fun handle(webSocketSession: WebSocketSession): Mono<Void> {
        println("handling");
        return webSocketSession.send(intervalFlux
            .map { payload -> webSocketSession.textMessage(payload!! as String) })
            .and(webSocketSession.receive()
                .map { obj: WebSocketMessage ->
                    obj.payloadAsText
                }
                .log())
    }
}