package com.example.electrize.pages

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonElevation
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.util.lerp
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.electrize.EnergeticScreen
import com.example.electrize.EnergeticViewModel
import com.example.electrize.R
import com.example.electrize.data.DataSource
import com.example.electrize.dataStructures.Product
import com.example.electrize.ui.theme.ElectrizeTheme
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun product(
    viewModel: EnergeticViewModel,
    navController: NavController,
    productId: Int,
    focusManager: FocusManager
) {
    Scaffold(
        floatingActionButton = {
            Row(){
                FloatingActionButton(onClick = {
                    focusManager.clearFocus()
                    viewModel._currentData._compare.addToCompare(productId)//Add the product to the compare list
                    navController.navigate("${EnergeticScreen.Compare}") //Navigate to the compare page
                   },
                    modifier = Modifier.padding(end = 10.dp)) {
                    Icon(painter = painterResource(id = R.drawable.compare_add), contentDescription = "", Modifier.size(24.dp))
                }
                FloatingActionButton(onClick = {
                    viewModel.addProductToCart(productId)
                    navController.navigate(EnergeticScreen.ShoppingCart.name)
                    focusManager.clearFocus()
                }){
                    Icon(painter = painterResource(id = R.drawable.baseline_add_shopping_cart_24), contentDescription = "", Modifier.size(24.dp))
                }
            }
        },
        modifier = Modifier.fillMaxHeight()
    ){
        var product: Product? = DataSource.findProduct(productId = productId)
        LazyColumn(){
            item{
                productImages(productImages = product!!._productImages, focusManager)
                productTitle(title = product!!._productName)
                collapsableMenu(title = stringResource(R.string.specifications), focusManager = focusManager, content = {
                    Surface(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = RoundedCornerShape(8.dp),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurfaceVariant),
                        shadowElevation = 8.dp,
                        modifier = Modifier.padding(top = 4.dp, start = 8.dp, end = 8.dp, bottom = 8.dp)
                    ){
                        Column(){
                            product._specList.forEach{
                                Row(
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .fillMaxWidth()
                                ) {
                                    Text(
                                        text = it.first,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    )
                                    Text(
                                        text = it.second,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    )
                                }
                            }
                        }
                    }
                }
                )
                collapsableMenu(title = stringResource(id = R.string.pricing), focusManager = focusManager,  content = {
                    Surface(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = RoundedCornerShape(8.dp),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurfaceVariant),
                        shadowElevation = 8.dp,
                        modifier = Modifier.padding(top = 4.dp, start = 8.dp, end = 8.dp, bottom = 8.dp)
                    ){
                        Column(){
                            product._pricingList.forEach{ item ->
                                Row(
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ){
                                    DataSource.findStore(item.first)?.let { Text(
                                        text = it._storeName,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    ) }
                                    Text(
                                        text = item.second.toString() + "€",
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    )
                                }
                            }
                        }
                    }
                }
                )
                collapsableMenu(title = stringResource(id = R.string.reviews), focusManager = focusManager,  content = {
                        Column(){
                            Surface(
                                color = MaterialTheme.colorScheme.surfaceVariant,
                                shape = RoundedCornerShape(8.dp),
                                border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurfaceVariant),
                                shadowElevation = 8.dp,
                                modifier = Modifier.padding(top = 4.dp, start = 8.dp, end = 8.dp, bottom = 8.dp)
                            ) {
                                Row(modifier = Modifier.fillMaxWidth()) {
                                    Button(
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = MaterialTheme.colorScheme.surfaceVariant,
                                        ),
                                        modifier = Modifier
                                            .fillMaxSize(),
                                        onClick = {
                                            navController.navigate(EnergeticScreen.Review.name)
                                        }
                                    ) {
                                        Text(
                                            text = "Leave a review",
                                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        )
                                    }
                                }
                            }
                            product._reviewList.forEach{
                                    Surface(
                                        color = MaterialTheme.colorScheme.surfaceVariant,
                                        shape = RoundedCornerShape(8.dp),
                                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurfaceVariant),
                                        shadowElevation = 8.dp,
                                        modifier = Modifier.padding(top = 4.dp, start = 8.dp, end = 8.dp, bottom = 8.dp)
                                    ) {
                                    Column(
                                        modifier = Modifier
                                            .padding(8.dp)
                                            .fillMaxWidth()
                                    ){
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ){
                                            Column(
                                                modifier = Modifier.fillMaxWidth(0.5f)
                                            ) {
                                                    Text(
                                                        text = (DataSource.findAccount(it._reviewWriter)?._firstName) + " " + DataSource.findAccount(it._reviewWriter)?._lastName + " - aangekocht bij " + it._reviewStore,
                                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                                    )
                                                    Text(
                                                        text = it._reviewDate.day.toString() + "/" + it._reviewDate.month.toString() + "/" + it._reviewDate.year.toString(),
                                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                                    )
                                            }
                                            Column(
                                                modifier = Modifier.fillMaxWidth(),
                                                horizontalAlignment = Alignment.End
                                            ) {
                                                Row() {
                                                    for(i in 1..it._reviewRating.toInt()){
                                                        Icon(painter = painterResource(id = R.drawable.baseline_star_rate_24), contentDescription = "", tint = Color(0xFFFFC107))
                                                    }
                                                    for(i in it._reviewRating.toInt()..4){
                                                        Icon(painter = painterResource(id = R.drawable.baseline_star_rate_24), contentDescription = "", tint = MaterialTheme.colorScheme.onSurfaceVariant)
                                                    }
                                                }
                                            }
                                        }
                                        Row() {
                                            Text(
                                                text = it._reviewContent,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun productTitle(title: String){
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ){
        Text(
            text = title,
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun productImages(productImages: List<Int>, focusManager: FocusManager){
    val pagerState = rememberPagerState()
    HorizontalPager(
        modifier = Modifier.fillMaxSize(),
        pageCount = productImages.size,
        state = pagerState,
        contentPadding = PaddingValues(start = 30.dp, end = 30.dp)
    ) { page ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(10.dp)
                .graphicsLayer {
                    val pageOffset =
                        ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue
                    alpha = lerp(
                        start = 0.5f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                },
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = productImages[page]),
                contentDescription = ""
            )
        }
    }
    Row(
        Modifier
            .height(60.dp)
            .fillMaxWidth(),
            //.align(Alignment.BottomCenter),
        horizontalArrangement = Arrangement.Center
    ) {
        val coroutineScope = rememberCoroutineScope()
        if(productImages.size > 5){
            repeat(5) { iteration ->
                val border = if (pagerState.currentPage == iteration) MaterialTheme.colorScheme.surfaceTint else MaterialTheme.colorScheme.onSurfaceVariant
                val borderWidth = if (pagerState.currentPage == iteration) 2.dp else 1.dp
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clickable {
                            focusManager.clearFocus()
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(
                                    iteration
                                )
                            }
                        }
                        .border(width = borderWidth, color = border)
                ){
                    Image(painter = painterResource(id = productImages[iteration]), contentDescription = "")
                }
            }
            val border = if (pagerState.currentPage >= 5) MaterialTheme.colorScheme.surfaceTint else MaterialTheme.colorScheme.onSurfaceVariant
            val borderWidth = if (pagerState.currentPage >= 5) 2.dp else 1.dp
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .border(width = borderWidth, color = border)
            ){
                Image(painter = painterResource(id = productImages[6]), contentDescription = "")
                Text(text = (productImages.size - 5).toString() + "+", modifier = Modifier.align(Alignment.Center), color = Color.White, fontSize = 25.sp)
            }
        } else {
            repeat(productImages.size) { iteration ->
                val border = if (pagerState.currentPage == iteration) MaterialTheme.colorScheme.surfaceTint else MaterialTheme.colorScheme.onSurfaceVariant
                val borderWidth = if (pagerState.currentPage == iteration) 2.dp else 1.dp
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clickable {
                            focusManager.clearFocus()
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(
                                    iteration
                                )
                            }
                        }
                        .border(width = borderWidth, color = border)
                ){
                    Image(painter = painterResource(id = productImages[iteration]), contentDescription = "")
                }
            }
        }
    }
}

@Composable
private fun collapsableMenu(title: String, content: @Composable () -> Unit = {}, focusManager: FocusManager) {
    var expanded by remember { mutableStateOf(true) }

    val degrees = if (expanded) 180f else 0f
    Column() {
        Surface(
            color = MaterialTheme.colorScheme.surfaceVariant,
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurfaceVariant),
            shadowElevation = 8.dp,
            modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp)
        ) {
            Row(
                modifier = Modifier
                    .clickable {
                        focusManager.clearFocus()
                        expanded = !expanded
                    }
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    modifier = Modifier.weight(1f),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Surface(
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.surfaceTint
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_expand_more_24),
                        contentDescription = "",
                        modifier = Modifier.rotate(degrees),
                        tint = MaterialTheme.colorScheme.surfaceVariant
                    )
                }
            }
        }
        AnimatedVisibility(visible = expanded) {
            if (expanded) {
                content()
            }
        }
    }
}
