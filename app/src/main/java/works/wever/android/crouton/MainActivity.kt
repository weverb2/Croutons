package works.wever.android.crouton

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import works.wever.android.crouton.basic.SuperBasicActivity
import works.wever.android.crouton.channels.ChannelExampleActivity
import works.wever.android.crouton.fancynetworking.FancyNetworkingActivity
import works.wever.android.crouton.networking.NetworkingActivity
import works.wever.android.crouton.timer.TimerActivity
import works.wever.android.crouton.viewmodelscope.ViewModelActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        superBasicExample.setOnClickListener {
            startActivity(SuperBasicActivity.buildIntent(this))
        }

        networkingExample.setOnClickListener {
            startActivity(NetworkingActivity.buildIntent(this))
        }

        fancyNetworkingExample.setOnClickListener {
            startActivity(FancyNetworkingActivity.buildIntent(this))
        }

        channelExample.setOnClickListener {
            startActivity(ChannelExampleActivity.buildIntent(this))
        }

        tickerExample.setOnClickListener {
            startActivity(TimerActivity.buildIntent(this))
        }

        viewModelExample.setOnClickListener {
            startActivity(ViewModelActivity.buildIntent(this))
        }
    }
}
