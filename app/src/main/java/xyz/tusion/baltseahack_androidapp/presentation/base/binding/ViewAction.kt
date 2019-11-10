package xyz.tusion.baltseahack_androidapp.presentation.base.binding

import com.jakewharton.rxrelay2.PublishRelay
import com.jakewharton.rxrelay2.Relay

class ViewAction<T>() : Bind<T>() {
    override val relay: Relay<T> = PublishRelay.create<T>()

    val consumer = asConsumer()
}