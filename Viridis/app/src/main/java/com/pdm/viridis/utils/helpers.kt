package com.pdm.viridis.utils

import android.util.Base64
import org.json.JSONObject

internal fun extractUidFromToken(token: String): String? {
    return try {
        val parts = token.split(".")
        if (parts.size < 2) return null
        val payload = String(Base64.decode(parts[1], Base64.URL_SAFE or Base64.NO_PADDING or Base64.NO_WRAP))
        val json = JSONObject(payload)
        json.getString("user_id")
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}


fun decodeJwtPayload(jwt: String): JSONObject {
    val parts = jwt.split(".")
    if (parts.size != 3) throw IllegalArgumentException("Invalid JWT token")

    val payloadBytes = Base64.decode(parts[1], Base64.URL_SAFE or Base64.NO_PADDING or Base64.NO_WRAP)
    val payload = String(payloadBytes, Charsets.UTF_8)

    return JSONObject(payload)
}