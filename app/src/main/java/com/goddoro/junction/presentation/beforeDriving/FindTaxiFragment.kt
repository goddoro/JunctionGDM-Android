package com.goddoro.junction.presentation.beforeDriving

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.goddoro.junction.databinding.FragmentFindTaxiBinding
import com.goddoro.junction.extensions.startActivity
import com.goddoro.junction.presentation.toTaxi.ToTaxiActivity
import com.goddoro.junction.util.setOnDebounceClickListener

class FindTaxiFragment : Fragment(){

    private lateinit var mBinding : FragmentFindTaxiBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentFindTaxiBinding.inflate(inflater,container,false).also { mBinding = it}.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.lifecycleOwner = viewLifecycleOwner

        initView()
    }

    private fun initView() {

        mBinding.btnRidingCheck.setOnDebounceClickListener {
            startActivity(ToTaxiActivity::class)
            requireActivity().finish()
        }
    }

    companion object {

        fun newInstance() = FindTaxiFragment()
    }
}