package com.goddoro.junction.presentation.feed


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.goddoro.junction.databinding.FragmentFeedBinding
import com.goddoro.junction.extensions.disposedBy
import com.goddoro.junction.presentation.feed.detail.DriverDetailActivity
import com.kakao.sdk.common.util.KakaoCustomTabsClient
import com.kakao.sdk.navi.NaviClient
import com.kakao.sdk.navi.model.CoordType
import com.kakao.sdk.navi.model.Location
import com.kakao.sdk.navi.model.NaviOption
import com.skt.Tmap.TMapTapi
import com.skt.Tmap.TMapView
import io.reactivex.disposables.CompositeDisposable
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * created By DORO 5/21/21
 */

class FeedFragment : Fragment() {

    private val TAG = FeedFragment::class.java.simpleName
    private val compositeDisposable = CompositeDisposable()

    private lateinit var mBinding : FragmentFeedBinding
    private val mViewModel : FeedViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentFeedBinding.inflate(inflater,container,false).also { mBinding = it}.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.lifecycleOwner = viewLifecycleOwner
        mBinding.vm = mViewModel

        observeViewModel()
        setupRecyclerView()

        initView()
    }

    private fun setupRecyclerView() {


        mBinding.mRecyclerView.apply {


            adapter = DriverBindingAdapter().apply {

                clickEvent.subscribe{
                    val intent = DriverDetailActivity.newIntent(requireActivity(),it)
                    startActivity(intent)

                }.disposedBy(compositeDisposable)
            }
        }
    }

    private fun observeViewModel() {


    }


    private fun initView(){


        val uri =
            NaviClient.instance.navigateWebUrl(
                Location("카카오 판교오피스", "127.108640", "37.402111"),
                NaviOption(coordType = CoordType.WGS84)
            )

// CustomTabs로 길안내
        KakaoCustomTabsClient.openWithDefault(context!!, uri)

    }

    companion object {

        fun newInstance() = FeedFragment()
    }


}