package works.wever.android.crouton.fancynetworking

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_fancy_networking.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
            getComic(comicNumberInput.text.toString().toInt())
        }
    }

    private fun getComic(id: Int) = lifecycleScope.launch {
        val comic = withContext(Dispatchers.IO) { comicApi.getCurrentComic().execute().body()!! }
        setComic(comic)
    }

    private fun setComic(comic: Comic) {
        GlideApp.with(this).load(comic.img).fitCenter().into(comicView)
    }
}
