package xyz.acevedosharp.sabanateachrest.controller

import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import xyz.acevedosharp.sabanateachrest.model.user.SignInUser
import xyz.acevedosharp.sabanateachrest.model.user.SignUpUser
import xyz.acevedosharp.sabanateachrest.model.user.User
import xyz.acevedosharp.sabanateachrest.repos.UserRepo

@RestController
class UserController(
    private val userRepo: UserRepo,
    private val bCryptPasswordEncoder: BCryptPasswordEncoder
) {
    @PostMapping("/sign-up")
    fun signUp(@RequestBody user: SignUpUser): ResponseEntity<String> {
        val userToPersist = User(
            null,
            user.firstName,
            user.lastName,
            user.email,
            bCryptPasswordEncoder.encode(user.rawPassword),
            user.role
        )

        try {
            userRepo.save(userToPersist)
        } catch (e: Exception) {
            return ResponseEntity.badRequest().body("Error al registrarse.")
        }
        return ResponseEntity.ok().body("Registro exitoso.")
    }

    @PostMapping("/sign-in")
    fun signIn(@RequestBody user: SignInUser): ResponseEntity<String> {
        val persistedUser = userRepo.findByEmail(user.email)
            ?: return ResponseEntity.badRequest().body("Email incorrecto.")

        if (bCryptPasswordEncoder.matches(user.rawPassword, persistedUser.passwordHash)) {
            // TODO: create and give token
            return ResponseEntity.ok().body("Ingreso exitoso.")
        } else {
            return ResponseEntity.badRequest().body("Contraseña incorrecta.")
        }
    }
}