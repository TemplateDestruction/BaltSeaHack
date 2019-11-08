package xyz.tusion.baltseahack_androidapp.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import xyz.tusion.baltseahack_androidapp.presentation.main.MainActivity

class SplashActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

        startActivity(Intent(this, MainActivity::class.java))
    finish()
  }
}
