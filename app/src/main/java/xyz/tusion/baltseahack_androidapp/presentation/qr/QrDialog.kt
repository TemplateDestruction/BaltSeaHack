package xyz.tusion.baltseahack_androidapp.presentation.qr


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.DialogFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.dialog_add_users.*

import xyz.tusion.baltseahack_androidapp.R
import xyz.tusion.baltseahack_androidapp.domain.QRCodeGenerator
import xyz.tusion.baltseahack_androidapp.domain.getNormalProfileQrCodeSize


@SuppressLint("ValidFragment")
class QrDialog : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_add_users, container, false)
        view.findViewById<View>(R.id.close).setOnClickListener { dialog!!.dismiss() }

        var qrId = arguments?.getString("userId")!!

        QRCodeGenerator.generateFromString(
            qrId,
            getNormalProfileQrCodeSize(requireContext())
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                qr_image.setImageBitmap(it)
                qr_id.text = qrId
            }, {})

        return view
    }
}

