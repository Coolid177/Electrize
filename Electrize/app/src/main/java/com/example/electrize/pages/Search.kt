package com.example.electrize.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.electrize.EnergeticScreen
import com.example.electrize.R
import com.example.electrize.data.DataSource
import com.example.electrize.dataStructures.Category
import com.example.electrize.dataStructures.Product

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun search(navController: NavController, focusManager: FocusManager) {
    var products: List<Product> = DataSource.Products.toList()
    var filterVisibility = remember {
        mutableStateOf(false)
    }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {filterVisibility.value = !filterVisibility.value}) {
                androidx.compose.material.Icon(
                    painter = painterResource(id = R.drawable.filter),
                    contentDescription = "toggle filter menu"
                )
            }
        }
    ){
        filterMenu(filterVisibility, focusManager)
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            generateChildRow(DataSource.Categories.take(10), this, navController = navController, focusManager = focusManager)
            generateColumnChildren(products, this, navController = navController, focusManager = focusManager)
        }
    }
}

private fun generateChildRow(values: List<Category>, scope: LazyListScope, navController: NavController, focusManager: FocusManager) {
    scope.item {
        LazyRow() {
            items(values) {value ->
                Surface(
                    color = Color.White,
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.dp, Color.LightGray),
                    shadowElevation = 8.dp,
                    modifier = Modifier.padding(8.dp).clickable {
                        focusManager.clearFocus()
                        navController.navigate("${EnergeticScreen.CategoryProducts.name}/${value._categoryId}")
                    }
                ) {
                    Column() {
                        Image(
                            painter = painterResource(id = value._imageId),
                            contentDescription = "product",
                            modifier = Modifier
                                .size(140.dp))
                        Text(
                            text = value._categoryName,
                            color = Color.Black,
                            modifier = Modifier.fillMaxSize()
                                                .padding(start = 5.dp)
                                                .width(140.dp),
                            maxLines = 1
                        )
                    }
                }
            }
        }
    }
}

private fun generateColumnChildren(values: List<Product>, scope: LazyListScope, navController: NavController, focusManager: FocusManager) {
    scope.items(values) { value ->
        Surface(
            color = Color.White,
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, Color.LightGray),
            shadowElevation = 8.dp,
            modifier = Modifier
                .clickable {
                    focusManager.clearFocus()
                    navController.navigate("${EnergeticScreen.Products.name}/${value._productId}")
                }
                .padding(vertical = 8.dp, horizontal = 8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ){
                Column(){
                    Image(
                        painter = painterResource(id = value._productImages.first()),
                        contentDescription = "",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.size(140.dp)
                    )
                }
                Column(
                    modifier = Modifier.padding(10.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(start = 8.dp)
                    ){
                        Text(
                            text = value._productName,
                            color = Color.Black,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
                            modifier = Modifier.fillMaxWidth(),
                            fontSize = 18.sp
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(start = 8.dp)
                    ){
                        Text(
                            text = value.getPriceRange().first.toString() + "€" + " - " + value.getPriceRange().second.toString() + "€",
                            color = Color.Black,
                            fontSize = 14.sp
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(10.dp)
                    ){
                        //sterretjes
                        for(i in 1..averageReview(value)){
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_star_rate_24),
                                contentDescription = "",
                                tint = Color(0xFFFFC107)
                            )
                        }
                        for(i in averageReview(value)..4){
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_star_rate_24),
                                contentDescription = "",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }
    }
}

private fun averageReview(product: Product): Int{
    var sum: Float = 0.0f
    product._reviewList.forEach {
        sum += it._reviewRating
    }
    return (sum/product._reviewList.size).toInt()
}
