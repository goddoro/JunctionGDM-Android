package com.goddoro.junction.presentation.indriving

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.goddoro.junction.databinding.ActivityInDrivingBinding
import com.goddoro.junction.extensions.disposedBy
import com.goddoro.junction.extensions.rxRepeatTimer
import com.goddoro.junction.extensions.rxSingleTimer
import io.reactivex.disposables.CompositeDisposable
import org.koin.androidx.viewmodel.ext.android.viewModel

class InDrivingActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityInDrivingBinding


    private val compositeDisposable = CompositeDisposable()

    private val mViewModel: InDrivingViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityInDrivingBinding.inflate(LayoutInflater.from(this))

        mBinding.vm = mViewModel
        mBinding.lifecycleOwner = this

        setContentView(mBinding.root)

        setupRecyclerView()
        observeViewModel()
    }

    private fun observeViewModel() {

        mViewModel.apply {

            rxRepeatTimer(10) {

                if ( (curTime.value ?: 0) <= 0) {

                    mBinding.recyclerview.smoothScrollBy(0,mBinding.recyclerview.top)

                    if (position < textList.size) {
                        isRed.value = position == 2 || position == 5

                        inDrivingTextList.value =
                            listOf(textList[position]) + (inDrivingTextList.value ?: listOf())
                        position++



                        for (i in 0 until position) {
                            Log.d("ZXCV", i.toString())
                            inDrivingTextList.value!![i].position.set(i)
                        }
                    }
                    curTime.value = second[position-1]
                }


            }.disposedBy(compositeDisposable)
        }
    }

    private fun setupRecyclerView() {

        mBinding.recyclerview.apply {

            adapter = InDrivingTextAdapter(this@InDrivingActivity)
        }
    }
}