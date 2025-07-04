package com.pdm.viridis.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pdm.viridis.data.model.Recommendation
import kotlinx.serialization.json.Json

class Converters {

    @TypeConverter
    fun fromStringList(value: String): List<String> =
        if (value.isBlank()) emptyList() else value.split("|")

    @TypeConverter
    fun toStringList(list: List<String>): String =
        list.joinToString("|")

    @TypeConverter
    fun fromRecommendationList(value: String): List<Recommendation> {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun toRecommendationList(list: List<Recommendation>): String {
        return Json.encodeToString(list)
    }
}




