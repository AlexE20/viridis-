package com.pdm.viridis.data.model

data class User (
	val message:String,
	val user:UserDetails
) {
	companion object {
		fun empty() = User(
			message = "",
			user = UserDetails(
				badges = emptyList(),
				currentStreak = 0
			)
		)
	}
}

data class UserDetails(
	val badges: List<String>,
	val currentStreak: Int,
)