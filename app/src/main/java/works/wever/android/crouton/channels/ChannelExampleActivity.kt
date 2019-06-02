package works.wever.android.crouton.channels

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.launch
import works.wever.android.crouton.R

class ChannelExampleActivity : AppCompatActivity() {

    companion object {
        fun buildIntent(context: Context): Intent =
                Intent(context, ChannelExampleActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_channel_example)
    }
}

class ChannelViewModel : ViewModel() {

    private val job = Job()
    val channel: ConflatedBroadcastChannel<Int> = ConflatedBroadcastChannel()

    fun postNumber(number: Int) = viewModelScope.launch { channel.send(number) }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

}
