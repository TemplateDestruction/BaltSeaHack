package xyz.tusion.baltseahack_androidapp.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import xyz.tusion.baltseahack_androidapp.R
import xyz.tusion.baltseahack_androidapp.presentation.base.BaseActivity

class MainActivity : BaseActivity(R.layout.act_main) {
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController = Navigation.findNavController(this, R.id.act_main_nav_host_fragment)

    }

    override fun onStart() {
        super.onStart()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

}