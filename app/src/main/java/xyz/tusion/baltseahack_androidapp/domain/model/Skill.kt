package xyz.tusion.baltseahack_androidapp.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Skill(
    val name: String,
    val value: Int
) : Parcelable