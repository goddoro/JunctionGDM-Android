package com.goddoro.junction.presentation.description


import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.goddoro.junction.databinding.FragmentReadyBriefBinding
import java.util.*

class ReadyBriefFragment : Fragment(){

    private lateinit var textToSpeech: TextToSpeech

    private lateinit var mBinding : FragmentReadyBriefBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentReadyBriefBinding.inflate(inflater,container,false).also { mBinding = it}.root
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.lifecycleOwner = viewLifecycleOwner


        textToSpeech = TextToSpeech(context!!) {
            if ( it != TextToSpeech.ERROR) {
                textToSpeech.language =(Locale.ENGLISH)

            }
            textToSpeech.speak("Get a briefing on the route using D Handy",
                TextToSpeech.QUEUE_FLUSH,null,null)

        }
    }

    companion object {

        fun newInstance() = ReadyBriefFragment()
    }
}