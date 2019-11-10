package xyz.tusion.baltseahack_androidapp.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Club(
    @SerializedName("id")
    @Expose
    var id: Int,
    @SerializedName("adress")
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

)