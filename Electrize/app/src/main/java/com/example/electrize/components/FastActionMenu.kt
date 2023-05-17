package com.example.electrize.components

/**
 * @author Lukas
 */

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.electrize.R
import androidx.compose.material3.Typography
import androidx.compose.material.IconButton
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.electrize.EnergeticScreen


@Composable
fun fastActionMenu(
    onProductButtonClicked: () -> Unit = {},
    onHomeButtonClicked: () -> Unit = {},
    onRoutesButtonClicked: () -> Unit = {},
    onShoppingCartButtonClicked:() -> Unit = {},
    navController: NavController
    ) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 0.dp)
            .height(55.dp)
            .background(MaterialTheme.colorScheme.background)
    ){
        BottomNavigationItem(
            selected = navBackStackEntry?.destination?.route == EnergeticScreen.Home.name,
            onClick = onHomeButtonClicked,
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_home_24),
                    contentDescription = "Home",
                    modifier = Modifier.size(30.dp),
                    tint = if(navBackStackEntry?.destination?.route == EnergeticScreen.Home.name) MaterialTheme.colorScheme.surfaceTint else MaterialTheme.colorScheme.onBackground
                )
            },
            label = {
                Text(
                    text = "Home",
                    //style = MaterialTheme.typography.button
                )
            },
            selectedContentColor = MaterialTheme.colorScheme.surfaceTint,
            unselectedContentColor = MaterialTheme.colorScheme.onBackground
        )

        BottomNavigationItem(
            selected = navBackStackEntry?.destination?.route  == EnergeticScreen.Products.name,
            onClick = onProductButtonClicked,
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.package_box),
                    contentDescription = "Products",
                    modifier = Modifier.size(30.dp),
                    tint = if(navBackStackEntry?.destination?.route == EnergeticScreen.Products.name) MaterialTheme.colorScheme.surfaceTint else MaterialTheme.colorScheme.onBackground
                )
            },
            label = {
                Text(
                    text = "Producten",
                    //style = MaterialTheme.typography.button
                )
            },
            selectedContentColor = MaterialTheme.colorScheme.surfaceTint,
            unselectedContentColor = MaterialTheme.colorScheme.onBackground
        )

        BottomNavigationItem(
            selected = navBackStackEntry?.destination?.route  == EnergeticScreen.Routes.name,
            onClick = onRoutesButtonClicked,
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_pin_drop_24),
                    contentDescription = "Routes",
                    modifier = Modifier.size(30.dp),
                    tint = if(navBackStackEntry?.destination?.route == EnergeticScreen.Routes.name) MaterialTheme.colorScheme.surfaceTint else MaterialTheme.colorScheme.onBackground
                )
            },
            label = {
                Text(
                    text = "Routes",
                    //style = MaterialTheme.typography.button
                )
            },
            selectedContentColor = MaterialTheme.colorScheme.surfaceTint,
            unselectedContentColor = MaterialTheme.colorScheme.onBackground
        )

        BottomNavigationItem(
            selected = navBackStackEntry?.destination?.route == EnergeticScreen.ShoppingCart.name,
            onClick = onShoppingCartButtonClicked,
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_shopping_cart_24),
                    contentDescription = "Home",
                    modifier = Modifier.size(30.dp),
                    tint = if(navBackStackEntry?.destination?.route == EnergeticScreen.ShoppingCart.name) MaterialTheme.colorScheme.surfaceTint else MaterialTheme.colorScheme.onBackground
                )
            },
            label = {
                Text(
                    text = "Mandje",
                    //style = MaterialTheme.typography.button
                )
            },
            selectedContentColor = MaterialTheme.colorScheme.surfaceTint,
            unselectedContentColor = MaterialTheme.colorScheme.onBackground
        )
    }
}