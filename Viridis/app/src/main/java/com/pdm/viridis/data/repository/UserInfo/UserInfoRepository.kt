package com.pdm.viridis.data.repository.UserInfo

import com.pdm.viridis.data.model.User

interface UserInfoRepository {
	suspend fun updateStreak(userId:String): User
	suspend fun updateBadges(userId:String):User
}