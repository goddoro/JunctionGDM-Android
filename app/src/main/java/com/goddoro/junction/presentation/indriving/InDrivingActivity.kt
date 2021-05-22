package com.goddoro.junction.presentation.indriving

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.goddoro.junction.databinding.ActivityInDrivingBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class InDrivingActivity : AppCompatActivity() {

    private lateinit var mBinding : ActivityInDrivingBinding

    private val mViewModel : InDrivingViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityInDrivingBinding.inflate(LayoutInflater.from(this))

        mBinding.vm = mViewModel
        mBinding.lifecycleOwner = this

        setContentView(mBinding.root)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {

        mBinding.recyclerview.apply {

            adapter = InDrivingTextAdapter(this@InDrivingActivity)
        }
    }
}