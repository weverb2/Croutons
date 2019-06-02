package works.wever.android.crouton.networking

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_networking.*
import kotlinx.coroutines.launch
import works.wever.android.crouton.GlideApp
import works.wever.android.crouton.R
import works.wever.android.crouton.networking.ApiProvider.getComicApi

class NetworkingActivity : AppCompatActivity() {

    companion object {
        fun buildIntent(context: Context): Intent = Intent(context, NetworkingActivity::class.java)
    }

    private lateinit var comicApi: ComicApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_networking)

        comicApi = getComicApi()

        fetchComic.setOnClickListener {
            getComic()
        }
    }

    private fun getComic() = lifecycleScope.launch {
        val comic = comicApi.getCurrentComic().await()
        setComic(comic)
    }

    private fun setComic(comic: Comic) {
        GlideApp.with(this).load(comic.img).fitCenter().into(comicView)
    }
}

