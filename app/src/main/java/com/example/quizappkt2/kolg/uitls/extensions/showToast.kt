package com.example.quizappkt2.kolg.uitls.extensions

import android.content.Context
import android.widget.Toast


fun Context.showToast(message:String){
    Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
}