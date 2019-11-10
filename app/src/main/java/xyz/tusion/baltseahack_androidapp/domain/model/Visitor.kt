package xyz.tusion.baltseahack_androidapp.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Visitor(
    val name: String
) : Parcelable


val listOfVisitor = listOf(
    Visitor("Валера Петров"),
    Visitor("Валера Петров"),
    Visitor("Валера Петров"),
    Visitor("Валера Петров"),
    Visitor("Валера Петров"),
    Visitor("Валера Петров"),
    Visitor("Валера Петров")
)