package xyz.acevedosharp.sabanateachrest.repos

import org.springframework.data.jpa.repository.JpaRepository
import xyz.acevedosharp.sabanateachrest.model.user.User

interface UserRepo: JpaRepository<User, Int> {
    fun findByEmail(str: String): User?
}