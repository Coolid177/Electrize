package com.example.project_groep_2

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.project_groep_2.ui.theme.Project_groep_2Theme

//Test data
val muis1: Product1 = Product1(productName = "Muis1", productSpecificationTypes = listOf("Gewicht", "Lengte", "Size"), productSpecificationValues = listOf("50g", "1m", "5"))
val muis2: Product1 = Product1(productName = "Muis2", productSpecificationTypes = listOf("test", "Gewicht", "Size"), productSpecificationValues = listOf("50000", "25g", "4"))
val muis3: Product1 = Product1(productName = "Muis3", productSpecificationTypes = listOf("test", "Draadloos"), productSpecificationValues = listOf("4000", "Yes"))
val muis4: Product1 = Product1(productName = "Muis4", productSpecificationTypes = listOf("test", "Draadloos"), productSpecificationValues = listOf("4000", "Yes"))
val muis5: Product1 = Product1(productName = "Muis5", productSpecificationTypes = listOf("test", "Draadloos", "Test1", "Test2", "Test3", "Test4", "Test5", "Test6", "Test7", "Test8", "Test9", "Test10"), productSpecificationValues = listOf("4000", "Yes", "Test1", "Test2", "Test3", "Test4", "Test5", "Test6", "Test7", "Test8", "Test9", "Test10"))

@Preview(showBackground = true)
@Composable
fun PreviewComparePage() {
    Project_groep_2Theme {
        Surface {
            val coroutineScope = rememberCoroutineScope()
            val scrollState = rememberScrollState()
            ComparePage(productList = listOf(muis1, muis2, muis3, muis4, muis5))
        }
    }
}

@Composable
fun ComparePage(
    productList: List<Product1>,
    modifier: Modifier = Modifier
        .fillMaxSize()
) {
    //TODO Limit max amount of products to compare to 3

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        //Top row, fills 15% of screen
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.15f)
        ) {
            //Spacer links boven hoek
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1.0f)
            )

            //Product headers
            productList.forEach {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1.0f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    //Product image
                    Image(
                        painter = painterResource(id = it.productImages.first()),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.7f),
                    )
                    
                    //Product name
                    Text(
                        text = it.productName,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.3f)
                    )
                }
            }
        }

        Row(
            modifier = Modifier
            .weight(0.85f)
        ) {
            //Get all the specifications to show
            val allSpecificationStrings = GetAllSpecificationStrings(productList)
            //Make a list with all the specifications and there values
            var gridList = mutableListOf<String>()
            
            //FOr each of the specifications
            allSpecificationStrings.forEach {
                //Add it to the grid
                gridList.add(it)
                //Get all the values of all the products for that specifiication
                var allSpecificationValues = GetAllProductSpecificationValues(productList, it)
                //Add all the product values to the grid list
                gridList.addAll(allSpecificationValues)
            }
            LazyColumn(
                content = {

                }
            )
            LazyVerticalGrid(
                //Make a column for each of the products and the categories
                columns = GridCells.Fixed(productList.size + 1),
                content = {
                    //Put all items from the gridlist into the grid
                    items(gridList.size) {
                        index ->
                        Text(
                            text = gridList.elementAt(index),
                            modifier = Modifier
                                .padding(5.dp, 10.dp, 5.dp, 10.dp)
                        )
                    }
                }
            )
        }

        ExtendedFloatingActionButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .align(alignment = Alignment.End),
        )
        {
            Text(text = "Add product")
        }
    }
}

@Composable
fun GetCompareRow(rowHeader: String, rowValues: List<String>){
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = rowHeader)
        GetCompareRowValueRow(rowValues = rowValues)
    }
}

@Composable
fun GetCompareRowValueRow(rowValues: List<String>){
    LazyRow(
        modifier = Modifier,
    ) {
        items(rowValues) {
            Text(
                text = it,
                modifier = Modifier.padding(5.dp, 10.dp, 5.dp, 10.dp)
            )
        }
    }
}

fun GetAllSpecificationStrings(productList: List<Product1>): Set<String> {
    var allSpecStrings: MutableSet<String> = mutableSetOf()
    productList.forEach {
        var specTypes = it.productSpecificationTypes
        allSpecStrings.addAll(specTypes)
    }

    return allSpecStrings
}

fun GetAllProductSpecificationValues(productList: List<Product1>, specString: String): List<String>{
    var allProductSpecValues: MutableList<String> = mutableListOf()
    productList.forEach {
        val specificationIndex = it.productSpecificationTypes.indexOf(specString)
        if (specificationIndex > -1){
            allProductSpecValues.add(it.productSpecificationValues.elementAt(specificationIndex))
        } else {
            allProductSpecValues.add("/")
        }
    }
    return allProductSpecValues
}