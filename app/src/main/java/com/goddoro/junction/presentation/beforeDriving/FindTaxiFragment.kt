package com.goddoro.junction.presentation.beforeDriving

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.goddoro.junction.databinding.FragmentFindTaxiBinding
import com.goddoro.junction.extensions.disposedBy
import com.goddoro.junction.extensions.rxSingleTimer
import com.goddoro.junction.extensions.startActivity
import com.goddoro.junction.presentation.description.DescriptionActivity
import com.goddoro.junction.presentation.toTaxi.ToTaxiActivity
import com.goddoro.junction.util.setOnDebounceClickListener
import io.reactivex.disposables.CompositeDisposable
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.util.*

class FindTaxiFragment : Fragment(){

    private lateinit var mBinding : FragmentFindTaxiBinding

    private val mViewModel : BeforeDrivingViewModel by sharedViewModel()

    private lateinit var textToSpeech : TextToSpeech

    private val compositeDisposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentFindTaxiBinding.inflate(inflater,container,false).also { mBinding = it}.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.lifecycleOwner = viewLifecycleOwner



        initView()

    }

    private fun initView() {

        mBinding.btnRidingCheck.setOnDebounceClickListener {
            startActivity(ToTaxiActivity::class)
            requireActivity().finish()
        }

        rxSingleTimer(4000){
            textToSpeech = TextToSpeech(context!!) {
                if ( it != TextToSpeech.ERROR) {
                    textToSpeech.language =(Locale.ENGLISH)

                }
                textToSpeech.speak("Find a reserved taxi using D Handy",
                    TextToSpeech.QUEUE_FLUSH,null,null)

            }
        }.disposedBy(compositeDisposable)

        rxSingleTimer(10000){





            startActivity(DescriptionActivity::class)
            requireActivity().finish()
        }.disposedBy(compositeDisposable)
    }


    override fun onDestroy() {
        super.onDestroy()

        compositeDisposable.clear()
    }

    companion object {

        fun newInstance() = FindTaxiFragment()
    }
}