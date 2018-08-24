package works.wever.android.crouton.channels

import android.arch.lifecycle.ViewModel
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.coroutines.experimental.channels.ConflatedBroadcastChannel
import works.wever.android.crouton.R

class ChannelExampleActivity : AppCompatActivity() {

    companion object {
        fun buildIntent(context: Context): Intent = Intent(context, ChannelExampleActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_channel_example)
    }
}

class ChannelViewModel : ViewModel() {

    val channel: ConflatedBroadcastChannel<Int> = ConflatedBroadcastChannel()

    suspend fun postNumber(number: Int) {
        channel.send(number)
    }

}
