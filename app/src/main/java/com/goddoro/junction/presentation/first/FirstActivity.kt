package com.goddoro.junction.presentation.first

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.app.ActivityOptionsCompat
import com.goddoro.junction.databinding.ActivityFirstBinding
import com.goddoro.junction.extensions.rxSingleTimer
import com.goddoro.junction.presentation.intro.IntroActivity

class FirstActivity : AppCompatActivity() {

    private lateinit var mBinding : ActivityFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityFirstBinding.inflate(LayoutInflater.from(this))

        mBinding.lifecycleOwner = this
        setContentView(mBinding.root)

        rxSingleTimer(1500){

            val intent = Intent ( this@FirstActivity, IntroActivity::class.java)
            startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this@FirstActivity,mBinding.imgLogo,"Intro").toBundle())
            finish()
        }
    }
}