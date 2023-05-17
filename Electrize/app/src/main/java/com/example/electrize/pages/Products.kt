package com.example.electrize.pages

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
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
import com.example.electrize.data.DataSource
import com.example.electrize.dataStructures.Category

/*
Matches balsamiq slide 3 (start counting from 1)
*/

@Composable
fun products(navController: NavController, focusManager: FocusManager){
    LazyColumn(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(start = 10.dp, end = 10.dp)
            .fillMaxSize()
    ){
        DataSource.CategoryGroups.forEach { group ->
            item {
                row(group._categoryGroupName, DataSource.getCategoriesFromGroup(group._categoryGroupId), navController = navController, focusManager = focusManager)
            }
        }
    }
}

@Composable
private fun row(rowTitle: String, categories: List<Category>, navController: NavController, focusManager: FocusManager){
    Column(
    ) {
        Text(
            text = rowTitle,
            fontSize = 25.sp,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(8.dp)
        )
        LazyRow(
            modifier = Modifier.fillMaxSize()
        ){
            categories.forEach{category->
                item{
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
                        Column(modifier = Modifier.border(width = 1.dp, color = Color.Black).padding(5.dp)) {
                            Image(
                                painter = painterResource(id = category._imageId),
                                contentDescription = "",
                                contentScale = ContentScale.Fit,
                                modifier = Modifier.size(140.dp)
                            )
                            Text(
                                text = category._categoryName,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1,
                                modifier = Modifier.width(140.dp)
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