package xyz.tusion.baltseahack_androidapp.presentation.screener

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.frag_screener_1.*
import xyz.tusion.baltseahack_androidapp.R
import xyz.tusion.baltseahack_androidapp.presentation.base.BaseFragment

class ScreenerFrag1 : BaseFragment() {


    lateinit var sex: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.frag_screener_1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        next_first_question.setOnClickListener {
//            if (radioGroup_sex_screener_first_frag.checkedRadioButtonId != -1) {
                toSecondQuestion()
//            } else {
                /*DialogUtils.showDialog(
                    requireContext(),
                    getString(R.string.screener_alert),
                    getString(R.string.screener_screener_alert_first_frag)
                )*/
//            }

        }
        super.onViewCreated(view, savedInstanceState)
    }

    fun toSecondQuestion() {
//        fillScreenerDocument()
        navController.navigate(R.id.secondQuestionFrag)
    }

//    private fun fillScreenerDocument() {
//        val screenDoc = (activity as ScreenerActivity).screenerDocument
//        if (firstQuestion_male_btn.isChecked) {
//            screenDoc.sex = 1
//        } else {
//            screenDoc.sex = 0
//        }
//    }


}