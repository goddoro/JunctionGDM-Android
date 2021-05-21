package com.goddoro.junction.util.component

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.FragmentManager
import com.goddoro.junction.R
import com.goddoro.junction.databinding.DialogBottomSheetBinding
import com.goddoro.junction.util.ScreenUtil
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class BottomSheetDialog : BottomSheetDialogFragment() {

    private lateinit var mBinding : DialogBottomSheetBinding
    override fun getTheme(): Int = R.style.BottomSheetDialog

    private val screenUtil : ScreenUtil by inject()

    private val mViewModel : BottomSheetViewModel by viewModel()


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val bottomSheetDialog = BottomSheetDialog(activity!!, theme)

        bottomSheetDialog.behavior.peekHeight = screenUtil.screenHeight

        return bottomSheetDialog

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = DialogBottomSheetBinding.inflate(inflater, container, false)

        if (dialog != null && dialog?.window != null) {
            dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        }
        return mBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        mBinding.lifecycleOwner = viewLifecycleOwner
        mBinding.vm = mViewModel

    }

    companion object {
        fun show(
            fm: FragmentManager
        ) {
            val dialog = BottomSheetDialog()
            dialog.show(fm, dialog.tag)
        }
    }
}