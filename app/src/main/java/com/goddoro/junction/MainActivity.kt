package com.goddoro.junction

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import com.goddoro.junction.databinding.ActivityMainBinding
import com.goddoro.junction.extensions.observeOnce
import com.goddoro.junction.extensions.startActivity
import com.goddoro.junction.presentation.test.TestActivity
import com.goddoro.junction.util.AppPreference
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName

    private lateinit var mBinding : ActivityMainBinding

    private val mViewModel : MainViewModel by viewModel()

    private val appPreference : AppPreference by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(LayoutInflater.from(this))

        mBinding.lifecycleOwner = this
        mBinding.vm = mViewModel

        setContentView(mBinding.root)

        observeViewModel()

        appPreference.isLogin = true

        Log.d(TAG, "WOW")

        if ( appPreference.isLogin  ) {

            FirebaseMessaging.getInstance().token
                .addOnCompleteListener(OnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        Log.w(TAG, "getInstanceId failed", task.exception)
                        return@OnCompleteListener
                    }

                    // Get new Instance ID token
                    val newFCMToken = task.result
                    Log.d(TAG, newFCMToken)
                    val savedFCMToken = appPreference.curFCMToken
                    if (newFCMToken != null) {
                        var uuid = appPreference.curDeviceUUID
                        if (uuid.isEmpty()) {
                            uuid = UUID.randomUUID().toString() // randon UUID 생성
                        }

                        if (savedFCMToken.isNotEmpty() && newFCMToken != savedFCMToken) {
                            // fcm token이 변경되었으면 preference update하고 서버도 update한다.
                            appPreference.curFCMToken = newFCMToken
                            Log.d(TAG, "Main IN devide")
                        } else {
                            appPreference.curFCMToken = newFCMToken
                        }
                    }
                })
        }
    }

    private fun observeViewModel() {

        mViewModel.apply {

            clickTestActivity.observeOnce(this@MainActivity){

                startActivity(TestActivity::class)
            }
        }
    }

}