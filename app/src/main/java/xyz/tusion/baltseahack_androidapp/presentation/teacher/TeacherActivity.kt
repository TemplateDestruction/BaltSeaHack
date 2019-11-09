package xyz.tusion.baltseahack_androidapp.presentation.teacher

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import xyz.tusion.baltseahack_androidapp.R
import xyz.tusion.baltseahack_androidapp.presentation.base.BaseActivity

class TeacherActivity : BaseActivity(R.layout.act_teacher) {
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController = Navigation.findNavController(this, R.id.act_teacher_nav_host_fragment)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

}