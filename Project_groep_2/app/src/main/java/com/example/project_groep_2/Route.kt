package com.example.project_groep_2

import java.util.Date

class Route(year: Int, month: Int, day: Int, duration: Int, distance: Float, numberOfShops: Int, products: List<ProductOrder>) {
    private val m_orderedOnDate: Date = Date(year,month,day)
    private var m_duration: Int = duration
    private var m_distance: Float = distance
    private var m_numberOfShops: Int = numberOfShops
    private var m_products: List<ProductOrder> = products

    fun getDeliveryDate(): Date{
        return m_orderedOnDate
    }

    fun getOrderedOnDate(): Date{
        var date: Date = Date(m_orderedOnDate.year, m_orderedOnDate.month, m_orderedOnDate.day)
        date.date += 1
        return date
    }

    fun getDuration(): Int{
        return m_duration
    }

    fun getDistance(): Float {
        return m_distance
    }

    fun getTotalPrice(): Float{
        var totalPrice: Float = 0f
        for(item: ProductOrder in m_products){
            totalPrice += item.getProductPrice()
        }
        return totalPrice
    }

    fun getNumberOfshops(): Int {
        return m_numberOfShops
    }

    fun getProducts(): List<ProductOrder> {
        return m_products
    }
}