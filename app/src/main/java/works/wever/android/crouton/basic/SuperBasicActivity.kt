package works.wever.android.crouton.basic

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import androidx.core.widget.toast
import kotlinx.android.synthetic.main.activity_super_basic.*
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.withContext
import works.wever.android.crouton.R

class SuperBasicActivity : AppCompatActivity() {

    companion object {
        fun buildIntent(context: Context): Intent = Intent(context, SuperBasicActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_super_basic)

        sayHello.setOnClickListener {
            greet(1000)
        }
    }

    private fun greet(delayMs: Long) {
        launch(UI) {
            withContext(CommonPool) {
                Thread.sleep(delayMs)
            }

            toast("Hello!")
        }
    }
}
