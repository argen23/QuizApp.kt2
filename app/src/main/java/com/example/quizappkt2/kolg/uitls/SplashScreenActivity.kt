package com.example.quizappkt2.kolg.uitls

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.quizappkt2.R
import com.example.quizappkt2.kolg.presentation.MainActivity
import dagger.hilt.android.AndroidEntryPoint


@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashScreenActivity : AppCompatActivity() {

    lateinit var iv : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        iv = findViewById(R.id.iv_br)

        iv.alpha = 0f
        iv.animate().alpha(1f).withEndAction{
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            val i  = Intent(this , MainActivity::class.java)
            startActivity(i)
            finish()
        }
    }

}