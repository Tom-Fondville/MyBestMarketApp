package fr.epsi.mybestmarketapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            val mail = (application as MarketApplication).readSharedPref("mail")
            if (mail.isEmpty()) {
                startActivity(Intent(application, CreateAccountActivity::class.java))
            } else {
                startActivity(Intent(application, MainActivity::class.java))
            }
        }, 2000)
    }
}