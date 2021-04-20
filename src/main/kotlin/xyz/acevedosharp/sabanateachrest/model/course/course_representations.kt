package xyz.acevedosharp.sabanateachrest.model.course

import xyz.acevedosharp.sabanateachrest.model.lesson.PostLesson

class GetFlatCourse(
    val id: Int,
    val title: String,
    val duration: String,
    val isCertified: Boolean,
    val tags: String
)

class PostCourse(
    val title: String,
    val description: String,
    val duration: String,
    val teacher: String,
    val isCertified: Boolean,
    val tags: String,
    val introVideoUrl: String,
    val lessons: List<PostLesson>
)