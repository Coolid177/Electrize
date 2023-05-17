package com.example.electrize.data

import androidx.compose.ui.res.painterResource
import com.example.electrize.EnergeticScreen
import com.example.electrize.R
import com.example.electrize.dataStructures.Account
import com.example.electrize.dataStructures.Address
import com.example.electrize.dataStructures.BoughtProduct
import com.example.electrize.dataStructures.Compare
import com.example.electrize.dataStructures.Product
import com.example.electrize.dataStructures.Review
import com.example.electrize.dataStructures.Route
import com.example.electrize.dataStructures.RouteType
import com.example.electrize.dataStructures.Settings
import com.example.electrize.dataStructures.ShoppingCart
import com.example.electrize.dataStructures.theme
import java.util.Date

class CurrentInstanceData(
    account: Account = Account(
        accountId = 0,
        firstName = "Emily",
        lastName = "Lee",
        email = "emilylee@gmail.com",
        phoneNumber = "+32 479 12 34 56",
        addressId = Address(
            addressLine1 = "Dorpsstraat 1",
            addressLine2 = "1B",
            city = "Hasselt",
            postalCode = "3500"
        ),
        imageId = R.drawable.profile_picture_man
    ),
    reviews: List<Review> = listOf<Review>(),
    settings: Settings = Settings("Nederlands", theme.DARK),
    routes: MutableList<Int> = mutableListOf<Int>(
        0,
        1,
        2
    )
){
    var _account: Account = account
    var _settings: Settings = settings
    var _shoppingCart: ShoppingCart = ShoppingCart(
        mutableMapOf(
        3 to 3,
        1 to 4
    )
    )
    var _currentRouteId: Int = 1
    var _compare: Compare = Compare(mutableListOf<Int>(10, 7, 8))
    var _routes: MutableList<Int> = routes


    //Login
    var _logged_in: Boolean = false
    var _destined_page: EnergeticScreen = EnergeticScreen.Home

    //Review
    var _currentProductid: Int = 0
    var _tempRoute: Route? = null
}