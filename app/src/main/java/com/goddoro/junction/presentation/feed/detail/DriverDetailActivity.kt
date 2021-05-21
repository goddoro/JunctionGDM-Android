package com.goddoro.junction.presentation.feed.detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.goddoro.junction.databinding.ActivityDriverDetailBinding
import com.goddoro.junction.network.model.Driver
import com.skt.Tmap.TMapTapi
import com.skt.Tmap.TMapView


class DriverDetailActivity : AppCompatActivity() {

    private lateinit var mBinding : ActivityDriverDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        mBinding = ActivityDriverDetailBinding.inflate(LayoutInflater.from(this))

        mBinding.lifecycleOwner = this

        setContentView(mBinding.root)

        initView()
    }


    private fun initView() {

        val tMapView = TMapView(this)
        tMapView.setSKTMapApiKey("l7xx4c348e1fea424ab0ae89146da5ee4431")

        // Initial Setting
        tMapView.setZoomLevel(17);
        tMapView.setIconVisibility(true);
        tMapView.setMapType(TMapView.MAPTYPE_STANDARD);
        tMapView.setLanguage(TMapView.LANGUAGE_KOREAN);
        tMapView.setHttpsMode(true);

        mBinding.tMapView.addView(tMapView)


        val tMapTapi = TMapTapi(this)
        tMapTapi.invokeNavigate("T타워", 126.984098f, 37.566385f, 0, true)


    }


    companion object {

        const val ARG_DRIVER = "ARG_DRIVER"

        fun newIntent(context: Activity, driver: Driver) : Intent {

            val intent = Intent(context, DriverDetailActivity::class.java)
            intent.putExtra(ARG_DRIVER, driver)
            return intent
        }
    }
}