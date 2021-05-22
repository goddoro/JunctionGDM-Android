package com.goddoro.junction.presentation.map

import android.Manifest
import android.content.pm.PackageManager
import android.icu.util.TimeUnit
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.goddoro.junction.databinding.FragmentMapBinding
import com.goddoro.junction.extensions.addSchedulers
import com.goddoro.junction.extensions.disposedBy
import com.goddoro.junction.extensions.observeOnce
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * created By DORO 5/21/21
 */

class MapFragment : Fragment(), OnMapReadyCallback {

    private val cameraChanged: BehaviorSubject<Pair<Double, Double>> = BehaviorSubject.create()

    private val compositeDisposable = CompositeDisposable()

    val TAG = MapFragment::class.java.simpleName

    private lateinit var mBinding : FragmentMapBinding

    private val mViewModel : MapViewModel by viewModel()

    private lateinit var mapView: MapView

    private lateinit var naverMap : NaverMap

    private lateinit var locationSource: FusedLocationSource
    private lateinit var uiSettings : UiSettings


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentMapBinding.inflate(inflater, container, false).also { mBinding = it}.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.lifecycleOwner = viewLifecycleOwner
        mBinding.vm = mViewModel

        mapView = mBinding.mapView
        mapView.onCreate(savedInstanceState)

        mBinding.mapView.getMapAsync(this)


        observeViewModel()
        listenChangeCamera()
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

            cameraChanged.onNext(Pair(cameraPosition.target.longitude, cameraPosition.target.latitude))


        }


    }

    private fun listenChangeCamera() {
        cameraChanged
            .debounce(100L, java.util.concurrent.TimeUnit.MILLISECONDS)
            .addSchedulers()
            .subscribe {
                mViewModel.onCameraChange(it.first, it.second)
            }.disposedBy(compositeDisposable)

    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>,
                                            grantResults: IntArray) {

        Log.d(TAG, "onRequestPermission")
        if (locationSource.onRequestPermissionsResult(requestCode, permissions,
                grantResults)) {
            if (!locationSource.isActivated) { // 권한 거부됨
                naverMap.locationTrackingMode = LocationTrackingMode.None
            }
            return
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }



    override fun onMapReady(p0: NaverMap) {
        Log.d(TAG,"셋팅www했어..")
        naverMap = p0


        naverMap.locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)
        naverMap.locationTrackingMode = LocationTrackingMode.Face
        Log.d(TAG,"셋팅했어..")
        initMap( )

        // Request camera permissions
        if (allPermissionsGranted()) {
            //startCamera(currentCameraSelector)
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(), REQUIRED_PERMISSIONS, LOCATION_PERMISSION_REQUEST_CODE)
        }
//
        val marker = Marker()
        marker.position = (LatLng(37.5670135, 126.9783740))
        marker.map =(naverMap)
//
//        val uiSettings = naverMap.uiSettings
//
//        uiSettings.isLocationButtonEnabled = true

    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            context!!, it) == PackageManager.PERMISSION_GRANTED
    }







    companion object {

        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000

        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)

        fun newInstance() = MapFragment()
    }


}