package com.example.viridis.utils

import android.util.Base64
import org.json.JSONObject

internal fun extractUidFromToken(token: String): String? {
    return try {
        val parts = token.split(".")
        if (parts.size < 2) return null
        val payload = String(Base64.decode(parts[1], Base64.URL_SAFE or Base64.NO_PADDING or Base64.NO_WRAP))
        val json = JSONObject(payload)
        json.getString("user_id") // or try "uid" if that fails
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}
