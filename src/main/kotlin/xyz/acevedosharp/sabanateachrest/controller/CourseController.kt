package xyz.acevedosharp.sabanateachrest.controller

import org.springframework.data.repository.findByIdOrNull
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import xyz.acevedosharp.sabanateachrest.model.course.Course
import xyz.acevedosharp.sabanateachrest.model.course.PostCourse
import xyz.acevedosharp.sabanateachrest.model.course_enrollment.CourseEnrollment
import xyz.acevedosharp.sabanateachrest.model.course_enrollment.PostCourseEnrollment
import xyz.acevedosharp.sabanateachrest.model.course_progress.CourseProgress
import xyz.acevedosharp.sabanateachrest.model.lesson.Lesson
import xyz.acevedosharp.sabanateachrest.repos.*
import kotlin.RuntimeException

@RestController
class CourseController(
    private val courseRepo: CourseRepo,
    private val lessonRepo: LessonRepo,
    private val userRepo: UserRepo,
    private val courseProgressRepo: CourseProgressRepo,
    private val courseEnrollmentRepo: CourseEnrollmentRepo
) {
    @GetMapping("/courses")
    fun allCourses(): List<Course> = courseRepo.findAll()

    @GetMapping("/courses/{courseId}")
    fun singleCourse(@PathVariable courseId: Int): Course {
        return courseRepo.findByIdOrNull(courseId) ?: throw RuntimeException("Course with id $courseId not found")
    }

    @Transactional
    @PostMapping("/courses")
    fun createCourse(@RequestBody course: PostCourse): Course {
        val persistedCourse = courseRepo.save(
            Course(
                null,
                course.title,
                course.description,
                course.duration,
                course.teacher,
                course.isCertified,
                course.tags,
                course.introVideoUrl,
                listOf()
            )
        )

        val lessonsToPersist = course.lessons.map {
            Lesson(
                null,
                it.title,
                it.description,
                it.transcript,
                it.videoUrl,
                persistedCourse
            )
        }

        lessonRepo.saveAll(lessonsToPersist)

        return courseRepo.findByIdOrNull(persistedCourse.id)!! // no way it can be null
    }

    @Transactional
    @PostMapping("/courses/enroll")
    fun enrollToCourse(@RequestBody postCourseEnrollment: PostCourseEnrollment): CourseEnrollment {
        val course = courseRepo.findByIdOrNull(postCourseEnrollment.courseId)
            ?: throw RuntimeException("Curso no encontrado.")

        // TODO: infer user from token
        val user = userRepo.findByIdOrNull(postCourseEnrollment.userId)
            ?: throw RuntimeException("Usuario no encontrado.")

        if (courseEnrollmentRepo.findAllByUser(user).map { it.course }.contains(course))
            throw RuntimeException("Ya estás inscrito a ese curso.")

        val persistedCourseEnrollment = courseEnrollmentRepo.save(
            CourseEnrollment(
                null,
                false,
                course,
                user,
                listOf()
            )
        )

        val courseProgressesToPersist = course.lessons.map {
            CourseProgress(
                null,
                false,
                persistedCourseEnrollment,
                it
            )
        }

        courseProgressRepo.saveAll(courseProgressesToPersist)

        return courseEnrollmentRepo.findByIdOrNull(persistedCourseEnrollment.id)!! // cannot be null
    }

    @GetMapping("/courses/user/{userId}")
    fun getEnrolledCourses(@PathVariable userId: Int): List<CourseEnrollment> {
        // TODO: Infer user from token
        val user = userRepo.findByIdOrNull(userId) ?: throw RuntimeException("No such user.")

        return courseEnrollmentRepo.findAllByUser(user)
    }

    @PostMapping("/courses/progress/{courseProgressId}")
    fun markCourseProgress(
        @PathVariable courseProgressId: Int
    ) {
        val courseProgress = courseProgressRepo.findByIdOrNull(courseProgressId)
            ?: throw RuntimeException("No such course progress.")

        courseProgressRepo.save(
            CourseProgress(
                courseProgress.id,
                true,
                courseProgress.courseEnrollment,
                courseProgress.lesson
            )
        )
    }

    @DeleteMapping("/courses")
    fun deleteEverything() {
        courseProgressRepo.deleteAll()
        courseEnrollmentRepo.deleteAll()
        lessonRepo.deleteAll()
        courseRepo.deleteAll()
    }
}