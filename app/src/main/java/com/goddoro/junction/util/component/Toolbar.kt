package com.goddoro.junction.util.component

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.databinding.BindingAdapter
import com.goddoro.junction.R
import com.goddoro.junction.databinding.ToolBarBinding
import com.goddoro.junction.util.setOnDebounceClickListener
import com.goddoro.junction.util.setVisibility

class Toolbar @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    ConstraintLayout(context, attrs) {
    private val TAG = Toolbar::class.java.simpleName

    private val mBinding: ToolBarBinding

    private var title: String = ""
    private var leftIconDrawable: Drawable? = null
    private var rightIconDrawable: Drawable? = null
    private var rightText: String = ""


    init {
        // Clear Background
        this.setBackgroundResource(0)

        val inflater = LayoutInflater.from(context)

        mBinding = ToolBarBinding.inflate(inflater, this, true)

        enableLeftIcon(false)
        enableRightIcon(false)

        context.theme.obtainStyledAttributes(attrs, R.styleable.MainPageToolbar, 0, 0).apply {
            getString(R.styleable.MainPageToolbar_toolbar_title)?.run {
                setTitle(this)
            }
            getResourceId(R.styleable.MainPageToolbar_toolbar_left_icon, 0).run {
                if (this == 0) return@run

                setLeftIcon(AppCompatResources.getDrawable(context, this))
            }
            getResourceId(R.styleable.MainPageToolbar_toolbar_right_icon, 0).run {
                if (this == 0) return@run

                setRightIcon(AppCompatResources.getDrawable(context, this))
            }
            getString(R.styleable.MainPageToolbar_toolbar_right_text)?.run {
                setRightText(this)
            }

            getResourceId(R.styleable.MainPageToolbar_toolbar_drawable_start,0).run {
                if ( this == 0 ) return@run

                setDrawableStartIcon(AppCompatResources.getDrawable(context,this))
            }
            getBoolean(R.styleable.MainPageToolbar_toolbar_right_text_visibility,false).run {
                enableRightText(this)
            }

            getBoolean(R.styleable.MainPageToolbar_toolbar_right_text_clickable,false).run{
                setClickableRightText(this)
            }
            recycle()
        }

        ViewCompat.setElevation(this, getContext().resources.getDimension(R.dimen.toolbar_elevation))
    }

    fun setTitle(title: String?) {
        if (title == null) return

        this.title = title
        mBinding.titleText.text = title
    }

    fun setTitle(@StringRes id: Int?) {
        if (id == null) return

        context.getString(id).run {
            this@Toolbar.title = this
            mBinding.titleText.text = this
        }
    }

    //region Left Icon
    fun setLeftIcon(drawable: Drawable?) {
        if (drawable == null) return

        enableLeftIcon(true)

        this.leftIconDrawable = drawable
        mBinding.leftButton.setImageDrawable(drawable)
    }

    fun setLeftIcon(@DrawableRes id: Int) {
        enableLeftIcon(true)

        this.leftIconDrawable = AppCompatResources.getDrawable(context, id)
        mBinding.leftButton.setImageDrawable(leftIconDrawable)
    }

    fun enableLeftIcon(enable: Boolean) {
        mBinding.leftButton.setVisibility(enable)
    }

    fun setLeftIconClickListener(listener: () -> Unit) {
        setLeftIconClickListener(OnClickListener { listener() })
    }

    fun setLeftIconClickListener(listener: OnClickListener) {
        mBinding.leftButton.setOnDebounceClickListener(listener)
    }
    //endregion

    //region Right Icon
    fun setRightIcon(drawable: Drawable?) {
        enableRightIcon(true)
        enableRightText(false)

        this.rightIconDrawable = drawable
        mBinding.rightButton.setImageDrawable(drawable)
    }

    fun setRightIcon(@DrawableRes id: Int) {
        enableRightIcon(true)
        enableRightText(false)

        this.rightIconDrawable = AppCompatResources.getDrawable(context, id)
        mBinding.rightButton.setImageDrawable(rightIconDrawable)
    }

    fun enableRightIcon(enable: Boolean) {
        mBinding.rightButton.setVisibility(enable)
    }

    fun setRightIconClickListener(listener: OnClickListener) {
        mBinding.rightButton.setOnDebounceClickListener(listener)
    }


    //endregion

    //region Right Text

    fun setRightText(@StringRes strRes: Int) {
        setRightText(context.getString(strRes))
    }

    fun setRightText(text: String) {
        enableRightIcon(false)
        enableRightText(true)
        rightText = text
        mBinding.rightText.text = rightText
    }

    fun setRightTextClickListener(listener: OnClickListener) {
        mBinding.rightText.setOnDebounceClickListener(listener)
    }

    fun enableRightText(enable: Boolean) {
        mBinding.rightText.setVisibility(enable)
    }

    fun setClickableRightText ( enable : Boolean ) {

        mBinding.apply {
            if (enable) {
                rightText.isClickable = true
                rightText.setTextColor(resources.getColor(R.color.white))
            } else {
                rightText.isClickable = false
                rightText.setTextColor(resources.getColor(R.color.black))
            }
        }
    }
    fun setDrawableStartIcon ( drawable : Drawable? ) {

        mBinding.apply {

            titleText.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable,null,null,null)
            titleText.compoundDrawablePadding = 20
        }
    }


    //endregion
}

@BindingAdapter("app:toolbar_title_binding")
fun Toolbar.setTitleTextBinding(text: String?) {
    setTitle(text)
}

@BindingAdapter("app:toolbar_title_binding")
fun Toolbar.setTitleTextBinding(@StringRes id: Int) {
    setTitle(id)
}

@BindingAdapter("app:toolbar_right_icon_binding")
fun Toolbar.setRightIconBinding(drawable: Drawable) {
    setRightIcon(drawable)
}

@BindingAdapter("app:toolbar_left_icon_binding")
fun Toolbar.setLeftIconBinding(drawable: Drawable) {
    setLeftIcon(drawable)
}

@BindingAdapter("app:toolbar_right_icon_visibility")
fun Toolbar.setRightIconBinding(enable: Boolean) {
    enableRightIcon(enable)
}

@BindingAdapter("app:toolbar_left_icon_visibility")
fun Toolbar.setLeftIconBinding(enable: Boolean) {
    enableLeftIcon(enable)
}

@BindingAdapter("app:toolbar_right_text_clickable")
fun Toolbar.setRightTextBinding(enable: Boolean){
    setClickableRightText(enable)
}

@BindingAdapter("app:toolbar_drawable_start")
fun Toolbar.setDrawableStartIconBinding( drawable : Drawable) {
    setDrawableStartIcon(drawable)
}

@BindingAdapter("app:toolbar_right_text_visibility")
fun Toolbar.setRightTextVisibilityBinding ( enable : Boolean) {
    enableRightText(enable)
}