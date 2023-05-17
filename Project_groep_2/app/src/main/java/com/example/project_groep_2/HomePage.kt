package com.example.project_groep_2

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material.Text
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun <E> displayRows(valuesList: List<List<E>>, headers: List<String>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        itemsIndexed(valuesList) { index, values ->
            Column(
                modifier = Modifier
                    .fillParentMaxWidth()
                    .fillParentMaxHeight(0.20f)
                    .padding(start = 10.dp, end = 10.dp)
            ) {
                Text(text = headers[index], Modifier.fillMaxWidth())
                LazyRow(
                    Modifier
                        .fillMaxSize()
                ) {
                    items(values) { value ->
                        Column(
                            modifier = Modifier
                                .fillParentMaxWidth(0.33f)
                                .fillParentMaxHeight()
                                .padding(top = 10.dp, bottom = 10.dp, end = 10.dp)
                        ) {
                            Text(
                                text = value.toString(), modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.Cyan)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun searchBar() {
    var text by remember { mutableStateOf("") }
    OutlinedTextField(
        value = text, onValueChange = { text = it },
        modifier = Modifier
            .fillMaxWidth()
            .height(25.dp),
        shape = RoundedCornerShape(25),
        singleLine = true,
        leadingIcon = {
            Icon(painter = painterResource(R.drawable.search),
                contentDescription = "search",
                modifier = Modifier
                    .fillMaxHeight()
                    .width(22.dp))
        }
    )
}

@Composable
fun hamburgerMenu(scaffoldState: ScaffoldState, scope: CoroutineScope) {
    IconButton(onClick = {
            scope.launch {
                scaffoldState.drawerState.apply {
                    if (isClosed){
                        open()
                    } else close()
                }
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()) {
        Icon(painter = painterResource(R.drawable.hamburger_menu),
            contentDescription = "expand menu",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight())
    }
}


@Composable
fun header(scaffoldState: ScaffoldState, scope: CoroutineScope) {
    Surface(
        shadowElevation = 30.dp,
        tonalElevation = 50.dp,
        shape = RectangleShape,
    ) {
        Row(modifier = Modifier
            .background(color = Color.White)
            .fillMaxWidth()
            .height(60.dp)
            .padding(bottom = 10.dp, top = 5.dp)) {
            Column(verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.2f)) {
                hamburgerMenu(scaffoldState, scope)
            }
            Column(modifier = Modifier
                .fillMaxWidth(0.75f)
                .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {
                searchBar()
            }
            Column(verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(all = 5.dp),
                horizontalAlignment = Alignment.CenterHorizontally){
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(painter = painterResource(id = R.drawable.qrcode_scanner),
                        contentDescription = "qr code scanner",
                        modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}

@Composable
fun fastActionMenu() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .background(color = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.25f)
        ) {
            IconButton(
                onClick = {},
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Icon(
                    painter = painterResource(R.drawable.home),
                    contentDescription = "go to home",
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.333f)
        ) {
            IconButton(
                onClick = {},
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Icon(
                    painter = painterResource(R.drawable.products),
                    contentDescription = "go to home",
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.5f)
        ) {
            IconButton(
                onClick = {},
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Icon(
                    painter = painterResource(R.drawable.routes),
                    contentDescription = "go to home",
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            IconButton(
                onClick = {},
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Icon(
                    painter = painterResource(R.drawable.shopping_cart),
                    contentDescription = "go to home",
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }
    }
}

@Composable
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
fun homePage() {
    val valuesList: List<List<Int>> = listOf(
        listOf(1, 2, 3, 4, 5),
        listOf(1, 2, 3, 4, 5),
        listOf(1, 2, 3, 4, 5),
        listOf(1, 2, 3, 4, 5),
        listOf(1, 2, 3, 4, 5),
        listOf(1, 2, 3, 4, 5),
        listOf(1, 2, 3, 4, 5)
    )
    val headers: List<String> = listOf(
        "recent bezocht",
        "populaire categoriën",
        "huidige acties",
        "aanbevolen voor jou",
        "iets",
        "nog iets",
        "yes"
    )
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { header(scaffoldState, scope) },
        bottomBar = { fastActionMenu() },
        drawerContent = { hamburgerDrawer(scaffoldState, scope) }
    ) {
        displayRows(valuesList, headers)
    }
}