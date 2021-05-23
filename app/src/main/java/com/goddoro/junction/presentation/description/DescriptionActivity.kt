package com.goddoro.junction.presentation.description

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.goddoro.junction.databinding.ActivityDescriptionBinding
import com.goddoro.junction.extensions.disposedBy
import com.goddoro.junction.extensions.rxRepeatTimer
import com.goddoro.junction.extensions.rxSingleTimer
import com.goddoro.junction.extensions.startActivity
import com.goddoro.junction.presentation.beforeDriving.FindTaxiFragment
import com.goddoro.junction.presentation.beforeDriving.LookingTaxiFragment
import com.goddoro.junction.presentation.beforeDriving.WillArriveFragment
import com.goddoro.junction.presentation.indriving.InDrivingActivity
import com.goddoro.junction.presentation.toTaxi.ToTaxiActivity
import io.reactivex.disposables.CompositeDisposable

class DescriptionActivity : AppCompatActivity() {

    var currentPage = 0


    private val compositeDisposable = CompositeDisposable()

    private lateinit var mBinding : ActivityDescriptionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityDescriptionBinding.inflate(LayoutInflater.from(this))

        mBinding.lifecycleOwner = this
        setContentView(mBinding.root)

        initView()
        setupViewPager()
    }

    private fun setupViewPager(){

        mBinding.mViewPager.apply {

            adapter = DescriptionViewPager(supportFragmentManager,2)
        }

        rxSingleTimer(5000){
            mBinding.mViewPager.currentItem = currentPage + 1
        }.disposedBy(compositeDisposable)

        rxSingleTimer(15000){
            startActivity(InDrivingActivity::class)
            finish()
        }
    }

    private fun initView() {


    }

    inner class DescriptionViewPager(fragmentManager: FragmentManager, pageCount: Int) :
        FragmentStateAdapter(fragmentManager, lifecycle) {
        private val _count: Int = pageCount


        override fun getItemCount(): Int {
            return _count
        }

        override fun createFragment(position: Int): Fragment {
            return when ( position ) {

                0 -> ReadyBriefFragment.newInstance()
                else -> BriefFragment.newInstance()


            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()

        compositeDisposable.clear()
    }
}