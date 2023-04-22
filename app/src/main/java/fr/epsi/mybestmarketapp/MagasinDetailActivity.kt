package fr.epsi.mybestmarketapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class MagasinDetailActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_magasin_detail)
        val name = intent.extras!!.getString("name")
        val image = intent.extras!!.getString("image")
        val address = intent.extras!!.getString("address")
        val city = intent.extras!!.getString("city")
        val zipCode = intent.extras!!.getString("zipCode")
        val description = intent.extras!!.getString("description")

        setHeaderTitle(name.toString())
        showBackButton()

        val imageView = findViewById<ImageView>(R.id.layout_store_ImageView)
        Picasso.get()
            .load(image)
            .into(imageView)


        val adresseTextView = findViewById<TextView>(R.id.textViewStoreAdress)
        adresseTextView.text = address.toString()

        val villeTextView = findViewById<TextView>(R.id.textViewStoreCodePostalVille)
        villeTextView.text = zipCode.toString() + " - " + city.toString()

        val descriptionTextView = findViewById<TextView>(R.id.textViewStoreDescription)
        descriptionTextView.text = "Description : " + description.toString()
    }
}