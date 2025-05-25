package com.example.viridis.Navigation

import kotlinx.serialization.Serializable

@Serializable

sealed class Navigation(val route:String){
    @Serializable
    object Meeting: Navigation("meeting")

    @Serializable
    object Notification: Navigation("notification")

}
