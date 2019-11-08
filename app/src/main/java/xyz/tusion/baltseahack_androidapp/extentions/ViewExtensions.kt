package xyz.tusion.nrboom_app.extentions

import android.os.Bundle
import android.os.Parcelable
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

const val EXTRA_SCREEN_DATA = "EXTRA_SCREEN_DATA"

fun Fragment.addDto(args: Parcelable? = null): Fragment {
    args?.let {
        this.arguments = bundleOf(EXTRA_SCREEN_DATA to it)
    }
    return this
}

fun <T : Parcelable> Bundle?.getDto(): T? {
    return this?.getParcelable(EXTRA_SCREEN_DATA)
}