package com.example.electrize.pages

/**
 * @author Senne
 */

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
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
import com.example.electrize.ui.theme.ElectrizeTheme

@Composable
fun compare(
    viewModel: EnergeticViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
        .fillMaxSize(),
    focusManager: FocusManager
) {
    val allCompareProducts = viewModel.findCompareProducts();

    //Take the last three elemets so when a new items is added to the compare that will take priority
    val compareProducts: List<Product> = allCompareProducts.takeLast(3)

    if (compareProducts.size == 0){
        //Handle no products to compare
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .background(MaterialTheme.colorScheme.background),
        ) {
            Spacer(modifier = Modifier.weight(0.4f))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){
                Image(
                    painter = painterResource(id = R.drawable.empty_compare_transparent),
                    contentDescription = "",
                    modifier = Modifier.size(190.dp)
                )
            }
            Spacer(modifier = Modifier.weight(0.1f))
            Row(
                modifier = Modifier.fillMaxWidth()
            ){
                Text(
                    text = stringResource(id = R.string.empty_compare_header),
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
                    text = stringResource(id = R.string.empty_compare_message),
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(start = 50.dp, end = 50.dp),
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.weight(0.6f))
            Row(){
                ExtendedFloatingActionButton(onClick = {
                    focusManager.clearFocus()
                    navController.navigate(EnergeticScreen.Products.name)
                                                       }, modifier = Modifier
                    .padding(30.dp, 10.dp)
                    .weight(1.0f)) {
                    Text(text = stringResource(id = R.string.go_to_products))
                }
            }
            Spacer(modifier = Modifier.weight(0.1f))
        }
    } else {
        Scaffold (
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    onClick = {
                        focusManager.clearFocus()
                        navController.navigate("${EnergeticScreen.Products}")
                              },
                    modifier = Modifier,
                )
                {
                    Icon(painter = painterResource(id = R.drawable.compare_add), contentDescription = "", Modifier.size(24.dp))
                }
            },
            floatingActionButtonPosition = FabPosition.End
        ) { contentPadding ->
            //Handle products to compare
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                //Top row, fills 15% of screen
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.20f)
                ) {
                    //Spacer links boven hoek
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1.0f)
                    )

                    //Product headers
                    compareProducts.forEach {
                        Box(
                            modifier = Modifier.weight(1.0f),
                            contentAlignment = Alignment.TopEnd
                        ) {
                            Card(
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.White,
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .border(
                                        1.dp, Color.LightGray, RoundedCornerShape(10.dp)
                                    )
                                    .clickable {
                                        focusManager.clearFocus()
                                        navController.navigate("${EnergeticScreen.Products}/${it._productId}") //Navigate back to the product page
                                    },
                            ) {
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                ) {
                                    //Product image
                                    Image(
                                        painter = painterResource(id = it._productImages.first()),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .weight(0.5f),
                                    )

                                    //Product name
                                    Text(
                                        text = it._productName,
                                        color = Color.Black,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(5.dp)
                                            .weight(0.5f),
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                            }
                            Button(
                                onClick = {
                                    focusManager.clearFocus()
                                    viewModel.removeFromCompare(it) //Remove the item from the compare
                                    navController.navigate("${EnergeticScreen.Compare}") //Refresh the page
                                    //TODO: Check if remove can be implemented better
                                },
                                contentPadding = PaddingValues(0.dp), //https://developer.android.com/jetpack/compose/layouts/material#content-slots
                                shape = RoundedCornerShape(5.dp, 5.dp, 5.dp, 5.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                                ),
                                modifier = Modifier
                                    .size(30.dp)
                                    .padding(0.dp),
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.close),
                                    contentDescription = "Verwijder uit compare",
                                    modifier = Modifier,
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }

                LazyColumn(
                    modifier = Modifier
                        .weight(0.85f)
                        .padding(0.dp, 15.dp)
                        .drawBehind {
                            val width = size.width
                            val height = size.height
                            drawLine(
                                color = Color.LightGray,
                                start = Offset(x = 0.0f, y = 0.0f),
                                end = Offset(x = width, y = 0.0f),
                                strokeWidth = 1.dp.toPx()
                            )
                        }
                ) {
                    //Get all the specifications to show
                    val allSpecificationStrings = GetAllSpecificationStrings(compareProducts)
                    items (allSpecificationStrings.size) { index ->
                        GetCompareRow(rowHeader = allSpecificationStrings.elementAt(index), rowValues = GetAllProductSpecificationValues(allCompareProducts, allSpecificationStrings.elementAt(index)))
                    }
                }
            }
        }
    }
}

@Composable
private fun GetCompareRow(rowHeader: String, rowValues: List<String>){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .drawBehind {
                val width = size.width
                val height = size.height
                drawLine(
                    color = Color.LightGray,
                    start = Offset(x = 0.0f, y = height),
                    end = Offset(x = width, y = height),
                    strokeWidth = 1.dp.toPx()
                )
            }
    ) {
        Text(
            text = rowHeader,
            modifier = Modifier
                .padding(5.dp, 10.dp)
                .fillMaxSize()
                .weight(1.0f)
        )
        GetCompareRowValueRow(
            rowValues = rowValues,
            modifier = Modifier.weight(rowValues.size * 1.0f),
        )
    }
}

@Composable
private fun GetCompareRowValueRow(rowValues: List<String>, modifier: Modifier){
    Row(
        modifier = modifier
    ) {
        for(rowValue in rowValues) {
            Text(
                text = rowValue,
                modifier = Modifier
                    .padding(5.dp, 10.dp, 5.dp, 10.dp)
                    .fillMaxSize()
                    .weight(1.0f)
            )
        }
    }
}

private fun GetAllSpecificationStrings(productList: List<Product>): Set<String> {
    var allSpecStrings: MutableSet<String> = mutableSetOf()
    productList.forEach {
        var specTypes = it._specList.forEach { spec->
            allSpecStrings.add(spec.first)
        }
    }
    return allSpecStrings
}

private fun GetAllProductSpecificationValues(productList: List<Product>, specString: String): List<String>{
    var allProductSpecValues: MutableList<String> = mutableListOf()
    productList.forEach {item->
        val specificationIndex = item._specList.indexOfFirst { it.first == specString }
        if (specificationIndex > -1){
            allProductSpecValues.add(item._specList.elementAt(specificationIndex).second)
        } else {
            allProductSpecValues.add("/")
        }
    }
    return allProductSpecValues
}