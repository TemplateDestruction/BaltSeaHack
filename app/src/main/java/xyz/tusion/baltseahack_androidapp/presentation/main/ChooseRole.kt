package xyz.tusion.baltseahack_androidapp.presentation.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.frag_choose_role.*
import xyz.tusion.baltseahack_androidapp.R
import xyz.tusion.baltseahack_androidapp.presentation.base.BaseFragment
import xyz.tusion.baltseahack_androidapp.presentation.teacher.TeacherActivity

class ChooseRole : BaseFragment(R.layout.frag_choose_role) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        visitor.setOnClickListener {
            navController.navigate(R.id.firstQuestionFrag)
        }
        teacher.setOnClickListener {
            startActivity(Intent(requireContext(), TeacherActivity::class.java))
            activity?.finish()
        }
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