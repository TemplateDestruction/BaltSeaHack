package xyz.tusion.baltseahack_androidapp.presentation.teacher

import android.content.Intent
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.frag_main_teacher.*
import xyz.tusion.baltseahack_androidapp.R
import xyz.tusion.baltseahack_androidapp.presentation.base.BaseFragment

class MainTeacherFragment : BaseFragment(R.layout.frag_main_teacher) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*create_event.setOnClickListener {
            navController.navigate(R.id.createEventFragment)
        }*/
        navController.navigate(R.id.myEventFragment)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun createBinds() {
        super.createBinds()
        rxBinds.addAll(

        )
    }
}