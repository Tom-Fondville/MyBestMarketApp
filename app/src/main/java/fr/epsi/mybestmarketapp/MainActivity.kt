package fr.epsi.mybestmarketapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : BaseActivity() {

    val carteFragment = CarteFragment.newInstance("", "")
    val offreFragment = OffreFragment.newInstance("", "")
    val magasinsFragment = MagasinsFragment.newInstance("", "")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setHeaderTitle("MyBestMarket")
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

        val magasinsBtn = findViewById<TextView>(R.id.magasinsBtn)
        magasinsBtn.setOnClickListener {
            showMagasinsFragment()
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

    fun showMagasinsFragment() {
        val frManager = supportFragmentManager
        val fragmentTra = frManager.beginTransaction()
        fragmentTra.addToBackStack("Magasins")
        fragmentTra.replace(R.id.fragmentContent, magasinsFragment)
        fragmentTra.commit()
    }
}