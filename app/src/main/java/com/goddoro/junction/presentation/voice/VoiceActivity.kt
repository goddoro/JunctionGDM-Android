package com.goddoro.junction.presentation.voice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import com.goddoro.junction.databinding.ActivityVoiceBinding
import com.goddoro.junction.extensions.disposedBy
import com.goddoro.junction.extensions.rxSingleTimer
import com.goddoro.junction.extensions.startActivity
import com.goddoro.junction.presentation.beforeDriving.BeforeDrivingActivity
import io.reactivex.disposables.CompositeDisposable
import java.util.*

class VoiceActivity : AppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()

    private lateinit var textToSpeech : TextToSpeech

    private lateinit var mBinding : ActivityVoiceBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityVoiceBinding.inflate(LayoutInflater.from(this))

        mBinding.lifecycleOwner = this
        setContentView(mBinding.root)

        textToSpeech = TextToSpeech(this) {
            if ( it != TextToSpeech.ERROR) {
                textToSpeech.language =(Locale.ENGLISH)

            }
            textToSpeech.speak("Say D Handy. Tell your destination",TextToSpeech.QUEUE_FLUSH,null,null)

        }

        initSetting()
        setupAnimation()
    }

    private fun initSetting() {

        rxSingleTimer(3000){
            mBinding.txtType.apply {
                text = ""
                setCharacterDelay(200)
                animateText("Seoul Station")

                rxSingleTimer(5000){
                    startActivity(BeforeDrivingActivity::class)
                    finish()
                }


            }
        }.disposedBy(compositeDisposable)

    }

    private fun setupAnimation () {

    }


}