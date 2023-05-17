package com.example.project_groep_2

class Product(Price: Float, Name: String, Stores: List<String>, Images: List<String>) {
    private var m_price: Float = Price
    private var m_name: String = Name
    private var m_stores: List<String> = Stores
    private var m_imageResources: List<String> = Images

    fun getPrice(): Float{
        return m_price
    }

    fun getName(): String{
        return m_name
    }

    fun getStores(): List<String>{
        return m_stores
    }

    fun getImageResources(): List<String>{
        return m_imageResources
    }
}