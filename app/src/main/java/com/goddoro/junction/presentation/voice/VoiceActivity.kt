package com.goddoro.junction.presentation.voice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.goddoro.junction.databinding.ActivityVoiceBinding
import com.goddoro.junction.extensions.rxSingleTimer

class VoiceActivity : AppCompatActivity() {

    private lateinit var mBinding : ActivityVoiceBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityVoiceBinding.inflate(LayoutInflater.from(this))

        mBinding.lifecycleOwner = this
        setContentView(mBinding.root)

        initTextView()
        setupAnimation()
    }

    private fun initTextView() {


    }

    private fun setupAnimation () {

    }


}