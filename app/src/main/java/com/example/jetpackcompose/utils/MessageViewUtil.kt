package com.example.jetpackcompose.utils

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MessageViewUtil(
    private val context: Context,
    private val type: MessageViewType = MessageViewType.TOAST
) {

    fun show(message: String){
        builderMessageView(message = message)
    }

    fun show(title: String, message: String){
        builderMessageView(title = title, message = message)
    }

    private fun builderMessageView(title: String = "Attention", message: String){
        when(type){
            MessageViewType.TOAST -> toastBuilder(message).show()
            MessageViewType.ALERT -> alertBuilder(title, message).show()
        }
    }

    private fun toastBuilder(message: String): Toast{
        return Toast.makeText(context, message, Toast.LENGTH_SHORT)
    }

    private fun alertBuilder(title: String, message: String): AlertDialog{
        return AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .create()
    }
}

enum class MessageViewType{
    TOAST, ALERT
}