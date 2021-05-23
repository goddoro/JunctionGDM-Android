package com.goddoro.junction.presentation.indriving

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.goddoro.junction.databinding.ActivityInDrivingBinding
import com.goddoro.junction.extensions.disposedBy
import com.goddoro.junction.extensions.rxRepeatTimer
import com.goddoro.junction.extensions.rxSingleTimer
import com.goddoro.junction.extensions.startActivity
import com.goddoro.junction.presentation.description.DescriptionActivity
import com.goddoro.junction.presentation.end.EndActivity
import io.reactivex.disposables.CompositeDisposable
import org.koin.androidx.viewmodel.ext.android.viewModel

class InDrivingActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityInDrivingBinding


    private val timeDisposable = CompositeDisposable()
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


        rxRepeatTimer(1000){
            mViewModel.curTime.value = (mViewModel.curTime.value ?: 0) - 1
        }.disposedBy(timeDisposable)
    }

    private fun observeViewModel() {

        mViewModel.apply {

            rxRepeatTimer(10) {

                if ( (curTime.value ?: 0) <= 0) {



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

                    if ( position == textList.size) {

                        rxSingleTimer(3000){
                            timeDisposable.clear()
                            mBinding.txtSecond.text = 0.toString()
                        }

                        rxSingleTimer(6000) {

                            startActivity(EndActivity::class)
                            finish()
                        }.disposedBy(compositeDisposable)

                    }
                    mBinding.recyclerview.smoothScrollToPosition(0)


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

    override fun onDestroy() {
        super.onDestroy()

        compositeDisposable.clear()
    }
}