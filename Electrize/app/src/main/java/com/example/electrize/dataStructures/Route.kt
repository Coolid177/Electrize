package com.example.electrize.dataStructures

import com.example.electrize.EnergeticViewModel
import com.example.electrize.data.CurrentInstanceData
import com.example.electrize.data.DataSource
import java.sql.Time
import java.util.Date

enum class RouteType(name: String) {
    Shortest("Shortest"),
    Cheapest("Cheapest"),
    Custom("Custom")
}

class Route(routeId: Int, routeType: RouteType, routePaymentDate: Date, routeCollectionDate: Date, routeAccountId: Int, routeProducts: List<BoughtProduct>){
    var _routeId: Int = routeId;
    var _routeType: RouteType = routeType;
    var _routePaymentDate: Date = routePaymentDate;
    var _routeCollectionDate: Date = routeCollectionDate;
    var _routeAccountId: Int = routeAccountId;

    //Key: ProductId
    //Value: StoreId
    var _routeProducts: List<BoughtProduct> = routeProducts;

    fun getRouteDuration() : Time {
        return Time(0,0,0)
    }

    fun getRouteDistance(): Float{
        return 0.0f
    }

    fun getRoutePrice(): Float{
        var routePrice: Float = 0.0f

        _routeProducts.forEach { boughtProduct ->
            routePrice += boughtProduct.getBoughtProductTotalPrice()
        }

        return routePrice
    }
}