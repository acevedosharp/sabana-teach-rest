package xyz.acevedosharp.sabanateachrest.model.course_progress

import com.fasterxml.jackson.annotation.JsonIgnore
import xyz.acevedosharp.sabanateachrest.model.course_enrollment.CourseEnrollment
import xyz.acevedosharp.sabanateachrest.model.lesson.Lesson
import javax.persistence.*

@Entity
@Table(name = "course_progress")
class CourseProgress(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?,

    @Column(nullable = false)
    val isCompleted: Boolean,

    @JsonIgnore
    @ManyToOne @JoinColumn(nullable = false)
    val courseEnrollment: CourseEnrollment,

    @ManyToOne @JoinColumn(nullable = false)
    val lesson: Lesson
)