package com.pdm.viridis.data.repository.UserInfo

import com.pdm.viridis.data.model.User
import com.pdm.viridis.data.remote.responses.UsernameResponse

interface UserInfoRepository {
	suspend fun updateStreak(userId:String): User
	suspend fun updateBadges(userId:String):User
	suspend fun getUsername(userId: String): UsernameResponse
}