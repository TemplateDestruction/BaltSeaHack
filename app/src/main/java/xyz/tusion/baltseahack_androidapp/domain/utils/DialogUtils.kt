package xyz.tusion.baltseahack_androidapp.domain.utils

import android.app.AlertDialog
import android.content.Context
import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import kotlinx.android.synthetic.main.basic_alert_dialog_layout.view.*
import xyz.tusion.baltseahack_androidapp.R

class DialogUtils {
  companion object {
    fun showDialog(context: Context, title: String, message: String) {
      val alertadd = AlertDialog.Builder(context)
      val factory = LayoutInflater.from(context)
      val view = factory.inflate(R.layout.basic_alert_dialog_layout, null)
      alertadd.setView(view)
      val dialog = alertadd.create()
      view.title_alert_dialog.text = title
      view.message_alert_dialog.text = message
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        view.ok_alert_dialog.text =
          Html.fromHtml("OK", Html.FROM_HTML_MODE_LEGACY)
      } else {
        view.ok_alert_dialog.text = Html.fromHtml("OK")
      }
      view.ok_alert_dialog.setOnClickListener {
        dialog.dismiss()
      }
      dialog.show()
    }

  }

}