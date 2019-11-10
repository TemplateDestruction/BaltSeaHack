package xyz.tusion.baltseahack_androidapp.presentation.base.binding

import com.jakewharton.rxrelay2.Relay
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer

abstract class Bind<T> {
    abstract val relay: Relay<T>

    fun subscribe(onNext: Consumer<in T>): Disposable = relay.subscribe(onNext)

    fun subscribe(onNext: Observer<in T>) = relay.subscribe(onNext)

    fun subscribe(onNext: (t: T) -> Unit): Disposable = relay.subscribe(onNext)

    fun accept(t: T) = relay.accept(t)

    protected fun asObservable(): Observable<T> {
        return relay.hide()
    }

    protected fun asConsumer(): Consumer<T> {
        return relay
    }
}