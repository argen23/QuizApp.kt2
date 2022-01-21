package com.example.quizappkt2.kolg.uitls.extensions

import android.app.AlertDialog
import android.content.Context
import com.example.quizappkt2.R

fun deleteDialog(context: Context, title: String, msg: String, listener: () -> Unit) {
    AlertDialog.Builder(context)
        .setIcon(R.drawable.fvsdfgd)
        .setTitle(title)
        .setMessage(msg)
        .setPositiveButton("Ok") { _, _ ->
            listener()
        }
        .setNegativeButton("Cancel") { dialog, _ ->
            dialog.cancel()
        }.show()
}