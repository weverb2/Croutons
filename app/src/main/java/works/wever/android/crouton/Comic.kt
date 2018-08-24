package works.wever.android.crouton

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Comic(
        val month: String,
        val num: Int,
        val link: String,
        val year: String,
        val news: String,
        val safe_title: String,
        val transcript: String,
        val alt: String,
        val img: String,
        val title: String,
        val day: String
)