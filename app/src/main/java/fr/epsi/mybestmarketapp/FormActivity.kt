package fr.epsi.mybestmarketapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class FormActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        val nom = findViewById<EditText>(R.id.editTextNom)
        val prenom = findViewById<EditText>(R.id.editTextPrenom)
        val mail = findViewById<EditText>(R.id.editTextTextEmailAddress)
        val adresse = findViewById<EditText>(R.id.editTextAddress)
        val codePostal = findViewById<EditText>(R.id.editTextCodePostal)
        val ville = findViewById<EditText>(R.id.editTextVille)
        val carteFidelite = findViewById<EditText>(R.id.editTextCarteFidelite)

        val button = findViewById<Button>(R.id.buttonSignup)
        button.setOnClickListener { view ->
            writeSharedPref("nom", nom.text.toString())
            writeSharedPref("prenom", prenom.text.toString())
            writeSharedPref("mail", mail.text.toString())
            writeSharedPref("adresse", adresse.text.toString())
            writeSharedPref("codePostal", codePostal.text.toString())
            writeSharedPref("ville", ville.text.toString())
            writeSharedPref("carteFidelite", carteFidelite.text.toString())

            if (!verify("Nom", nom.text.toString()))
                return@setOnClickListener
            if(!verify("Prenom", prenom.text.toString()))
                return@setOnClickListener
            if(!verify("Mail", mail.text.toString()))
                return@setOnClickListener
            if(!verify("Adresse", adresse.text.toString()))
                return@setOnClickListener
            if(!verify("Code Postal", codePostal.text.toString()))
                return@setOnClickListener
            if(!verify("Ville", ville.text.toString()))
                return@setOnClickListener
            if(!verify("Carte Fidelite", carteFidelite.text.toString()))
                return@setOnClickListener

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}