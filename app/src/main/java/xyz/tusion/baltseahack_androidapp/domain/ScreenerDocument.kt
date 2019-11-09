package xyz.tusion.baltseahack_androidapp.domain

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class ScreenerDocument(
    var age: Int = 0,
    var interests: List<String>? = null,
    var nativeLanguage: String? = null,
    var occupation: String? = null,
    var sex: Int? = null,
    var youtubeChannels: List<String>? = null
) : Parcelable