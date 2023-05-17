package com.example.electrize.pages

/**
 * @author Lukas
 */

import android.annotation.SuppressLint
import androidx.cardview.widget.CardView
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.electrize.EnergeticScreen
import com.example.electrize.EnergeticViewModel
import com.example.electrize.R
import com.example.electrize.components.fastActionMenu
import com.example.electrize.components.header
import com.example.electrize.data.DataSource
import com.example.electrize.dataStructures.Product
import com.example.electrize.dataStructures.theme
import java.math.RoundingMode
import java.time.format.TextStyle

@Composable
fun home(viewModel: EnergeticViewModel, navController: NavController, focusManager: FocusManager) {
    var homePageProducts: List<List<Product>> = mutableListOf(DataSource.Products.toList(),DataSource.Products.toList(),DataSource.Products.toList(),DataSource.Products.toList())
    displayRows(homePageProducts, navController, focusManager)
}

@Composable
private fun displayRows(valuesList: List<List<Product>>, navController: NavController, focusManager: FocusManager) {
    var headers : Array<String> = stringArrayResource(id = R.array.homepage_categories)
    var prices: List<Float> = listOf(349.99f, 199.99f, 599.99f, 299.99f, 99.95f, 500.99f, 150.99f, 199.99f)
    LazyColumn(modifier = Modifier
        .background(MaterialTheme.colorScheme.background)
        .padding(start = 10.dp)
        .fillMaxSize()){
        item{
            Column(){
                Text(
                    text = headers[0],
                    fontSize = 25.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(8.dp)
                )
                LazyRow(
                    modifier = Modifier.fillMaxSize()
                ){
                    DataSource.Products.take(8).forEach{product->
                        item {
                            Surface(
                                color = Color.White,
                                shape = RoundedCornerShape(8.dp),
                                border = BorderStroke(1.dp, Color.LightGray),
                                shadowElevation = 8.dp,
                                modifier = Modifier.clickable {
                                    focusManager.clearFocus()
                                    navController.navigate("${EnergeticScreen.Products.name}/${product._productId}")
                                }
                            ) {
                                Column(
                                    modifier = Modifier
                                        .border(
                                            width = 1.dp,
                                            color = Color.Black,
                                        )
                                        .padding(5.dp)
                                ) {
                                    Image(
                                        painter = painterResource(
                                            id = product._productImages.first()
                                        ),
                                        contentDescription = "",
                                        contentScale = ContentScale.Fit,
                                        modifier = Modifier
                                            .size(140.dp)
                                    )
                                    Text(
                                        text = product._productName,
                                        overflow = TextOverflow.Ellipsis,
                                        maxLines = 1,
                                        modifier = Modifier.width(140.dp)
                                    )
                                    Text(
                                        text = product.getPriceRange().first.toString() + "€" + " - " + product.getPriceRange().second.toString() + "€",
                                        overflow = TextOverflow.Ellipsis,
                                        maxLines = 1,
                                        modifier = Modifier
                                            .width(140.dp)
                                            .padding(top = 8.dp)
                                    )
                                }
                            }
                        }
                        item{
                            Divider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 10.dp))
                        }
                    }
                }
            }
        }
        item{
            Column(){
                Text(
                    text = headers[1],
                    fontSize = 25.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(8.dp)
                )
                LazyRow(
                    modifier = Modifier.fillMaxSize()
                ){
                    DataSource.Categories.take(8).forEachIndexed{index, category->
                        item {
                            Surface(
                                color = Color.White,
                                shape = RoundedCornerShape(8.dp),
                                border = BorderStroke(1.dp, Color.LightGray),
                                shadowElevation = 8.dp,
                                modifier = Modifier.clickable {
                                    focusManager.clearFocus()
                                    navController.navigate("${EnergeticScreen.CategoryProducts.name}/${category._categoryId}")
                                }
                            ) {
                                Column(
                                    modifier = Modifier
                                        .border(
                                            width = 1.dp,
                                            color = Color.Black,
                                        )
                                        .padding(5.dp)
                                ) {
                                    Image(
                                        painter = painterResource(
                                            id = category._imageId
                                        ),
                                        contentDescription = "",
                                        contentScale = ContentScale.Fit,
                                        modifier = Modifier
                                            .size(140.dp)
                                    )
                                    Text(
                                        text = category._categoryName,
                                        overflow = TextOverflow.Ellipsis,
                                        maxLines = 1,
                                        modifier = Modifier.width(140.dp)
                                    )
                                    Text(
                                        text = "< " + prices[index].toString() + "€",
                                        overflow = TextOverflow.Ellipsis,
                                        maxLines = 1,
                                        modifier = Modifier.width(140.dp)
                                            .padding(top = 8.dp)
                                    )
                                }
                            }
                        }
                        item{
                            Divider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 10.dp))
                        }
                    }
                }
            }
        }
        item{
            Column(){
                Text(
                    text = headers[2],
                    fontSize = 25.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(8.dp)
                )
                LazyRow(
                    modifier = Modifier.fillMaxSize()
                ){
                    DataSource.Products.takeLast(8).forEach{product->
                        item {
                            Surface(
                                color = Color.White,
                                shape = RoundedCornerShape(8.dp),
                                border = BorderStroke(1.dp, Color.LightGray),
                                shadowElevation = 8.dp,
                                modifier = Modifier.clickable {
                                    focusManager.clearFocus()
                                    navController.navigate("${EnergeticScreen.Products.name}/${product._productId}")
                                }
                            ) {
                                Column(
                                    modifier = Modifier
                                        .border(
                                            width = 1.dp,
                                            color = Color.Black,
                                        )
                                        .padding(5.dp)
                                ) {
                                    Image(
                                        painter = painterResource(
                                            id = R.drawable.discount_mouse
                                        ),
                                        contentDescription = "",
                                        contentScale = ContentScale.Fit,
                                        modifier = Modifier
                                            .size(140.dp)
                                    )
                                    Text(
                                        text = product._productName,
                                        overflow = TextOverflow.Ellipsis,
                                        maxLines = 1,
                                        modifier = Modifier.width(140.dp)
                                    )
                                    Text(
                                        text = (product.getPriceRange().first + product.getPriceRange().first * 0.25).toBigDecimal().setScale(2, RoundingMode.UP).toFloat().toString() + "€" + " - " + (product.getPriceRange().second + product.getPriceRange().second * 0.25).toBigDecimal().setScale(2, RoundingMode.UP).toFloat().toString() + "€",
                                        style = androidx.compose.ui.text.TextStyle(
                                            textDecoration = TextDecoration.LineThrough
                                        )
                                    )
                                    Text(
                                        text = product.getPriceRange().first.toString() + "€" + " - " + product.getPriceRange().second.toString() + "€",
                                        color = Color.Red,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                        item{
                            Divider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 10.dp))
                        }
                    }
                }
            }
        }
        item{
            Column(){
                Text(
                    text = headers[3],
                    fontSize = 25.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(8.dp)
                )
                LazyRow(
                    modifier = Modifier.fillMaxSize()
                ){
                    DataSource.Products.takeLast(16).take(8).forEach{product->
                        item {
                            Surface(
                                color = Color.White,
                                shape = RoundedCornerShape(8.dp),
                                border = BorderStroke(1.dp, Color.LightGray),
                                shadowElevation = 8.dp,
                                modifier = Modifier.clickable {
                                    focusManager.clearFocus()
                                    navController.navigate("${EnergeticScreen.Products.name}/${product._productId}")
                                }
                            ) {
                                Column(
                                    modifier = Modifier
                                        .border(
                                            width = 1.dp,
                                            color = Color.Black,
                                        )
                                        .padding(5.dp)
                                ) {
                                    Image(
                                        painter = painterResource(
                                            id = product._productImages.first()
                                        ),
                                        contentDescription = "",
                                        contentScale = ContentScale.Fit,
                                        modifier = Modifier
                                            .size(140.dp)
                                    )
                                    Text(
                                        text = product._productName,
                                        overflow = TextOverflow.Ellipsis,
                                        maxLines = 1,
                                        modifier = Modifier.width(140.dp)
                                    )
                                    Text(
                                        text = product.getPriceRange().first.toString() + "€" + " - " + product.getPriceRange().second.toString() + "€",
                                        overflow = TextOverflow.Ellipsis,
                                        maxLines = 1,
                                        modifier = Modifier
                                            .width(140.dp)
                                            .padding(top = 8.dp)
                                    )
                                }
                            }
                        }
                        item {
                            Divider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 10.dp))
                        }
                    }
                }
            }
        }
    }
}