package works.wever.android.crouton.channels

import android.content.Context
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_posting.view.*
import works.wever.android.crouton.R
import java.util.*

class PostingFragment : Fragment() {

    lateinit var channelViewModel: ChannelViewModel

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
        channelViewModel.postNumber(number)
    }

}
