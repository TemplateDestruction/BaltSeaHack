package xyz.tusion.baltseahack_androidapp.presentation.screener

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.frag_screener_2.*
import xyz.tusion.baltseahack_androidapp.R
import xyz.tusion.baltseahack_androidapp.presentation.base.BaseFragment

class ScreenerFrag2 : BaseFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.frag_screener_2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        frag_screener_2_tv_next.setOnClickListener {
//            if (age_group.checkedRadioButtonId != -1) {
                toThirdQuestion()
//            } else {
//                DialogUtils.showDialog(
//                    requireContext(),
//                    getString(R.string.screener_alert),
//                    getString(R.string.screener_alert_second_frag)
//                )
//            }
        }
        super.onViewCreated(view, savedInstanceState)
    }


    fun toThirdQuestion() {
//        navController.navigate(R.id.thirdQuestionFrag)
    }

//    fun fillScreenerDocument(max: Int?, min: Int?) {
//        (activity as ScreenerActivity).screenerDocument.ageRange = AgeRange(max, min)
//    }

}