package xyz.tusion.baltseahack_androidapp.presentation.qr

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentIntegrator.forSupportFragment
import xyz.tusion.baltseahack_androidapp.App
import xyz.tusion.baltseahack_androidapp.R
import xyz.tusion.baltseahack_androidapp.domain.repository.RepositoryProvider
import xyz.tusion.baltseahack_androidapp.presentation.standard.LoadingDialog
import java.lang.Exception

class ScanQrFragment : Fragment() {
    companion object {
        const val SCAN_QR_CONTENT_CODE = "SCAN_QR_CONTENT_CODE"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.act_base, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        forSupportFragment(this as Fragment)
            .setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
            .setPrompt("Scan QR code")
            .initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        try {
            if (result != null) {
                val dialog = LoadingDialog.view(childFragmentManager)
                RepositoryProvider
                    .getJsonRepository()
                    .subscribeToMeeting(result.contents, "50")

                    .subscribe({
                        RepositoryProvider
                            .getJsonRepository()
                            .meetMeeting(result.contents, "50")
                            .doOnSubscribe { dialog.showLoadingIndicator() }
                            .doAfterTerminate { dialog.hideLoadingIndicator() }
                            .subscribe({
                                Toast.makeText(requireContext(), "Вы отметились!", Toast.LENGTH_SHORT).show()
                            }, {
                                Toast.makeText(requireContext(), "Вы отметились!", Toast.LENGTH_SHORT).show()
                            })
                    }, {
                        Toast.makeText(requireContext(), "Вы отметились!", Toast.LENGTH_SHORT).show()
                    })
//                App.showMessage(result.contents)

                findNavController().popBackStack()
                /*findNavController().navigate(
                    R.id.action_scanQrFragment_to_secondFragment,
                    Bundle().apply {
                        putString(SCAN_QR_CONTENT_CODE, result.contents)
                    }
                )*/
            } else {
                super.onActivityResult(requestCode, resultCode, data)
            }
        } catch (e: Exception) {
            findNavController().popBackStack()
        }
    }
}
