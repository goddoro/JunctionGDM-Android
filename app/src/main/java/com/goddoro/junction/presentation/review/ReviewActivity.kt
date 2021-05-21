package com.goddoro.junction.presentation.review

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.goddoro.junction.databinding.ActivityReviewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReviewActivity : AppCompatActivity() {

    private lateinit var mBinding : ActivityReviewBinding

    private val mViewModel : ReviewViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityReviewBinding.inflate(LayoutInflater.from(this))
        mBinding.vm = mViewModel
        mBinding.lifecycleOwner = this

        setContentView(mBinding.root)
    }
}