package works.wever.android.crouton.timer

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_timer.*
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.launch
import works.wever.android.crouton.R

class TimerActivity : AppCompatActivity() {

    companion object {
        fun buildIntent(context: Context): Intent =
                Intent(context, TimerActivity::class.java)
    }

    var timeLeft = 60000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)


        lifecycleScope.launch {
            val channel = ticker(16)

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
