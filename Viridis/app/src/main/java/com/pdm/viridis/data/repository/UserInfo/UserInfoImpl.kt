package com.pdm.viridis.data.repository.UserInfo

import com.pdm.viridis.data.model.Garden
import com.pdm.viridis.data.model.User
import com.pdm.viridis.data.model.UserDetails
import com.pdm.viridis.data.remote.responses.UsernameResponse
import com.pdm.viridis.data.remote.user.UserService

class UserInfoImpl(
	private val userService: UserService
) : UserInfoRepository {
	
	
	override suspend fun updateStreak(userId: String): User {
		return try {
			val response = userService.updateUserStreak(userId)
			User(
				message = response.message,
				user = UserDetails(
					badges = response.user.badges,
					currentStreak = response.user.currentStreak
				)
			)
		} catch (e: Exception) {
			e.printStackTrace()
			User.empty()
		}
	}
	
	
	override suspend fun updateBadges(userId: String): User = try {
        val response = userService.updateUserBadges(userId)
        User(
            message = response.message,
            user = UserDetails(
                badges = response.user.badges,
                currentStreak = response.user.currentStreak
            )
        )
    } catch (e: Exception) {
        e.printStackTrace()
        User.empty()
    }

	override suspend fun getUsername(userId: String): UsernameResponse {
		return try {
			userService.getUsername(userId)
		} catch (e: Exception) {
			e.printStackTrace()
			UsernameResponse(username = "Unknown")
		}
	}
}