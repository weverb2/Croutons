package works.wever.android.crouton.networking

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_networking.*
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.withContext
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

    private fun getComic() {
        launch(UI) {
            val comic = withContext(CommonPool) {
                comicApi.getCurrentComic().await()
            }

            GlideApp.with(this@NetworkingActivity).load(comic.img).fitCenter().into(comicView)
        }
    }
}

