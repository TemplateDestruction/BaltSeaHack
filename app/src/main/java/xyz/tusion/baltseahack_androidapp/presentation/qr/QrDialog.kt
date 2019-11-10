package xyz.tusion.baltseahack_androidapp.presentation.qr


import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import androidx.fragment.app.DialogFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.dialog_qr.*

import xyz.tusion.baltseahack_androidapp.R
import xyz.tusion.baltseahack_androidapp.domain.QRCodeGenerator
import xyz.tusion.baltseahack_androidapp.domain.getNormalProfileQrCodeSize
import xyz.tusion.baltseahack_androidapp.domain.repository.RepositoryProvider
import xyz.tusion.baltseahack_androidapp.domain.utils.DialogUtils
import xyz.tusion.baltseahack_androidapp.domain.utils.PreferenceUtils
import java.util.concurrent.TimeUnit




@SuppressLint("ValidFragment")
class QrDialog : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    var isNotChecked = true
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_qr, container, false)
        view.findViewById<View>(R.id.close).setOnClickListener { dialog!!.dismiss() }

        var qrId = arguments?.getString("eventId")!!


        QRCodeGenerator.generateFromString(
            qrId,
            getNormalProfileQrCodeSize(requireContext())
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                qr_image.setImageBitmap(it)
                qr_id.text = qrId
                RepositoryProvider
                    .getJsonRepository()
                    .getCountByEventId("10")
                    .delay(5000, TimeUnit.MILLISECONDS)
                    .subscribe({
                        Toast.makeText(requireContext(), "Toast", Toast.LENGTH_SHORT).show()
                        if (it > PreferenceUtils.getAGE()) {
                            DialogUtils.showDialog(
                                requireContext(),
                                "Уведомление",
                                "Ваш ученик отметился! Текущее количество отметившихся: $it"
                            )
                            isNotChecked = false
                        }
                    }, {
                    })
            }, {})




        return view
    }
}

