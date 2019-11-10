package xyz.tusion.baltseahack_androidapp.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import xyz.tusion.baltseahack_androidapp.presentation.main.MainActivity
import xyz.tusion.baltseahack_androidapp.presentation.main.MainActivity2
import xyz.tusion.baltseahack_androidapp.presentation.screener.ScreenerActivity
import xyz.tusion.baltseahack_androidapp.presentation.ui.BottomNavAct

class SplashActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

        startActivity(Intent(this, ScreenerActivity::class.java))
//    startActivity(Intent(this, BottomNavAct::class.java))
//    startActivity(Intent(this, MainActivity2::class.java))



//      startActivity(Intent(this, CalendarActivity::class.java))

      finish()
  }
}
