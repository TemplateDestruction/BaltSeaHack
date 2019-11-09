package xyz.tusion.baltseahack_androidapp.presentation.screener

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.frag_screener_3.*
import xyz.tusion.baltseahack_androidapp.R
import xyz.tusion.baltseahack_androidapp.presentation.base.BaseFragment
import xyz.tusion.baltseahack_androidapp.presentation.main.MainActivity

class ScreenerFrag3 : BaseFragment() {

    private var languages = ArrayList<String>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.frag_screener_3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        frag_screener_3_tv_next.setOnClickListener {
            toMainAct()
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun toMainAct() {
        Log.e("VIDEO HUB", "METHOD STARTED")
//        (activity as ScreenerActivity).screenerDocument.interests = selectedActivites
//        RepositoryProvider
//            .getJsonRepository()
//            .sendScreener(ScreenerFactory.generateJson((activity as ScreenerActivity).screenerDocument))
//            .doOnSubscribe { dialog.showLoadingIndicator() }
//            .doAfterTerminate { dialog.hideLoadingIndicator() }
//            .subscribe({onSuccess()}, { Log.e("toVideoHub", it.localizedMessage)})
        onSuccess()
    }

    private fun onSuccess() {
        startActivity(Intent(requireContext(), MainActivity::class.java))
        activity?.finish()
    }
}