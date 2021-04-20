package xyz.acevedosharp.sabanateachrest.repos

import org.springframework.data.jpa.repository.JpaRepository
import xyz.acevedosharp.sabanateachrest.model.lesson.Lesson

interface LessonRepo: JpaRepository<Lesson, Int>