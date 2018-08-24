package works.wever.android.crouton.channels

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_receiving.view.*
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.experimental.channels.consumeEach
import kotlinx.coroutines.experimental.launch
import works.wever.android.crouton.AndroidJob
import works.wever.android.crouton.R


class ReceivingFragment : Fragment() {

    private lateinit var channelViewModel: ChannelViewModel

    private val job = AndroidJob(lifecycle)

    lateinit var numberText: TextView

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        channelViewModel = ViewModelProviders.of(context as AppCompatActivity)
            .get(ChannelViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_receiving, container, false)

        numberText = view.number

        return view
    }

    override fun onResume() {
        super.onResume()
        launch(UI, parent = job) { receive(channelViewModel.channel) }
    }

    private suspend fun receive(numberChannel: ConflatedBroadcastChannel<Int>) {
        numberChannel.consumeEach { numberText.text = "$it" }
    }
}