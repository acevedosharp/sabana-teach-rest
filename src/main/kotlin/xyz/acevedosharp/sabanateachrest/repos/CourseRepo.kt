package xyz.acevedosharp.sabanateachrest.repos

import org.springframework.data.jpa.repository.JpaRepository
import xyz.acevedosharp.sabanateachrest.model.course.Course

interface CourseRepo: JpaRepository<Course, Int> {
}