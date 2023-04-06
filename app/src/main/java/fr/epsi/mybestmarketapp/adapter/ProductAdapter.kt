package fr.epsi.mybestmarketapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.epsi.mybestmarketapp.R
import fr.epsi.mybestmarketapp.model.Product

class ProductAdapter (val products: ArrayList<Product>): RecyclerView.Adapter<ProductAdapter.ViewHolder> () {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val imageView = view.findViewById<ImageView>(R.id.layout_product_ImageView)
        val productNameTextView = view.findViewById<TextView>(R.id.layout_product_name)
        val productDescriptionTextView = view.findViewById<TextView>(R.id.layout_product_description)
        val container = view.findViewById<LinearLayout>(R.id.layout_product_linearLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_product, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products.get(position)

        holder.productNameTextView.text = product.name
        holder.productDescriptionTextView.text = product.description
        Picasso.get().load(product.picture_url).into(holder.imageView)
    }
}