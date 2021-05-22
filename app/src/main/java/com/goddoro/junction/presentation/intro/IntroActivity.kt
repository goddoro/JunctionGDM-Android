package com.goddoro.junction.presentation.intro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import com.goddoro.junction.MainActivity
import com.goddoro.junction.databinding.ActivityIntroBinding
import com.goddoro.junction.extensions.rxSingleTimer
import com.goddoro.junction.extensions.startActivity
import com.goddoro.junction.util.AppPreference
import com.goddoro.junction.util.setOnDebounceClickListener
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class IntroActivity : AppCompatActivity() {

    private lateinit var mBinding : ActivityIntroBinding

    private val mViewModel : IntroViewModel by viewModel()

    private val appPreference : AppPreference by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityIntroBinding.inflate(LayoutInflater.from(this))
        mBinding.lifecycleOwner = this
        mBinding.vm = mViewModel

        setContentView(mBinding.root)

        initView()
        appPreference.isLogin = false
    }

    private fun initView() {

        //mBinding.animTaxi.playAnimation()


        rxSingleTimer(2000){

            if ( appPreference.isLogin ) startActivity(MainActivity::class)
            else {
                mBinding.animGettingStartedButton.visibility = VISIBLE
                //mBinding.animGettingStartedButton.playAnimation()


            }
        }

        mBinding.animGettingStartedButton.setOnDebounceClickListener {
            startActivity(MainActivity::class)
        }
    }
}