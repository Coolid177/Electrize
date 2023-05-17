package com.example.project_groep_2

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.project_groep_2.ui.theme.Project_groep_2Theme
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun PreviewShoppingCartPage() {
    Project_groep_2Theme {
        Surface {
            //ShoppingCartPage(productList = listOf(muis1, muis2, muis3, muis4, muis5), amountList = listOf(2, 1, 4, 3, 2))
        }
    }
}

@Composable
fun ShoppingCartPage(
    productList: List<Product1>,
    amountList: List<Int>,
    modifier: Modifier = Modifier
        .fillMaxSize()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        AllShoppingCartProducts(productList = productList, amountList = amountList)
        TotalPrice(productList = productList, amountList = amountList)
        PickRouteButton()
    }
}

@Composable
fun AllShoppingCartProducts(
    productList: List<Product1>,
    amountList: List<Int>,
) {
    Column(
        modifier = Modifier
            .padding(15.dp, 10.dp)
    ) {
        productList.forEachIndexed { index, product ->
            ShoppingCartProduct(product = product, amount = amountList[index])
        }
    }
}

@Composable
fun TotalPrice(
    productList: List<Product1>,
    amountList: List<Int>,
) {
    Row(
        modifier = Modifier
            .padding(30.dp, 10.dp)
    ) {
        var totalPriceRange = CalculateTotalPriceRange(productList, amountList)
        Text(
            text = "Totale prijs",
            textAlign = TextAlign.Left,
            modifier = Modifier.weight(0.3f)
        )
        Text(
            text = "€${totalPriceRange.first} - €${totalPriceRange.second}",
            textAlign = TextAlign.Right,
            modifier = Modifier.weight(0.7f)
        )
    }
}

@Composable
fun PickRouteButton() {
    Row(
        modifier = Modifier,
        verticalAlignment = Alignment.Bottom,
    ) {
        ExtendedFloatingActionButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(30.dp, 10.dp)
                .weight(1.0f),
            )
        {
            Text(text = "Kies uw route")
        }
    }
}

fun CalculateTotalPriceRange(
    productList: List<Product1>,
    amountList: List<Int>,
) : Pair<Float, Float> {
    var minTotalPrice = 0.0f
    var maxTotalPrice = 0.0f
    productList.forEachIndexed { index, product ->
        var minProductPrice = getCheapestProductPrice(product)
        if (minProductPrice == null) {
            minProductPrice = 0.0f
        }

        var maxProductPrice = getMostExpensiveProductPrice(product)
        if (maxProductPrice == null) {
            maxProductPrice = 0.0f
        }

        var amount = amountList[index]

        minTotalPrice += (minProductPrice * amount)
        maxTotalPrice += (maxProductPrice * amount)

    }

    return Pair<Float, Float>(minTotalPrice, maxTotalPrice)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingCartProduct(
    product: Product1,
    amount: Int,
) {
    var value by remember {
        mutableStateOf("")
    }

    Row (
        modifier = Modifier
    ) {
        //Product image
        Image(
            painter = painterResource(id = product.productImages.first()),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .weight(1f),
        )

        //Product name and price
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .weight(2f),
        ) {
            Text(text = product.productName)
            Text(text = "${getCheapestProductPrice(product)} - ${getMostExpensiveProductPrice(product)}")
        }

        //+ - buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .weight(3f),
        ) {
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .weight(1f),
            ) {
                Icon(
                    painterResource(id = R.drawable.remove_icon),
                    "Verlaag hoeveelheid in winkelkar"
                )
            }

            TextField(
                value = value,
                onValueChange = { newText ->
                value = newText
                },
                modifier = Modifier
                    .weight(1f)
                )

            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .weight(1f)
            ) {
                Icon(
                    Icons.Default.Add,
                    "Verhoog hoeveelheid in winkelkar"
                )
            }
        }
    }
}

fun getCheapestProductPrice(product: Product1): Float? {
    var minValue: Float? = product.productPricesValues.first().toFloatOrNull()
    if (minValue == null) {
        //Throw error
    }
    product.productPricesValues.forEach {
        var currentValue: Float? = it.toFloatOrNull()
        if (currentValue == null){
            //Throw error
        }

        minValue = kotlin.math.min(minValue!!, currentValue!!)
    }
    return minValue
}

fun getMostExpensiveProductPrice(product: Product1): Float? {
    var maxValue: Float? = product.productPricesValues.first().toFloatOrNull()
    if (maxValue == null) {
        //Throw error
    }
    product.productPricesValues.forEach {
        var currentValue: Float? = it.toFloatOrNull()
        if (currentValue == null){
            //Throw error
        }

        maxValue = kotlin.math.max(maxValue!!, currentValue!!)
    }
    return maxValue
}