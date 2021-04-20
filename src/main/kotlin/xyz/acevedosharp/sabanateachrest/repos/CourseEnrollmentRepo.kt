package xyz.acevedosharp.sabanateachrest.repos

import org.springframework.data.jpa.repository.JpaRepository
import xyz.acevedosharp.sabanateachrest.model.course_enrollment.CourseEnrollment
import xyz.acevedosharp.sabanateachrest.model.user.User

interface CourseEnrollmentRepo: JpaRepository<CourseEnrollment, Int> {
    fun findAllByUser(user: User): List<CourseEnrollment>
}