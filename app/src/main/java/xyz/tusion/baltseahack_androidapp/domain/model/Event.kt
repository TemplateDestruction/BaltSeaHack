package xyz.tusion.baltseahack_androidapp.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Event(
    val id: Int,
    val name: String,
    val startDate: String,
    val endDate: String,
    val visitors: List<Visitor>
) : Parcelable