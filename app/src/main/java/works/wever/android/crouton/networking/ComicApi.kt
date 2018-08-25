package works.wever.android.crouton.networking

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.channels.ActorJob
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


interface ComicApi {

    @GET("info.0.json")
    fun getCurrentComic(): Deferred<Comic>

    @GET("{id}/info.0.json")
    fun getComic(@Path("id") id: Int): Deferred<Comic>

}

object ApiProvider {
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://xkcd.com/")
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    fun getComicApi(): ComicApi = getRetrofit().create(ComicApi::class.java)
}