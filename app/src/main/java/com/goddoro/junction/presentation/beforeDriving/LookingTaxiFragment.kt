package com.goddoro.junction.presentation.beforeDriving

import android.R
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.goddoro.junction.databinding.FragmentLookingTaxiBinding
import com.goddoro.junction.extensions.disposedBy
import com.goddoro.junction.extensions.rxSingleTimer
import io.reactivex.disposables.CompositeDisposable
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.util.*


class LookingTaxiFragment : Fragment(){

    private lateinit var mBinding : FragmentLookingTaxiBinding

    private val mViewModel : BeforeDrivingViewModel by sharedViewModel()

    private lateinit var textToSpeech : TextToSpeech

    private val compositeDisposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentLookingTaxiBinding.inflate(inflater, container, false).also { mBinding = it}.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.lifecycleOwner = viewLifecycleOwner

        val window: Window = activity!!.window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor =  (ContextCompat.getColor(activity!!, R.color.white))

        textToSpeech = TextToSpeech(context!!) {
            if ( it != TextToSpeech.ERROR) {
                textToSpeech.language =(Locale.ENGLISH)

            }
            textToSpeech.speak("Now, D Handy is looking for a taxi",TextToSpeech.QUEUE_FLUSH,null,null)

        }

        rxSingleTimer(4000){
            mViewModel.curPage.value = 1
        }.disposedBy(compositeDisposable)
    }

    override fun onDestroy() {
        super.onDestroy()

        compositeDisposable.clear()
    }

    companion object {

        fun newInstance() = LookingTaxiFragment()
    }
}