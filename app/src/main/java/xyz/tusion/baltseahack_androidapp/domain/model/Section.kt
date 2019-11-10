package xyz.tusion.baltseahack_androidapp.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Section(
    @SerializedName("id")
    @Expose
    var id: Int,
    @SerializedName("name")
    @Expose
    var name: String
)