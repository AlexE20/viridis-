package com.pdm.viridis.Navigation

import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
data class CreationName(val gardenName: String)

@Serializable
data class CreationShade(val gardenName: String)

@Serializable
object LogIn

@Serializable
object SignUp

@Serializable
object Profile

@Serializable
object SignIn

@Serializable
object SearchPlant

@Serializable
object Notifications

@Serializable
object Notification

@Serializable
object Meeting

@Serializable
data class PlantContentNavigation(val plantId : String)

@Serializable
data class PlantDetailNavigation(val plantId : String)

@Serializable
data class GardenContentNavigation(val gardenId : Int, val gardenName : String)

