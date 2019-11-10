package xyz.tusion.baltseahack_androidapp.presentation.qr


import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.dialog_qr.*
import xyz.tusion.baltseahack_androidapp.App
import xyz.tusion.baltseahack_androidapp.domain.QRCodeGenerator
import xyz.tusion.baltseahack_androidapp.domain.getNormalProfileQrCodeSize
import xyz.tusion.baltseahack_androidapp.domain.repository.RepositoryProvider
import xyz.tusion.baltseahack_androidapp.presentation.teacher.MyEventFragment
import java.util.concurrent.TimeUnit


@SuppressLint("ValidFragment")
class QrDialog : DialogFragment() {
    companion object {
        var timeOut = PublishRelay.create<Int>()
    }

    var wastedTime = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    var isNotChecked = true
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =
            inflater.inflate(xyz.tusion.baltseahack_androidapp.R.layout.dialog_qr, container, false)
        view.findViewById<View>(xyz.tusion.baltseahack_androidapp.R.id.close)
            .setOnClickListener { dialog!!.dismiss() }

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

                val timer = object : CountDownTimer(3000, 1000) {
                    override fun onTick(l: Long) {

                    }

                    override fun onFinish() {
                        dialog!!.dismiss()
                        App.showMessage("Ваш ученик отметился! Текущее количество отметившихся: ")
                        MyEventFragment.users++
                        timeOut.accept(1)

                        RepositoryProvider
                            .getJsonRepository()
                            .getCountByEventId("10")
                            .subscribe({
                                Toast.makeText(requireContext(), "Toast", Toast.LENGTH_SHORT).show()
//                        if (it > PreferenceUtils.getAGE()) {
                                App.showMessage("Ваш ученик отметился! Текущее количество отметившихся: $it")
                                /*DialogUtils.showDialog(
                                    requireContext(),
                                    "Уведомление",
                                    "Ваш ученик отметился! Текущее количество отметившихся: $it"
                                )*/
                                isNotChecked = false
//                        }
                            }, {
                            })
                    }
                }

                timer.start()


                RepositoryProvider
                    .getJsonRepository()
                    .getCountByEventId("10")
                    .delay(5000, TimeUnit.MILLISECONDS)
                    .subscribe({
                        Toast.makeText(requireContext(), "Toast", Toast.LENGTH_SHORT).show()
//                        if (it > PreferenceUtils.getAGE()) {
                        App.showMessage("Ваш ученик отметился! Текущее количество отметившихся: $it")
                        /*DialogUtils.showDialog(
                            requireContext(),
                            "Уведомление",
                            "Ваш ученик отметился! Текущее количество отметившихся: $it"
                        )*/
                        isNotChecked = false
//                        }
                    }, {
                    })
            }, {})




        return view
    }
}

