package com.goddoro.junction.presentation.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.goddoro.junction.R
import com.goddoro.junction.databinding.ActivityTestBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class TestActivity : AppCompatActivity() {

    private lateinit var mBinding : ActivityTestBinding

    private val mViewModel : TestViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityTestBinding.inflate(LayoutInflater.from(this))

        mBinding.vm = mViewModel
        mBinding.lifecycleOwner = this
        setContentView(R.layout.activity_test)
    }
}