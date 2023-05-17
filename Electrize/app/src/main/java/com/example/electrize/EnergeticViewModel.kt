package com.example.electrize

import androidx.lifecycle.ViewModel
import com.example.electrize.data.CurrentInstanceData
import com.example.electrize.data.DataSource
import com.example.electrize.dataStructures.Account
import com.example.electrize.dataStructures.CollectionRequest
import com.example.electrize.dataStructures.ContactRequest
import com.example.electrize.dataStructures.Product
import com.example.electrize.dataStructures.Route
import com.example.electrize.dataStructures.theme
import java.util.Calendar

class EnergeticViewModel : ViewModel() {
    var _currentData: CurrentInstanceData = CurrentInstanceData()

    fun findShoppingCartProducts() : MutableMap<Product, Int>{
        var products: MutableMap<Product, Int> = mutableMapOf<Product, Int>()
        _currentData._shoppingCart._productAmountList.forEach{item->
            DataSource.findProduct(item.key)?.let{products.put(it, item.value)}
        }
        return products
    }

    fun getContacts(): List<Account>{
        var accounts: MutableList<Account> = mutableListOf()
        DataSource.Contacts.forEach { item ->
            if(_currentData._account._accountId == item._firstAccountId){
                DataSource.findAccount(item._secondAccountId)?.let { accounts.add(it) }
            } else if (_currentData._account._accountId == item._secondAccountId){
                DataSource.findAccount(item._firstAccountId)?.let { accounts.add(it) }
            }
        }
        return accounts
    }

    fun switchTheme(){
        if(_currentData._settings._theme == theme.DARK){
            _currentData._settings._theme = theme.LIGHT
        } else {
            _currentData._settings._theme = theme.DARK
        }
    }

    fun isDarkTheme() : Boolean{
        return _currentData._settings._theme == theme.DARK
    }

    fun getOutgoingFriendRequests(): List<Account>{
        var outgoingRequests: MutableList<Account> = mutableListOf()
        DataSource.ContactRequests.forEach {item->
            if(item._senderId == _currentData._account._accountId){
                DataSource.findAccount(item._receiverId)?.let{outgoingRequests.add(it)}
            }
        }
        return outgoingRequests
    }

    fun getIncomingFriendRequests(): List<Account>{
        var incomingRequests: MutableList<Account> = mutableListOf()
        DataSource.ContactRequests.forEach {item->
            if(item._receiverId == _currentData._account._accountId){
                DataSource.findAccount(item._senderId)?.let{incomingRequests.add(it)}
            }
        }
        return incomingRequests
    }

    fun getOutgoingCollectionRequest(): List<CollectionRequest>{
        var outgoingRequests: MutableList<CollectionRequest> = mutableListOf()
        DataSource.CollectionRequest.forEach {item->
            if(item._senderId == _currentData._account._accountId){
                outgoingRequests.add(item)
            }
        }
        return outgoingRequests
    }

    fun getIncomingCollectionRequest(): List<CollectionRequest>{
        var incomingRequests: MutableList<CollectionRequest> = mutableListOf()
        DataSource.CollectionRequest.forEach { item->
            if(item._receiverId == _currentData._account._accountId){
                incomingRequests.add(item)
            }
        }
        return incomingRequests
    }

    fun getPresentRoutes(): List<Route>{
        var presentRoutes: MutableList<Route> = mutableListOf()
        _currentData._routes.forEach { route->
            var route = DataSource.findRoute(route)
            if (route != null) {
                if(Calendar.DAY_OF_MONTH == route._routeCollectionDate.day && Calendar.YEAR == route._routeCollectionDate.year && Calendar.MONTH == route._routeCollectionDate.month){
                    presentRoutes.add(route)
                }
            }
        }
        return presentRoutes
    }

    fun getFutureRoutes(): List<Route>{
        var futureRoutes: MutableList<Route> = mutableListOf()
        _currentData._routes.forEach { route ->
            var route = DataSource.findRoute(route)
            if (route != null) {
                if (!(Calendar.DAY_OF_MONTH == route._routeCollectionDate.day && Calendar.YEAR == route._routeCollectionDate.year && Calendar.MONTH == route._routeCollectionDate.month) and !(Calendar.DAY_OF_MONTH < route._routeCollectionDate.day && Calendar.YEAR <= route._routeCollectionDate.year && Calendar.MONTH <= route._routeCollectionDate.month)) {
                    futureRoutes.add(route)
                }
            }
        }
        return futureRoutes
    }

    fun getPastRoutes(): List<Route>{
        var pastRoutes: MutableList<Route> = mutableListOf()
        _currentData._routes.forEach { route ->
            var route = DataSource.findRoute(route)
            if (route != null) {
                if (Calendar.DAY_OF_MONTH < route._routeCollectionDate.day && Calendar.YEAR <= route._routeCollectionDate.year && Calendar.MONTH <= route._routeCollectionDate.month) {
                    pastRoutes.add(route)
                }
            }
        }
        return pastRoutes
    }

    fun increaseShoppingCartProduct(productId: Int){
        var previous = _currentData._shoppingCart._productAmountList.get(productId)
        previous = previous!! + 1
        _currentData._shoppingCart._productAmountList.put(productId, previous!!)
    }

    fun decreaseShoppingCartProduct(productId: Int){
        var previous = _currentData._shoppingCart._productAmountList.get(productId)
        previous = previous!! - 1
        if(previous == 0){
            _currentData._shoppingCart._productAmountList.remove(productId!!)
        }
        else {
            _currentData._shoppingCart._productAmountList.put(productId, previous!!)
        }
    }

    fun findCurrentRoute(): Route?{
        var currentRouteId:Int = _currentData._currentRouteId
        return DataSource.findRoute(currentRouteId)
    }
    fun findCompareProducts(): List<Product> {
        var products: MutableList<Product> = mutableListOf<Product>()
        _currentData._compare._productIds.forEach{item->
            DataSource.findProduct(item)?.let { products.add(it)}
        }
        return products;
    }

    fun removeFromCompare(product: Product) {
        _currentData._compare.removeFromCompare(product._productId)
    }
    
    fun addProductToCart(productId: Int){
        _currentData._shoppingCart.addProduct(productId, 1)
    }

    fun addOutgoingFriendrequest(accountId: Int){
        DataSource.ContactRequests.add(ContactRequest(_currentData._account._accountId, accountId))
    }

    fun addForeignRoute(routeId: Int){
        _currentData._routes.add(routeId)
    }

    fun setTempRoute(tempRoute: Route) {
        _currentData._tempRoute = Route(
            tempRoute._routeId,
            tempRoute._routeType,
            tempRoute._routePaymentDate,
            tempRoute._routeCollectionDate,
            tempRoute._routeAccountId,
            tempRoute._routeProducts
        )
    }

    fun clearTempRoute() {
        _currentData._tempRoute  = null
    }

    fun getTempRoute(): Route? {
        return _currentData._tempRoute
    }
}