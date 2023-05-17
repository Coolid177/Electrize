package com.example.project_groep_2

class ProductOrder(Product: Product, ProductPrice: Float, Quantity: Int, Store: String) {
    private val m_product: Product = Product
    private val m_productPrice: Float = ProductPrice
    private val m_quantity: Int = Quantity
    private val m_store: String = Store

    fun getProduct(): Product{
        return m_product
    }

    fun getProductPrice(): Float{
        return m_productPrice
    }

    fun getQuantity(): Int{
        return m_quantity
    }

    fun getStore(): String{
        return m_store
    }
}