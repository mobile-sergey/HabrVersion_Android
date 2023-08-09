package ru.habr.version

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

@SuppressLint("CustomSplashScreen")
class LaunchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)

        val duration = 2000L
        val handler = Handler(Looper.myLooper()!!)

        val textVersion = findViewById<TextView>(R.id.textVersion)
        textVersion.text = App.version

        handler.postDelayed(
            {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            },
            duration
        )
    }
}