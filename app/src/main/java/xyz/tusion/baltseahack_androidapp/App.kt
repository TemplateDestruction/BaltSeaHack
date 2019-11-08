package xyz.tusion.baltseahack_androidapp

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.widget.Toast
import io.reactivex.disposables.CompositeDisposable
import xyz.tusion.baltseahack_androidapp.domain.api.ServerApiFactory
import xyz.tusion.baltseahack_androidapp.domain.repository.RepositoryProvider

class App : Application() {
  companion object {
    @SuppressLint("StaticFieldLeak")
    var sContext: Context? = null
  }
  var appBinds = CompositeDisposable()

  fun showMessage(message: String) {
    Toast.makeText(sContext, message, Toast.LENGTH_SHORT).show()
  }

  //test change
  override fun onCreate() {
    super.onCreate()
    sContext = this

    ServerApiFactory.recreate()
    RepositoryProvider.init()
  }

  protected override fun attachBaseContext(context: Context) {
    super.attachBaseContext(context)
  }
}