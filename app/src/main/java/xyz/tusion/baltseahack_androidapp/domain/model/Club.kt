package xyz.tusion.baltseahack_androidapp.domain.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
class Club(
    @SerializedName("id")
    @Expose
    var id: Int,
    @SerializedName("address")
    @Expose
    var adress: String,
    @SerializedName("lat")
    @Expose
    var latitude: Float,
    @SerializedName("lon")
    @Expose
    var longtitude : Float,
    @SerializedName("name")
    @Expose
    var name: String,
    @SerializedName("sections")
    @Expose
    var sections: List<Section>

): Serializable, Parcelable