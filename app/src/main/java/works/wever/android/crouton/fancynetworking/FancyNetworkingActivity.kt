package works.wever.android.crouton.fancynetworking

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_fancy_networking.*
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.withContext
import works.wever.android.crouton.AndroidJob
import works.wever.android.crouton.GlideApp
import works.wever.android.crouton.R
import works.wever.android.crouton.networking.ApiProvider
import works.wever.android.crouton.networking.Comic
import works.wever.android.crouton.networking.ComicApi

class FancyNetworkingActivity : AppCompatActivity() {

    companion object {
        fun buildIntent(context: Context): Intent =
            Intent(context, FancyNetworkingActivity::class.java)
    }

    private val comicApi = ApiProvider.getComicApi()

    private val job = AndroidJob(lifecycle)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fancy_networking)

        fetchComic.setOnClickListener {
            getComic(comicNumberInput.text.toString().toInt())
        }
    }

    private fun getComic(id: Int) =
        launch(UI, parent = job) {
            val comic = withContext(CommonPool) {
                comicApi.getComic(id).await()
            }

            setComic(comic)
        }

    private fun setComic(comic: Comic) {
        GlideApp.with(this).load(comic.img).fitCenter().into(comicView)
    }
}
