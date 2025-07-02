package com.pdm.viridis.data.model

data class Reminder(
    val id: String,
    val userId: String,
    val plantId: String,
    val gardenId: String?,
    val garden_name: String?,
    val common_name: String,
    val image: String?,
    val dueAt: String,
    val done: Boolean,
    val createdAt: String
)
