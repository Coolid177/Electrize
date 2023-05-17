package com.example.electrize.dataStructures

class Compare(productsIds: MutableList<Int>) {
    var _productIds = productsIds;

    fun addToCompare(productId: Int): Boolean {
        if (_productIds.contains(productId)){
            return false;
        }
        _productIds.add(productId)
        return true;
    }

    fun removeFromCompare(productId: Int) {
        _productIds.remove(productId)
    }
}