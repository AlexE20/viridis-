package com.pdm.viridis.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pdm.viridis.data.model.Recommendation

object Converters {
    private val gson = Gson()

    @TypeConverter
    fun listStringToJson(list: List<String>?): String =
        gson.toJson(list ?: emptyList<String>())

    @TypeConverter
    fun jsonToListString(json: String): List<String> =
        gson.fromJson(json, object : TypeToken<List<String>>() {}.type)

    @TypeConverter
    fun listRecToJson(list: List<Recommendation>?): String =
        gson.toJson(list ?: emptyList<Recommendation>())

    @TypeConverter
    fun jsonToListRec(json: String): List<Recommendation> =
        gson.fromJson(json, object : TypeToken<List<Recommendation>>() {}.type)
}



