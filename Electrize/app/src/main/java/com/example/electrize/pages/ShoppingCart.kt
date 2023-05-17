package com.example.electrize.pages

/**
 * @author Senne
 */

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.electrize.EnergeticScreen
import com.example.electrize.EnergeticViewModel
import com.example.electrize.R
import com.example.electrize.dataStructures.Product
import java.time.temporal.TemporalAmount
import com.example.electrize.ui.theme.ElectrizeTheme
import java.math.RoundingMode

@Composable
fun shoppingCart(
    viewModel: EnergeticViewModel,
    navController: NavController,
    modifier: Modifier = Modifier.fillMaxSize(),
    focusManager: FocusManager
) {
    var shoppingCart by remember {
        mutableStateOf(viewModel.findShoppingCartProducts())
    }
    var productCount by remember {
        mutableStateOf(shoppingCart.size)
    }
    if(productCount > 0){
        LazyColumn(verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxHeight().background(MaterialTheme.colorScheme.background)) {
            item{
                Row(){
                    AllShoppingCartProducts(shoppingCart = shoppingCart, viewModel = viewModel, update = {
                        focusManager.clearFocus()
                        shoppingCart.clear()
                        shoppingCart = viewModel.findShoppingCartProducts()
                        productCount = shoppingCart.size
                    })
                }
            }
            item{
                Row(){
                    Column(verticalArrangement = Arrangement.Bottom) {
                        TotalPrice(shoppingCart = shoppingCart)
                        PickRouteButton(navController, focusManager = focusManager)
                    }
                }
            }
        }
    } else {
        emptyMessage(navController)
    }
}

@Composable
private fun emptyMessage(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxHeight().background(MaterialTheme.colorScheme.background)
    ) {
        Spacer(modifier = Modifier.weight(0.4f))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            Image(
                painter = painterResource(id = R.drawable.cat_box),
                contentDescription = "",
                modifier = Modifier.size(190.dp)
            )
        }
        Spacer(modifier = Modifier.weight(0.1f))
        Row(
            modifier = Modifier.fillMaxWidth()
        ){
            Text(
                text = stringResource(id = R.string.empty_cart_header),
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(start = 50.dp, end = 50.dp)
                    .fillMaxWidth(),
                fontSize = 25.sp
            )
        }
        Spacer(modifier = Modifier.weight(0.1f))
        Row(
            modifier = Modifier.fillMaxWidth()
        ){
            Text(
                text = stringResource(id = R.string.empty_cart_message),
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(start = 50.dp, end = 50.dp),
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.weight(0.6f))
        Row(){
            ExtendedFloatingActionButton(
                onClick = { navController.navigate(EnergeticScreen.Products.name) },
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier
                    .padding(30.dp, 10.dp)
                    .weight(1.0f)
            ) {
                Text(
                    text = stringResource(id = R.string.go_to_products),
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                )
            }
        }
        Spacer(modifier = Modifier.weight(0.1f))
    }
}

@Composable
private fun AllShoppingCartProducts(
    shoppingCart: MutableMap<Product, Int>,
    viewModel: EnergeticViewModel,
    update: () -> Unit = {}
) {
    Column{
        shoppingCart.forEach() { (product, amount) ->
            shoppingCartProduct(
                product = product,
                amount = amount,
                onIncrease = {
                    viewModel.increaseShoppingCartProduct(product._productId)
                    update()
                },
                onDecrease = {
                    viewModel.decreaseShoppingCartProduct(product._productId)
                    update()
                },
            )
        }
    }
}

@Composable
fun shoppingCartProduct(
    product: Product,
    amount: Int,
    onIncrease: () -> Unit = {},
    onDecrease: () -> Unit = {},
    ){
    Surface(
        color = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface),
        shadowElevation = 8.dp,
        modifier = Modifier.padding(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column() {
                Image(
                    painter = painterResource(id = product._productImages.first()),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(120.dp)
                )
            }
            Column() {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = product._productName,
                        color = MaterialTheme.colorScheme.onSurface,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 10.dp),
                        fontSize = 18.sp
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxHeight()
                ) {
                    Column(
                        modifier = Modifier.fillMaxHeight()
                    ) {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
                            Column(){
                                Text(
                                    text = product.getPriceRange().first.toBigDecimal().setScale(2, RoundingMode.UP).toFloat().toString() + "€ * " + amount.toString() + " = " + (product.getPriceRange().first * amount).toBigDecimal().setScale(2, RoundingMode.UP).toFloat().toString() + "€",
                                    color = MaterialTheme.colorScheme.onSurface,
                                    overflow = TextOverflow.Ellipsis,
                                    maxLines = 1,
                                    modifier = Modifier.padding(start = 10.dp, bottom = 10.dp, top = 10.dp)
                                )
                            }

                            Column(
                                modifier = Modifier.padding(end = 10.dp)
                            ){
                                Row(){
                                    Surface(
                                        shape = CircleShape,
                                        color = MaterialTheme.colorScheme.surfaceTint,
                                        modifier = Modifier.padding(5.dp)
                                    ) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.remove_icon),
                                            contentDescription = "Decrease product quantity",
                                            modifier = Modifier
                                                .clickable(onClick = onDecrease),
                                            tint = MaterialTheme.colorScheme.onSurface,
                                        )
                                    }

                                    Text(
                                        text = amount.toString(),
                                        color = MaterialTheme.colorScheme.onSurface,
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.padding(5.dp)
                                    )

                                    Surface(
                                        shape = CircleShape,
                                        color = MaterialTheme.colorScheme.surfaceTint,
                                        modifier = Modifier.padding(5.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Add,
                                            contentDescription = "Increase product quantity",
                                            modifier = Modifier
                                                .clickable(onClick = onIncrease),
                                            tint = MaterialTheme.colorScheme.onSurface,
                                        )
                                    }
                                }
                            }
                        }
                        Text(
                            text = product.getPriceRange().second.toBigDecimal().setScale(2, RoundingMode.UP).toFloat().toString() + "€ * " + amount.toString() + " = " + (product.getPriceRange().second * amount).toBigDecimal().setScale(2, RoundingMode.UP).toFloat().toString() + "€",
                            color = MaterialTheme.colorScheme.onSurface,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
                            modifier = Modifier.padding(start = 10.dp, bottom = 10.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun TotalPrice(
    shoppingCart: MutableMap<Product, Int>
) {
    Row(
        modifier = Modifier
            .padding(30.dp, 10.dp)
    ) {
        var totalPriceRange = calculateTotalPriceRange(shoppingCart)
        Text(
            text = "Totale prijs",
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Left,
            modifier = Modifier.weight(0.3f)
        )
        Text(
            text = "€${totalPriceRange.first} - €${totalPriceRange.second}",
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Right,
            modifier = Modifier.weight(0.7f)
        )
    }
}

@Composable
private fun PickRouteButton(navController: NavController, focusManager: FocusManager) {
    Row(
        modifier = Modifier,
        verticalAlignment = Alignment.Bottom,
    ) {
        ExtendedFloatingActionButton(
            onClick = {
                focusManager.clearFocus()
                navController.navigate("${EnergeticScreen.CustomizeRoute}")
                      },
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            modifier = Modifier
                .padding(30.dp, 10.dp)
                .weight(1.0f),
        )
        {
            Text(
                text = "Kies uw route",
                color = MaterialTheme.colorScheme.onPrimaryContainer,
            )
        }
    }
}

private fun calculateTotalPriceRange(
    shoppingCart: Map<Product, Int>
) : Pair<Float, Float> {
    var minTotalPrice = 0.0f
    var maxTotalPrice = 0.0f
    shoppingCart.forEach{product ->
        minTotalPrice += product.key.getPriceRange().first * product.value
        maxTotalPrice += product.key.getPriceRange().second * product.value
    }

    return Pair(minTotalPrice.toBigDecimal().setScale(2, RoundingMode.UP).toFloat(), maxTotalPrice.toBigDecimal().setScale(2, RoundingMode.UP).toFloat())
}