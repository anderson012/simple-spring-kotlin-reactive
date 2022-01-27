package br.com.andersoncarlos.reactivetest.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
open class AppConfig {
    @Bean
    open fun corsConfigurer(): WebMvcConfigurer? {
        return object: WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry) {
                registry.addMapping("/*").allowedMethods("GET", "POST", "PUT", "DELETE")
            }
        }
    }
}