package com.goddoro.junction.presentation.driver

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.goddoro.junction.databinding.FragmentDriverBinding
import com.skt.Tmap.TMapTapi
import com.skt.Tmap.TMapView


/**
 * created By DORO 5/22/21
 */

class DriverFragment : Fragment() {

    private lateinit var mBinding : FragmentDriverBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentDriverBinding.inflate(inflater, container, false).also { mBinding = it}.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.lifecycleOwner = viewLifecycleOwner


    }

    companion object {

        fun newInstance() = DriverFragment()
    }
}