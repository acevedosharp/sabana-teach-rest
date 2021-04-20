package xyz.acevedosharp.sabanateachrest.model.course_enrollment

import xyz.acevedosharp.sabanateachrest.model.course.Course
import xyz.acevedosharp.sabanateachrest.model.course_progress.CourseProgress
import xyz.acevedosharp.sabanateachrest.model.user.User
import javax.persistence.*

@Entity
@Table(name = "course_enrollment")
class CourseEnrollment (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?,

    @Column(nullable = false)
    val isCompleted: Boolean,

    @ManyToOne @JoinColumn(nullable = false)
    val course: Course,

    @ManyToOne @JoinColumn(nullable = false)
    val user: User,

    @OneToMany(mappedBy="courseEnrollment")
    val progresses: List<CourseProgress>
)
