package works.wever.android.crouton.basic

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_super_basic.*
import kotlinx.coroutines.launch
import works.wever.android.crouton.R

class SuperBasicActivity : FragmentActivity() {

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
        lifecycleScope.launch {
            Thread.sleep(delayMs)
        }
    }
}
