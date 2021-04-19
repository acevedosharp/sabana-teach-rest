package xyz.acevedosharp.sabanateachrest.model.user

class SignUpUser (
    val firstName: String,
    val lastName: String,
    val email: String,
    val rawPassword: String,
    val role: UserRole
)

class SignInUser (
    val email: String,
    val rawPassword: String,
)