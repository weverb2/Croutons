package works.wever.android.crouton.channels

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.fragment_receiving.view.*
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import works.wever.android.crouton.R


class ReceivingFragment : Fragment() {

    private lateinit var channelViewModel: ChannelViewModel

    lateinit var numberText: TextView

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        channelViewModel = ViewModelProviders.of(requireActivity())
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
        lifecycleScope.launch { receive(channelViewModel.channel) }
    }

    private suspend fun receive(numberChannel: ConflatedBroadcastChannel<Int>) {
        numberChannel.consumeEach { numberText.text = "$it" }
    }
}