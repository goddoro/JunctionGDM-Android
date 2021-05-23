package com.goddoro.junction.presentation.toTaxi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Window
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.goddoro.junction.R
import com.goddoro.junction.databinding.ActivityToTaxiBinding
import com.goddoro.junction.extensions.disposedBy
import com.goddoro.junction.extensions.rxRepeatTimer
import com.goddoro.junction.extensions.rxSingleTimer
import com.goddoro.junction.extensions.startActivity
import com.goddoro.junction.presentation.description.DescriptionActivity
import com.goddoro.junction.presentation.indriving.InDrivingTextAdapter
import io.reactivex.disposables.CompositeDisposable
import org.koin.androidx.viewmodel.ext.android.viewModel

class ToTaxiActivity : AppCompatActivity() {

    private lateinit var mBinding : ActivityToTaxiBinding

    private val mViewModel : ToTaxiViewModel by viewModel()

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityToTaxiBinding.inflate(LayoutInflater.from(this))

        mBinding.lifecycleOwner = this
        mBinding.vm = mViewModel

        setContentView(mBinding.root)

        val window: Window = window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor =  (ContextCompat.getColor(this, R.color.background_1))

        setupRecyclerView()

        observeViewModel()
    }

    private fun observeViewModel() {

        mViewModel.apply {


            rxRepeatTimer(10) {

                if ( (curTime.value ?: 0) <= 0) {

                    mBinding.recyclerview.smoothScrollBy(0,mBinding.recyclerview.top)

                    if (position < textList.size) {
                        toTaxiText.value =
                            listOf(textList[position]) + (toTaxiText.value ?: listOf())
                        position++



                        for (i in 0 until position) {
                            Log.d("ZXCV", i.toString())
                            toTaxiText.value!![i].position.set(i)
                        }
                    }

                    if ( position == textList.size) {

                        rxSingleTimer(3000) {
                            curTime.value = 0
                            startActivity(DescriptionActivity::class)
                            finish()
                        }.disposedBy(compositeDisposable)

                    }
                    curTime.value = second[position-1]
                }


            }.disposedBy(compositeDisposable)
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        compositeDisposable.clear()
    }


    private fun setupRecyclerView() {

        mBinding.recyclerview.apply {

            adapter = InDrivingTextAdapter(this@ToTaxiActivity)
        }
    }
}