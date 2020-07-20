package ru.netology.dto

import java.util.*

interface Post {
    val id: Long
    val author: String
    val content: String
    var created: Date
    var likes: Int
    var comments: Int
    var shares: Int
    var likedByMe: Boolean
    var commentedByMe: Boolean
    var sharedByMe: Boolean
}