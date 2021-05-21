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

class MapFragment : Fragment(), OnMapReadyCallback {

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

        mapView = mBinding.mapView
        mapView.onCreate(savedInstanceState)


        observeViewModel()
    }

    private fun observeViewModel() {

        mViewModel.apply {

            clickMyLocation.observeOnce(viewLifecycleOwner){

            }
        }
    }


    override fun onStart() {
        super.onStart()

        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()

        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()

        mapView.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        mapView.onSaveInstanceState(outState)
    }

    override fun onStop() {
        super.onStop()

        mapView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()

        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()

        mapView.onLowMemory()
    }

    private fun initMap() {

        naverMap.addOnCameraChangeListener { reason: Int, animated: Boolean ->
            val cameraPosition = naverMap.cameraPosition
            Log.d(
                "NaverMap",
                "카메라 변경 - 대상 지점 위도: " + cameraPosition.target.latitude.toString() + ", " +
                        "대상 지점 경도: " + cameraPosition.target.longitude.toString() + ", " +
                        "줌 레벨: " + cameraPosition.zoom.toString() + ", " +
                        "기울임 각도: " + cameraPosition.tilt.toString() + ", " +
                        "베어링 각도: " + cameraPosition.bearing
            )

//            cameraChanged.onNext(
//                Pair(
//                    cameraPosition.target.longitude,
//                    cameraPosition.target.latitude
//                )
//            )


        }

    }

//    private fun listenChangeCamera() {
//        cameraChanged
//            .debounce(100L, TimeUnit.MILLISECONDS)
//            .addSchedulers()
//            .subscribe {
//                mViewModel.onCameraChange(it.first, it.second)
//            }.disposedBy(compositeDisposable)
//
//    }


    override fun onMapReady(p0: NaverMap) {

        naverMap = p0
        initMap( )

    }






    companion object {


        fun newInstance() = MapFragment()
    }


}