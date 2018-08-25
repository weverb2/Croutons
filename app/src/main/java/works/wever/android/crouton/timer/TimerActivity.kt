package works.wever.android.crouton.timer

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_timer.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.channels.ticker
import kotlinx.coroutines.experimental.launch
import works.wever.android.crouton.AndroidJob
import works.wever.android.crouton.R
import works.wever.android.crouton.fancynetworking.FancyNetworkingActivity
import java.util.concurrent.TimeUnit

class TimerActivity : AppCompatActivity() {

    companion object {
        fun buildIntent(context: Context): Intent =
            Intent(context, TimerActivity::class.java)
    }

    val job = AndroidJob(lifecycle)
    var timeLeft = 6000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        launch(UI, parent = job) {
            val channel = ticker(16, TimeUnit.MILLISECONDS)

            while (timeLeft > 0) {
                channel.receive()
                timeLeft -= 16
                setTime(timeLeft)
            }
        }
    }

    private fun setTime(timeLeft: Int) {
        timerDisplay.text = "$timeLeft"
    }
}
