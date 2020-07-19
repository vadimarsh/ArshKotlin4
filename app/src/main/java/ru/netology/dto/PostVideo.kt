package ru.netology.dto

import java.util.*

data class PostVideo(
    override val id: Long,
    override val author: String,
    override val content: String,
    override var created: Date,
    val url: String,
    override var likes: Int = 0,
    override var comments: Int = 0,
    override var shares: Int = 0,
    override var likedByMe: Boolean = false,
    override var commentedByMe: Boolean = false,
    override var sharedByMe: Boolean = false
) : PostAbstract(
    id,
    author,
    content,
    created,
    likes,
    comments,
    shares,
    likedByMe,
    commentedByMe,
    sharedByMe
)