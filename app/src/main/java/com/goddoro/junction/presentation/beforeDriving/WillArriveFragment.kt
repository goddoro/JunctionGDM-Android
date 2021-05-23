package com.goddoro.junction.presentation.beforeDriving

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.goddoro.junction.databinding.FragmentWillArriveBinding
import com.goddoro.junction.extensions.disposedBy
import com.goddoro.junction.extensions.rxSingleTimer
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.util.*

class WillArriveFragment : Fragment() {

    private lateinit var textToSpeech : TextToSpeech

    private val mViewModel : BeforeDrivingViewModel by sharedViewModel()


    private lateinit var mBinding : FragmentWillArriveBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentWillArriveBinding.inflate(inflater,container,false).also { mBinding = it}.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.lifecycleOwner = viewLifecycleOwner

        textToSpeech = TextToSpeech(context!!) {
            if ( it != TextToSpeech.ERROR) {
                textToSpeech.language =(Locale.ENGLISH)

            }
            textToSpeech.speak("The taxi will arrive in 10 minutes",
                TextToSpeech.QUEUE_FLUSH,null,null)

        }


        rxSingleTimer(5000){


            mViewModel.curPage.value = 2


        }
    }

    companion object {

        fun newInstance() = WillArriveFragment()
    }
}