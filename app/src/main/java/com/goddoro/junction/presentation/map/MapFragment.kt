package com.goddoro.junction.presentation.map

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.goddoro.junction.databinding.FragmentMapBinding
import com.goddoro.junction.extensions.observeOnce
import com.naver.maps.map.*
import com.naver.maps.map.util.FusedLocationSource
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

/**
 * created By DORO 5/21/21
 */

class MapFragment : Fragment() {

    val TAG = MapFragment::class.java.simpleName

    private lateinit var mBinding : FragmentMapBinding

    private val mViewModel : MapViewModel by viewModel()

    private lateinit var mapView: MapView

    private lateinit var naverMap : NaverMap

    private lateinit var locationSource : LocationSource
    private lateinit var uiSettings : UiSettings


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentMapBinding.inflate(inflater,container,false).also { mBinding = it}.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.lifecycleOwner = viewLifecycleOwner
        mBinding.vm = mViewModel



    }


    companion object {

        fun newInstance() = MapFragment()
    }

}