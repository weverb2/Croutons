package works.wever.android.crouton.viewmodelscope

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import kotlinx.android.synthetic.main.activity_fancy_networking.*
import kotlinx.coroutines.launch
import works.wever.android.crouton.GlideApp
import works.wever.android.crouton.R
import works.wever.android.crouton.networking.ApiProvider
import works.wever.android.crouton.networking.Comic

class ViewModelActivity : AppCompatActivity() {

    val comicViewModel: ComicViewModel by lazy {
        ViewModelProviders.of(this)
                .get(ComicViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fancy_networking)

        fetchComic.setOnClickListener {
            comicViewModel.getComic(comicNumberInput.text.toString())
        }

        comicViewModel.comicLiveData.observe(this, Observer {
            setComic(it)
        })
    }

    private fun setComic(comic: Comic) {
        GlideApp.with(this).load(comic.img).fitCenter().into(comicView)
    }

    companion object {
        fun buildIntent(context: Context): Intent =
                Intent(context, ViewModelActivity::class.java)
    }
}

class ComicViewModel : ViewModel() {

    private val comicApi = ApiProvider.getComicApi()

    val comicLiveData = MutableLiveData<Comic>()

    fun getComic(id: String) = viewModelScope.launch {
        val comic = comicApi.getComic(id)
        comicLiveData.postValue(comic)
    }
}