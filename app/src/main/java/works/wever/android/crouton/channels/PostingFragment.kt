package works.wever.android.crouton.channels

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_posting.view.*
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.launch
import works.wever.android.crouton.AndroidJob
import works.wever.android.crouton.R
import java.util.*

class PostingFragment : Fragment() {

    lateinit var channelViewModel: ChannelViewModel

    private val job = AndroidJob(lifecycle)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        channelViewModel = ViewModelProviders.of(context as AppCompatActivity)
            .get(ChannelViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_posting, container, false)

        view.sendButton.setOnClickListener {
            sendNumber(Random().nextInt())
        }

        return view
    }

    private fun sendNumber(number: Int) {
        launch(CommonPool, parent = job) {
            channelViewModel.postNumber(number)
        }
    }

}
