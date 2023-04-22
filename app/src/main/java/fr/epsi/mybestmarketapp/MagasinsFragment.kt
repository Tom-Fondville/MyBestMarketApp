package fr.epsi.mybestmarketapp

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import fr.epsi.mybestmarketapp.model.Product
import fr.epsi.mybestmarketapp.model.Store
import okhttp3.*
import okhttp3.internal.notify
import org.json.JSONObject
import java.io.IOException
import java.lang.Double

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MagasinsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MagasinsFragment : Fragment() {
    lateinit var googleMap: GoogleMap
    val stores = arrayListOf<Store>()

    @SuppressLint("MissingPermission")
    val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                // Precise location access granted.
                googleMap.isMyLocationEnabled = true
            }
            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                // Only approximate location access granted.
                googleMap.isMyLocationEnabled = true
            }
            else -> {
                // No location access granted.
            }
        }
    }

    private val callback = OnMapReadyCallback { googleMap ->
        for (store in stores) {
            val marker = MarkerOptions()
            marker.title(store.name)
            marker.position(LatLng(store.latitude, store.longitude))
            googleMap.addMarker(marker)
        }

        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(48.854885, 2.338646), 5f))

        googleMap.setOnMapClickListener {
            (activity?.application as MarketApplication).showToast(it.toString())
        }

        googleMap.setOnInfoWindowClickListener {
            val intent = Intent(activity, MagasinDetailActivity::class.java)
            val currentStore = stores.find { store -> store.name == it.title }
            intent.putExtra("name", currentStore?.name)
            intent.putExtra("image", currentStore?.pictureStore)
            intent.putExtra("address", currentStore?.address)
            intent.putExtra("city", currentStore?.city)
            intent.putExtra("zipcode", currentStore?.zipcode)
            intent.putExtra("description", currentStore?.description)
            startActivity(intent)
        }
        this.googleMap = googleMap
        locationPermissionRequest.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )

    }

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_magasins, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
        getStores()

//        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
//        mapFragment?.getMapAsync(callback)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment magasinsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MagasinsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun getStores(){
        val httpClient = OkHttpClient().newBuilder().build()
        val request = Request.Builder()
            .get()
            .url("https://www.ugarit.online/epsi/stores.json")
            .cacheControl(CacheControl.FORCE_NETWORK)
            .build()

        httpClient.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {}

            override fun onResponse(call: Call, response: Response) {
                if (response.body == null) return
                val storeResponse = toStoreList(response.body!!.string())
                stores.addAll(storeResponse)
                val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
//                mapFragment?.getMapAsync(callback)
                activity?.runOnUiThread{mapFragment?.getMapAsync(callback)}
//                activity?.runOnUiThread(googleMap::notify)
//                activity?.runOnUiThread{ googleMap.notify() }
//                activity?.runOnUiThread { productAdapter.notifyDataSetChanged() }
            }

        })
    }

    private fun toStoreList(jsonString: String): ArrayList<Store>{
        val storeList = arrayListOf<Store>()
        val jsStore = JSONObject(jsonString)
        val jsArray = jsStore.getJSONArray("stores")

        for (i in 0 until jsArray.length()) {
            val jsStore = jsArray.getJSONObject(i)
            val store = Store(
                jsStore.optInt("storeId", 0),
                jsStore.optString("name", "unknown"),
                jsStore.optString("description", "unknown"),
                jsStore.optString("pictureStore", "unknown"),
                jsStore.optString("address", "unknown"),
                jsStore.optInt("zipcode", 0),
                jsStore.optString("city", "unknown"),
                jsStore.optDouble("longitude"),
                jsStore.optDouble("latitude"),
            )
            storeList.add(store)
        }
        return storeList
    }
}