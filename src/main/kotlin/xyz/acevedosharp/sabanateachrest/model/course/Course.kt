package xyz.acevedosharp.sabanateachrest.model.course

import xyz.acevedosharp.sabanateachrest.model.lesson.Lesson
import javax.persistence.*

@Entity
@Table(name = "course")
class Course(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?,

    @Column(nullable = false)
    val title: String,

    @Column(nullable = false)
    val description: String,

    @Column(nullable = false)
    val duration: String,

    @Column(nullable = false)
    val teacher: String,

    @Column(nullable = false)
    val isCertified: Boolean,

    @Column(nullable = false)
    val tags: String,

    @Column(nullable = false)
    val introVideoUrl: String,

    @OneToMany(mappedBy="course")
    val lessons: List<Lesson>
)