package fr.epsi.mybestmarketapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun setHeaderTitle(title: String) {
        val headerTextView = findViewById<TextView>(R.id.headerTextView)
        headerTextView.text = title
    }

    fun showBackButton() {
        val backButton = findViewById<ImageView>(R.id.headerBackButtonImageView)
        backButton.visibility = ImageView.VISIBLE
        backButton.setOnClickListener {
            finish()
        }
    }

}