package com.example.electrize.dataStructures

import android.location.Address
import java.net.Inet4Address
import java.util.Date

class Product(productId: Int, productName: String, rating: Float, specList: List<Pair<String, String>>, pricingList: List<Pair<Int, Float>>, reviewList: MutableList<Review>, productBrandId: Int, productCategoryId: Int, productImages: List<Int>) {
    var _productId: Int = productId;
    var _productName: String = productName;
    var _rating: Float = rating;
    var _specList: List<Pair<String, String>> = specList;
    //storeId
    //storePrice
    var _pricingList: List<Pair<Int, Float>> = pricingList;
    var _reviewList: MutableList<Review> = reviewList;
    var _productBrandId: Int = productBrandId;
    var _productCategory: Int = productCategoryId;
    var _productImages: List<Int> = productImages;

    fun getLowestProductPrice() : Float{
        var lowestPrice: Float = _pricingList.first().second
        _pricingList.forEach {price ->
            if (price.second < lowestPrice){
                lowestPrice = price.second
            }
        }
        return lowestPrice
    }

    fun getHighestProductPrice(): Float{
        var highestPrice: Float = 0.0f
        _pricingList.forEach { price ->
            if(price.second > highestPrice){
                highestPrice = price.second
            }
        }
        return highestPrice
    }

    fun getPriceRange(): Pair<Float, Float> {
        var lowestPrice: Float = 0.0f
        var highestPrice: Float = 0.0f
        _pricingList.forEach { price ->
            if (price.second < lowestPrice) {
                lowestPrice = price.second
            } else if (price.second > highestPrice) {
                highestPrice = price.second
            }
        }
        return Pair<Float, Float>(lowestPrice, highestPrice)
    }
}