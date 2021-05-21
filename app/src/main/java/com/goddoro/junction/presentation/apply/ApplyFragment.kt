package com.goddoro.junction.presentation.apply

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.goddoro.junction.databinding.FragmentApplyBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * created By DORO 5/22/21
 */

class ApplyFragment : Fragment(){

    private lateinit var mBinding : FragmentApplyBinding

    private val mViewModel : ApplyViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentApplyBinding.inflate(inflater,container,false).also { mBinding = it}.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.vm = mViewModel
        mBinding.lifecycleOwner = viewLifecycleOwner
    }
    companion object {

        fun newInstance() = ApplyFragment()
    }
}