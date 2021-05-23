package com.goddoro.junction.presentation.beforeDriving

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.observe
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.goddoro.junction.databinding.ActivityBeforeDrivingBinding
import com.goddoro.junction.extensions.disposedBy
import com.goddoro.junction.extensions.rxRepeatTimer
import com.goddoro.junction.extensions.startActivity
import com.goddoro.junction.presentation.toTaxi.ToTaxiActivity
import io.reactivex.disposables.CompositeDisposable
import org.koin.androidx.viewmodel.ext.android.viewModel

class BeforeDrivingActivity : AppCompatActivity() {

    var currentPage = 0

    private lateinit var mBinding : ActivityBeforeDrivingBinding

    private val mViewModel : BeforeDrivingViewModel by viewModel()

    private val compositeDisposable = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityBeforeDrivingBinding.inflate(LayoutInflater.from(this))

        mBinding.lifecycleOwner = this
        mBinding.vm = mViewModel

        setContentView(mBinding.root)

        setupViewPager()
        observeViewModel()

    }

    private fun setupViewPager(){

        mBinding.mViewPager.apply{

            adapter  = BeforeDrivingViewPager(supportFragmentManager,3)
        }

    }


    private fun observeViewModel () {

        mViewModel.apply {

            curPage.observe(this@BeforeDrivingActivity){
                mBinding.mViewPager.currentItem = it
            }
        }
    }





    inner class BeforeDrivingViewPager(fragmentManager: FragmentManager, pageCount: Int) :
        FragmentStateAdapter(fragmentManager, lifecycle) {
        private val _count: Int = pageCount


        override fun getItemCount(): Int {
            return _count
        }

        override fun createFragment(position: Int): Fragment {
            return when ( position ) {

                0 -> LookingTaxiFragment.newInstance()
                1 -> WillArriveFragment.newInstance()
                else -> FindTaxiFragment.newInstance()

            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()

        compositeDisposable.clear()
    }

}