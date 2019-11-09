package xyz.tusion.baltseahack_androidapp.presentation.main

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.frag_second.*
import xyz.tusion.baltseahack_androidapp.presentation.base.BaseFragment
import xyz.tusion.baltseahack_androidapp.presentation.qr.QrDialog
import xyz.tusion.baltseahack_androidapp.presentation.qr.ScanQrFragment.Companion.SCAN_QR_CONTENT_CODE
import android.R.attr.fragment



class SecondFragment : BaseFragment(xyz.tusion.baltseahack_androidapp.R.layout.frag_second) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val qrReceipt = arguments?.getString(SCAN_QR_CONTENT_CODE)!!
        qrlink.text = qrReceipt

        // Create and show the dialog.
        val ft = fragmentManager!!.beginTransaction()
        val newFragment = QrDialog()

        val bundle = Bundle()
        bundle.putString("userId", qrReceipt)

        newFragment.arguments = bundle
        newFragment.show(ft, "dialog")
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