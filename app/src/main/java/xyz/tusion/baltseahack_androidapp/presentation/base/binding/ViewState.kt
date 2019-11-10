package xyz.tusion.baltseahack_androidapp.presentation.base.binding

import com.jakewharton.rxrelay2.BehaviorRelay

class ViewState<T>(initValue: T? = null) : Bind<T>() {
    override val relay =
        if (initValue == null)
            BehaviorRelay.create<T>()
        else
            BehaviorRelay.createDefault<T>(initValue)

    val observable = asObservable()
}