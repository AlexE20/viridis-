package com.pdm.viridis.data.remote


import com.pdm.viridis.BuildConfig
import com.pdm.viridis.data.remote.gardens.GardenService
import com.pdm.viridis.data.remote.notifications.NotificationService
import com.pdm.viridis.data.remote.plants.PlantService
import com.pdm.viridis.data.remote.userPlants.UserPlantService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = BuildConfig.API_URL

    private val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }).build()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val authService: AuthService by lazy {
        retrofit.create(AuthService::class.java)
    }

    val gardenService: GardenService by lazy{
        retrofit.create(GardenService::class.java)
    }

    val userPlantService: UserPlantService by lazy {
        retrofit.create(UserPlantService::class.java)
    }

    val plantService: PlantService by lazy {
        retrofit.create(PlantService::class.java)
    }

    val notificationService: NotificationService by lazy {
        retrofit.create(NotificationService::class.java)
    }


}