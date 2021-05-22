package com.goddoro.junction

import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.net.Uri.encode
import android.os.Bundle
import android.util.Base64.*
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.goddoro.junction.databinding.ActivityMainBinding
import com.goddoro.junction.extensions.observeOnce
import com.goddoro.junction.extensions.startActivity
import com.goddoro.junction.presentation.feed.FeedFragment
import com.goddoro.junction.presentation.map.MapFragment
import com.goddoro.junction.presentation.test.TestActivity
import com.goddoro.junction.util.AppPreference
import com.goddoro.junction.util.MainMenu
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import net.bytebuddy.implementation.bytecode.assign.Assigner.DEFAULT
import okhttp3.internal.http2.Huffman.encode
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.net.URLEncoder.encode
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.security.spec.PSSParameterSpec.DEFAULT
import java.util.*
import android.os.Build
import android.util.Base64
import com.goddoro.junction.presentation.description.DescriptionActivity
import com.goddoro.junction.presentation.indriving.InDrivingActivity
import com.goddoro.junction.presentation.voice.VoiceActivity
import com.kakao.sdk.common.util.KakaoCustomTabsClient
import com.kakao.sdk.navi.NaviClient
import com.kakao.sdk.navi.model.CoordType
import com.kakao.sdk.navi.model.Location
import com.kakao.sdk.navi.model.NaviOption


class MainActivity : AppCompatActivity() {

    private val mFragment1 : MapFragment = MapFragment.newInstance()
    private val mFragment2 : FeedFragment = FeedFragment.newInstance()
    private var mActiveFragment: Fragment = mFragment1

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

        initFirebaseSetting()
        initFragments()
        setupBottomNavigationView()

        startActivity(VoiceActivity::class)


    }

    private fun initFirebaseSetting() {

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



    private fun initFragments() {
        val fm = supportFragmentManager

        fm.beginTransaction().add(R.id.mMainContainer, mFragment2, "2").hide(mFragment2).commit()
        fm.beginTransaction().add(R.id.mMainContainer, mFragment1, "1").commit()
    }

    private fun changeFragment(menu: MainMenu) {

        val willShow = when (menu.idx) {
            0 -> mFragment1
            1 -> mFragment2
            else -> throw IllegalArgumentException()
        }
        supportFragmentManager.beginTransaction()
            .hide(mFragment1)
            .hide(mFragment2)

            .show(willShow).commit()
        mActiveFragment = willShow
    }

    private fun observeViewModel() {

        mViewModel.apply {

            clickTestActivity.observeOnce(this@MainActivity){

                startActivity(TestActivity::class)
            }
        }
    }

    private fun setupBottomNavigationView() {

        mBinding.mBottomNavigationView.setOnNavigationItemSelectedListener {

            val selectedMenu = MainMenu.parseIdToMainMenu(it.itemId)


            when (selectedMenu) {
                MainMenu.FEED -> {
                    changeFragment(selectedMenu)
                }
                MainMenu.EXPLORE -> {
                    changeFragment(selectedMenu)
                }

            }
            true
        }

        mBinding.mBottomNavigationView.setOnNavigationItemReselectedListener {

            // 이미 선택되어있는 탭을 다시 선택하면 목록 제일 위로 이동시킨다.
            // Broadcast 날림
            when (MainMenu.parseIdToMainMenu(it.itemId)) {

                MainMenu.FEED -> {
                    //Broadcast.videoListGoTop.onNext(Unit)
                }
                MainMenu.EXPLORE -> {
                    if (supportFragmentManager.backStackEntryCount > 0 ) {
                        supportFragmentManager.popBackStack()
                    } else {
                        //Broadcast.exploreListGoTop.onNext(Unit)
                    }
                }

            }
        }

    }
}