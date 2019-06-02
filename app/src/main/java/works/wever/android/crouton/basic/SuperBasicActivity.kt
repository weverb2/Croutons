package works.wever.android.crouton.basic

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_super_basic.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                Thread.sleep(delayMs)
            }
            Toast.makeText(this@SuperBasicActivity, "hello!", Toast.LENGTH_SHORT).show()
        }
    }
}
