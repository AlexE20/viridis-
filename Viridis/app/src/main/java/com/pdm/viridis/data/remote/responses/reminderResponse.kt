data class ReminderResponse(
    val id: String,
    val userId: String,
    val plantId: String,
    val gardenId: String?,
    val garden_name: String?,
    val common_name: String,
    val image: Image,
    val dueAt: TimestampResponse,
    val done: Boolean,
    val createdAt: TimestampResponse
)

data class Image(
    val small_url: String?,
    val thumbnail: String?,
    val license: Int?,
    val original_url: String?,
    val medium_url: String?,
    val regular_url: String?,
    val license_url: String?,
    val license_name: String?
)

data class TimestampResponse(
    val _seconds: Long,
    val _nanoseconds: Int
)
