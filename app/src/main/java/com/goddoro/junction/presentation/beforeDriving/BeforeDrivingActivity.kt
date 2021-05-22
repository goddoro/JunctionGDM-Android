package com.goddoro.junction.presentation.beforeDriving

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.goddoro.junction.databinding.ActivityBeforeDrivingBinding

class BeforeDrivingActivity : AppCompatActivity() {

    private lateinit var mBinding : ActivityBeforeDrivingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityBeforeDrivingBinding.inflate(LayoutInflater.from(this))

        mBinding.lifecycleOwner = this
        setContentView(mBinding.root)

        setupViewPager()

    }

    private fun setupViewPager(){

        mBinding.mViewPager.apply{

            adapter  = BeforeDrivingViewPager(supportFragmentManager,3)
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

}