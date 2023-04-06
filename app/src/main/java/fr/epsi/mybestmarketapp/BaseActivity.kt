package fr.epsi.mybestmarketapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
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
    fun setAccoutOnclick(){
        val accountButton = findViewById<ImageView>(R.id.headerAccountButtonImageView)
        accountButton.setOnClickListener {
            val intent = Intent(this, AccountActivity::class.java)
            startActivity(intent)
        }
    }
    fun writeSharedPref(key:String,value:String){
        val sharedPreferences: SharedPreferences = getSharedPreferences("account", Context.MODE_PRIVATE)
        val editor =sharedPreferences.edit()
        editor.putString(key,value)
        editor.apply()
    }

    fun readSharedPref(key:String):String{
        val sharedPreferences: SharedPreferences = getSharedPreferences("account", Context.MODE_PRIVATE)
        return sharedPreferences.getString(key,"").toString()
    }

    fun verify(n: String, value: String): Boolean {
        if (value.isEmpty() || value == "") {
            (application as MarketApplication).showToast("Le champ $n est vide")
            return false
        }
        return true
    }

}