package xyz.tusion.baltseahack_androidapp.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ClubLocation(
    @SerializedName("id")
    @Expose
    var id: Int,
    @SerializedName("locationLatitude")
    @Expose
    var latitude: Float,
    @SerializedName("locationLongitude")
    @Expose
    var longtitude: Float,
    @SerializedName("name")
    @Expose
    var name: String
)