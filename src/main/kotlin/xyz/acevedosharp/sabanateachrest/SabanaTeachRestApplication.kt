package xyz.acevedosharp.sabanateachrest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@SpringBootApplication
class SabanaTeachRestApplication {
    @Bean fun bCrypPasswordEncoder() = BCryptPasswordEncoder()
}

fun main(args: Array<String>) {
    runApplication<SabanaTeachRestApplication>(*args)
}


@Configuration
class WebConfiguration : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**").allowedMethods("*")
    }
}