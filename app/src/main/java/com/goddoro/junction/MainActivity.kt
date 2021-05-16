package com.goddoro.junction

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.goddoro.junction.databinding.ActivityMainBinding
import com.goddoro.junction.extensions.observeOnce
import com.goddoro.junction.extensions.startActivity
import com.goddoro.junction.presentation.test.TestActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding : ActivityMainBinding

    private val mViewModel : MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(LayoutInflater.from(this))

        mBinding.lifecycleOwner = this
        mBinding.vm = mViewModel

        setContentView(mBinding.root)

        observeViewModel()
    }

    private fun observeViewModel() {

        mViewModel.apply {

            clickTestActivity.observeOnce(this@MainActivity){

                startActivity(TestActivity::class)
            }
        }
    }

}