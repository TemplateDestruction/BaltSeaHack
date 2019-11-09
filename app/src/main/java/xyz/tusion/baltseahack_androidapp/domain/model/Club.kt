package xyz.tusion.baltseahack_androidapp.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Club(
    @SerializedName("id")
    @Expose
    var id: Int,
    @SerializedName("location")
    @Expose
    var location: ClubLocation
)