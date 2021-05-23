package com.goddoro.junction.presentation.end

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.goddoro.junction.databinding.ActivityEndBinding
import com.goddoro.junction.extensions.rxSingleTimer

class EndActivity : AppCompatActivity() {

    private lateinit var mBinding : ActivityEndBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityEndBinding.inflate(LayoutInflater.from(this))

        mBinding.lifecycleOwner = this
        setContentView(mBinding.root)

        initView()
    }

    private fun initView() {


        rxSingleTimer(5000){
            finish()
        }
    }
}