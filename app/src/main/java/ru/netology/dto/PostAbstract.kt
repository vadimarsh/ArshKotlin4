package ru.netology.dto

import java.util.*

open class PostAbstract(
    open val id: Long,
    open val author: String,
    open val content: String,
    open var created: Date,
    open var likes: Int = 0,
    open var comments: Int = 0,
    open var shares: Int = 0,
    open var likedByMe: Boolean = false,
    open var commentedByMe: Boolean = false,
    open var sharedByMe: Boolean = false
)