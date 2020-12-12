package ru.alexbox.arch_gb_ko.util

import android.app.AlertDialog
import android.content.Context
import ru.alexbox.arch_gb_ko.R

fun getStubAlertDialog(context: Context): AlertDialog {
    return getAlertDialog(context, null, null)
}

fun getAlertDialog(context: Context, title: String?, message: String?): AlertDialog {
    val builder = AlertDialog.Builder(context)
    var finalTitle: String? = context.getString(R.string.empty_server_responce)
    if (!title.isNullOrBlank()) {
        finalTitle = title
    }
    builder.setTitle(finalTitle)

    if (!message.isNullOrBlank()) {
        builder.setMessage(message)
    }

    builder.setCancelable(true)
    builder.setPositiveButton(R.string.button_cancel) {
            dialog, _ -> dialog.dismiss()
    }

    return builder.create()
}
