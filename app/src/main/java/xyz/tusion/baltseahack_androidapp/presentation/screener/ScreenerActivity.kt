package xyz.tusion.baltseahack_androidapp.presentation.screener

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import xyz.tusion.baltseahack_androidapp.R
import xyz.tusion.baltseahack_androidapp.presentation.base.BaseActivity

class ScreenerActivity : BaseActivity() {

    lateinit var navController: NavController
//TODO    lateinit var screenerDocument: ScreenerDocument


    override fun onSupportNavigateUp(): Boolean {
        return findNavController(this, R.id.act_screener_nav_host_fragment).navigateUp()
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_screener)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        navController = findNavController(this, R.id.act_screener_nav_host_fragment)
//        screenerDocument = ScreenerDocument()
    }


    fun backToPreviousFrag(view: View) {
        navController.popBackStack()
    }
}
