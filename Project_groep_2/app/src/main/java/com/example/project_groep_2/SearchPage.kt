package com.example.project_groep_2

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun searchPage() {
    val values: List<String> = listOf("This is a test description","This is a test description","This is a test description","This is a test description","This is a test description","This is a test description","This is a test description","This is a test description","This is a test description","This is a test description","This is a test description","This is a test description")
    val categories: List<String> = listOf("category1","category2","category3","category4","category5","category6")
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    var filterVisibility = remember {
        mutableStateOf(false)
    }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {filterVisibility.value = !filterVisibility.value},
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.filter),
                    contentDescription = "toggle filter menu"
                )
            }
        },
        isFloatingActionButtonDocked = false,
        scaffoldState = scaffoldState,
        topBar = { header(scaffoldState, scope) },
        bottomBar = { fastActionMenu() },
        drawerContent = { hamburgerDrawer(scaffoldState, scope) }
    ) {
        filterMenu(filterVisibility)
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            generateChildRow(categories, this)
            generateColumnChildren(values, this)
        }
    }
}

fun <E> generateChildRow(values: List<E>, scope: LazyListScope) {
    scope.item {
        LazyRow(modifier = Modifier
            .fillParentMaxWidth()
            .fillParentMaxHeight(0.2f)
            .padding(start = 5.dp)) {
            items(values) {value ->
                Column(modifier = Modifier
                    .fillParentMaxHeight()
                    .fillParentMaxWidth(0.33f)
                    .padding(end = 5.dp, top = 5.dp, bottom = 5.dp)
                    .background(color = Color.Cyan)) {
                    Text(value.toString(), modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}