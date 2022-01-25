package br.com.andersoncarlos.reactivetest

import io.r2dbc.spi.ConnectionFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.core.io.ClassPathResource
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator


@SpringBootApplication
open class ReactivetestApplication

fun main(args: Array<String>) {
    runApplication<ReactivetestApplication>(*args)
}

@Bean
fun initializer(connectionFactory: ConnectionFactory): ConnectionFactoryInitializer? {
    val initializer = ConnectionFactoryInitializer()
    initializer.setConnectionFactory(connectionFactory)
    initializer.setDatabasePopulator(ResourceDatabasePopulator(ClassPathResource("schema.sql")))
    return initializer
}