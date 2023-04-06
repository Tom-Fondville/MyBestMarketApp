package fr.epsi.mybestmarketapp

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast

class MarketApplication : Application(){

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

    fun showToast(txt:String){
        Toast.makeText(this,txt, Toast.LENGTH_SHORT).show()
    }
}