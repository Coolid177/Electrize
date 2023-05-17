package com.example.electrize.dataStructures

class ShoppingCart(productAmountList: MutableMap<Int, Int> ){
    //Key: ProductId
    //Value: Amount of one product
    var _productAmountList: MutableMap<Int, Int> = productAmountList;

    fun addProduct(productId: Int, productQuantity: Int){
        _productAmountList[productId] = productQuantity
    }
}