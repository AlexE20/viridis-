package com.pdm.viridis.data.remote.user

import com.pdm.viridis.data.remote.responses.UserResponse
import retrofit2.http.PATCH
import retrofit2.http.Path

interface UserService {
	@PATCH("api/users/badges/{userId}")
	suspend fun updateUserBadges(
		@Path("userId") userId: String
	): UserResponse
	
	@PATCH("api/users/currentStreak/{userId}")
	suspend fun updateUserStreak(
		@Path("userId") userId: String
	): UserResponse
}