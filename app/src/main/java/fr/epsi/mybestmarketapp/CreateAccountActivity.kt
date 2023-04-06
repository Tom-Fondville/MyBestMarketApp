package fr.epsi.mybestmarketapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button

class CreateAccountActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        val qrCodeBtn = findViewById<Button>(R.id.buttonQrCode)
        qrCodeBtn.setOnClickListener { view ->
            //TODO faire activite QrCode
            val intent = Intent(this, FormActivity::class.java)
            startActivity(intent)
        }

        val formBtn = findViewById<Button>(R.id.buttonForm)
        formBtn.setOnClickListener { view ->
            val intent = Intent(this, FormActivity::class.java)
            startActivity(intent)
        }
    }
}