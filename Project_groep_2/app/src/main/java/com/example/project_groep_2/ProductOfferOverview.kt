package com.example.project_groep_2

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun <E> displayColumn(values: List<E>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        generateColumnChildren(values, this)
    }
}

fun <E> generateColumnChildren(values: List<E>, scope: LazyListScope) {
    scope.items(values) { value ->
        Row(modifier = Modifier
            .fillParentMaxWidth()
            .fillParentMaxHeight(0.2f)
            .padding(top = 5.dp)) {
            Column(modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.33f)
                .padding(end = 10.dp)) {
                Icon(painter = painterResource(id = R.drawable.products),
                    contentDescription = "product",
                    modifier = Modifier
                        .fillMaxSize())
            }
            Column(modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 10.dp)) {
                Text(text = value.toString())
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun productOfferOverview() {
    val values: List<String> = listOf("This is a test description","This is a test description","This is a test description","This is a test description","This is a test description","This is a test description","This is a test description","This is a test description","This is a test description","This is a test description","This is a test description","This is a test description")
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    var filterVisibility = remember {
        mutableStateOf(false)
    }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {filterVisibility.value = !filterVisibility.value},
            ) {
                androidx.compose.material.Icon(
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
        displayColumn(values)
    }
}

