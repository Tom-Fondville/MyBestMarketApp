package fr.epsi.mybestmarketapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class AccountActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)
        setHeaderTitle(getString(R.string.account_title))
        showBackButton()

        val nom = readSharedPref("nom")
        val nomEditext = findViewById<EditText>(R.id.editTextNom)
        nomEditext.setText(nom)

        val prenom = readSharedPref("prenom")
        val prenomEditext = findViewById<EditText>(R.id.editTextPrenom)
        prenomEditext.setText(prenom)

        val mail = readSharedPref("mail")
        val mailEditext = findViewById<EditText>(R.id.editTextTextEmailAddress)
        mailEditext.setText(mail)

        val adresse = readSharedPref("adresse")
        val adresseEditext = findViewById<EditText>(R.id.editTextAddress)
        adresseEditext.setText(adresse)

        val codePostal = readSharedPref("codePostal")
        val codePostalEditext = findViewById<EditText>(R.id.editTextCodePostal)
        codePostalEditext.setText(codePostal)

        val ville = readSharedPref("ville")
        val villeEditext = findViewById<EditText>(R.id.editTextVille)
        villeEditext.setText(ville)


        val buttton = findViewById<Button>(R.id.updateButton)
        buttton.setOnClickListener {
            if (!verify("Nom", nomEditext.text.toString()))
                return@setOnClickListener
            if(!verify("Prenom", prenomEditext.text.toString()))
                return@setOnClickListener
            if(!verify("Mail", mailEditext.text.toString()))
                return@setOnClickListener
            if(!verify("Adresse", adresseEditext.text.toString()))
                return@setOnClickListener
            if(!verify("Code Postal", codePostalEditext.text.toString()))
                return@setOnClickListener
            if(!verify("Ville", villeEditext.text.toString()))
                return@setOnClickListener

            writeSharedPref("nom", nomEditext.text.toString())
            writeSharedPref("prenom", prenomEditext.text.toString())
            writeSharedPref("mail", mailEditext.text.toString())
            writeSharedPref("adresse", adresseEditext.text.toString())
            writeSharedPref("codePostal", codePostalEditext.text.toString())
            writeSharedPref("ville", villeEditext.text.toString())

            (application as MarketApplication).showToast("Modifications effectuées")
        }



    }
}