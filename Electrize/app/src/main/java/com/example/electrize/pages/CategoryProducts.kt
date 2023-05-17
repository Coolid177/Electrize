package com.example.electrize.pages

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.electrize.dataStructures.Product

@Composable
fun categoryProducts(categoryId: Int, navController: NavController, focusManager: FocusManager){
    var products = DataSource.getProductsBelongingToCategory(categoryId)
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(8.dp)
    ){
        products.forEach { product -> 
            item{
                productElement(product = product, navController = navController, focusManager = focusManager)
            }    
        }
    }
}

@Composable
private fun productElement(product: Product, navController: NavController, focusManager: FocusManager){
    Surface(
        color = Color.White,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color.LightGray),
        shadowElevation = 8.dp,
        modifier = Modifier
            .clickable {
                focusManager.clearFocus()
                navController.navigate("${EnergeticScreen.Products.name}/${product._productId}")
            }
            .padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ){
            Column(){
                Image(
                    painter = painterResource(id = product._productImages.first()),
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
                        text = product._productName,
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
                        text = product.getPriceRange().first.toString() + "€" + " - " + product.getPriceRange().second.toString() + "€",
                        fontSize = 14.sp
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth().padding(10.dp)
                ){
                    //sterretjes
                    for(i in 1..averageReview(product)){
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_star_rate_24),
                            contentDescription = "",
                            tint = Color(0xFFFFC107)
                        )
                    }
                    for(i in averageReview(product)..4){
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

private fun averageReview(product: Product): Int{
    var sum: Float = 0.0f
    product._reviewList.forEach {
        sum += it._reviewRating
    }
    return (sum/product._reviewList.size).toInt()
}
