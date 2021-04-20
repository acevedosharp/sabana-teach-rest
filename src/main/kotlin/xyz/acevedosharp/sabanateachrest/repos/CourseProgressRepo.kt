package xyz.acevedosharp.sabanateachrest.repos

import org.springframework.data.jpa.repository.JpaRepository
import xyz.acevedosharp.sabanateachrest.model.course_progress.CourseProgress

interface CourseProgressRepo: JpaRepository<CourseProgress, Int>