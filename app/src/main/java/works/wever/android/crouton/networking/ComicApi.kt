package works.wever.android.crouton.networking

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


interface ComicApi {

    @GET("info.0.json")
    suspend fun getCurrentComic(): Comic

    @GET("{id}/info.0.json")
    suspend fun getComic(@Path("id") id: Int): Comic

}

object ApiProvider {
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl("http://xkcd.com/")
                .client(OkHttpClient.Builder().build())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
    }

    fun getComicApi(): ComicApi = getRetrofit().create(ComicApi::class.java)
}