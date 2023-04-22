package fr.epsi.mybestmarketapp.model

class Store (val storeId: Int, val name: String, val description: String,
             val pictureStore: String, val address: String, val zipcode: Int, val city: String,
             val longitude : Double, val latitude: Double) {}