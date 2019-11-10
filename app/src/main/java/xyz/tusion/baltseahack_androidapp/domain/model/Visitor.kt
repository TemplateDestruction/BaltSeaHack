package xyz.tusion.baltseahack_androidapp.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Visitor(
    val name: String
) : Parcelable


val listOfVisitor = listOf(
    Visitor("Валерий Петров"),
    Visitor("Андрей Касаренко"),
    Visitor("Валерия Буловенко"),
    Visitor("Кирилл Потапенко"),
    Visitor("Максим Остроухов"),
    Visitor("Петр Меньшов"),
    Visitor("Константин Обухов")
)