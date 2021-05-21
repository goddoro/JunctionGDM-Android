package com.goddoro.junction.presentation.feed.detail

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import com.goddoro.junction.databinding.ActivityDriverDetailBinding
import com.goddoro.junction.network.model.Driver

class DriverDetailActivity : AppCompatActivity() {

    private lateinit var mBinding : ActivityDriverDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        mBinding = ActivityDriverDetailBinding.inflate(LayoutInflater.from(this))

        mBinding.lifecycleOwner = this

        setContentView(mBinding.root)
    }


    companion object {

        const val ARG_DRIVER = "ARG_DRIVER"

        fun newIntent (context : Activity, driver : Driver) : Intent {

            val intent = Intent ( context, DriverDetailActivity::class.java)
            intent.putExtra(ARG_DRIVER, driver)
            return intent
        }
    }
}