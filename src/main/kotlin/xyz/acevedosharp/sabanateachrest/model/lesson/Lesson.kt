package xyz.acevedosharp.sabanateachrest.model.lesson

import com.fasterxml.jackson.annotation.JsonIgnore
import xyz.acevedosharp.sabanateachrest.model.course.Course
import javax.persistence.*

@Entity
@Table(name = "lesson")
class Lesson(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?,

    @Column(nullable = false)
    val title: String,

    @Column(nullable = false)
    val description: String,

    @Column(nullable = false)
    val transcript: String,

    @Column(nullable = false)
    val videoUrl: String,

    @JsonIgnore
    @ManyToOne @JoinColumn(nullable = false)
    val course: Course
)