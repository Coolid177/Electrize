package com.example.electrize.dataStructures

import android.provider.ContactsContract.Data
import com.example.electrize.data.DataSource
import com.example.electrize.pages.product

class BoughtProduct(productId: Int, storeId: Int, quantity: Int) {
    var _productId: Int = productId
    var _storeId: Int = storeId
    var _quantity: Int = quantity

    //Check if this is done right with null safety
    fun getBoughtProductTotalPrice(): Float {
        var product: Product?
        DataSource.findProduct(_productId).let {
            product = it
        }

        if (product == null) {
            return 0.0f
        }


        product!!._pricingList.forEach{ pricePair ->
            if (pricePair.first == _storeId){
                return pricePair.second * _quantity
            }
        }

        return 0.0f
    }
}