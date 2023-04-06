package fr.epsi.mybestmarketapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : BaseActivity() {

    val carteFragment = CarteFragment.newInstance("", "")
    val offreFragment = OffreFragment.newInstance("", "")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setHeaderTitle("TROUVER UN LOGO")
        setAccoutOnclick()

        showCarteFragment()

        val carteBtn = findViewById<TextView>(R.id.carteBtn)
        carteBtn.setOnClickListener {
            showCarteFragment()
        }

        val offreBtn = findViewById<TextView>(R.id.offresBtn)
        offreBtn.setOnClickListener {
            showOffreFragment()
        }


    }


    fun showCarteFragment() {
        val frManager = supportFragmentManager
        val fragmentTra = frManager.beginTransaction()
        fragmentTra.addToBackStack("Carte")
        fragmentTra.replace(R.id.fragmentContent, carteFragment)
        fragmentTra.commit()
    }

    fun showOffreFragment() {
        val frManager = supportFragmentManager
        val fragmentTra = frManager.beginTransaction()
        fragmentTra.addToBackStack("Offre")
        fragmentTra.replace(R.id.fragmentContent, offreFragment)
        fragmentTra.commit()
    }
}