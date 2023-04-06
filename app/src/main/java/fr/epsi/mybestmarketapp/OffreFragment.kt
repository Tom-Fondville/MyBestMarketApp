package fr.epsi.mybestmarketapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.epsi.mybestmarketapp.adapter.ProductAdapter
import fr.epsi.mybestmarketapp.model.Product
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OffreFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OffreFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var recyclerView: RecyclerView? = null


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
        val view = inflater.inflate(R.layout.fragment_offre, container, false)



        recyclerView = view.findViewById(R.id.recyclerView_products)
//        recyclerView?.layoutManager = LinearLayoutManager(this.context)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val products = arrayListOf<Product>()
        recyclerView?.layoutManager = LinearLayoutManager(view.context)
        val productAdapter = ProductAdapter(products)
        recyclerView?.adapter = productAdapter

        val httpClient = OkHttpClient().newBuilder().build()
        val request = Request.Builder()
            .get()
            .url("https://www.ugarit.online/epsi/offers.json")
            .cacheControl(CacheControl.FORCE_NETWORK)
            .build()

        httpClient.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {}

            override fun onResponse(call: Call, response: Response) {
                if (response.body == null) return
                val productsResponse = toProductsList(response.body!!.string())
                products.addAll(productsResponse)
                activity?.runOnUiThread { productAdapter.notifyDataSetChanged() }
            }

        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment OffreFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OffreFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


    fun toProductsList(jsonString: String): ArrayList<Product>{
        val productList = arrayListOf<Product>()
        val jsProduct = JSONObject(jsonString)
        val jsArray = jsProduct.getJSONArray("items")

        for (i in 0 until jsArray.length()) {
            val jsCat = jsArray.getJSONObject(i)
            val product = Product(
                jsCat.optString("name", "unknown"),
                jsCat.optString("description", "unknown"),
                jsCat.optString("picture_url", "unknown")
            )
            productList.add(product)
        }
        return productList
    }
}