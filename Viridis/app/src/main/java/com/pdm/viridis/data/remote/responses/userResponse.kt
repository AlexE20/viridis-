package com.pdm.viridis.data.remote.responses

data class UserResponse(
	val message: String,
	val user:UserInfo
)

data class UserInfo(
	val badges: List<String>,
	val currentStreak: Int,
)

data class UsernameResponse(
	val username: String
)

