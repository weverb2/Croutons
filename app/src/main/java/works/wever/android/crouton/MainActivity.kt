package works.wever.android.crouton

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import works.wever.android.crouton.basic.SuperBasicActivity
import works.wever.android.crouton.channels.ChannelExampleActivity
import works.wever.android.crouton.networking.NetworkingActivity

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

        channelExample.setOnClickListener {
            startActivity(ChannelExampleActivity.buildIntent(this))
        }
    }
}
