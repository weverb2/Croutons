package works.wever.android.crouton.fancynetworking

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_fancy_networking.*
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import works.wever.android.crouton.GlideApp
import works.wever.android.crouton.R
import works.wever.android.crouton.networking.ApiProvider
import works.wever.android.crouton.networking.Comic

class FancyNetworkingActivity : AppCompatActivity() {

    companion object {
        fun buildIntent(context: Context): Intent =
                Intent(context, FancyNetworkingActivity::class.java)
    }

    private val comicApi = ApiProvider.getComicApi()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fancy_networking)

        fetchComic.setOnClickListener {
            getComic(comicNumberInput.text.toString())
        }
    }

    private fun getComic(id: String) = lifecycleScope.launch {
        val comic = comicApi.getComic(id)
        setComic(comic)
    }

    private fun getComicCallback(id: String) {
        comicApi.getComicCallback(id).enqueue(object: Callback<Comic> {
            override fun onFailure(call: Call<Comic>, t: Throwable) {
                // Handle Failure
            }

            override fun onResponse(call: Call<Comic>, response: Response<Comic>) {
                setComic(response.body()!!)
            }

        })
    }

    private fun setComic(comic: Comic) {
        GlideApp.with(this).load(comic.img).fitCenter().into(comicView)
    }
}
