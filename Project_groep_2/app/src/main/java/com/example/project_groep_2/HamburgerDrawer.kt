package com.example.project_groep_2

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun hamburgerDrawer(scaffoldState: ScaffoldState, scope: CoroutineScope) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(60.dp)
        .background(Color.White)
        .padding(bottom = 10.dp, top = 5.dp),
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(start = 15.dp),
            horizontalAlignment = Alignment.Start) {
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
                    .width(50.dp)
                    .fillMaxHeight()) {
                Icon(painter = painterResource(R.drawable.hamburger_menu),
                    contentDescription = "collapse menu",
                    modifier = Modifier
                        .fillMaxSize())
            }
        }
    }
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(60.dp)
        .clickable { },
        horizontalArrangement = Arrangement.Start) {
        Column(modifier = Modifier
            .fillMaxHeight()
            .width(78.dp)
            .padding(start = 20.dp, end = 20.dp)) {
            Icon(painter = painterResource(R.drawable.contacts),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxSize())
        }
        Column(modifier = Modifier
            .fillMaxHeight(),
            verticalArrangement = Arrangement.Center) {
            Text("Contacten")
        }
    }
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(60.dp)
        .clickable { },
        horizontalArrangement = Arrangement.Start) {
        Column(modifier = Modifier
            .fillMaxHeight()
            .width(78.dp)
            .padding(start = 20.dp, end = 20.dp)) {
            Icon(painter = painterResource(R.drawable.settings),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxSize())
        }
        Column(modifier = Modifier
            .fillMaxHeight(),
            verticalArrangement = Arrangement.Center) {
            Text("instellingen")
        }
    }
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(60.dp)
        .clickable { },
        horizontalArrangement = Arrangement.Start) {
        Column(modifier = Modifier
            .fillMaxHeight()
            .width(78.dp)
            .padding(start = 20.dp, end = 20.dp)) {
            Icon(painter = painterResource(R.drawable.compare),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxSize())
        }
        Column(modifier = Modifier
            .fillMaxHeight(),
            verticalArrangement = Arrangement.Center) {
            Text("vergelijken")
        }
    }
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(60.dp)
        .clickable { },
        horizontalArrangement = Arrangement.Start) {
        Column(modifier = Modifier
            .fillMaxHeight()
            .width(78.dp)
            .padding(start = 20.dp, end = 20.dp)) {
            Icon(painter = painterResource(R.drawable.friend_requests),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxSize())
        }
        Column(modifier = Modifier
            .fillMaxHeight(),
            verticalArrangement = Arrangement.Center) {
            Text("vriendschapsverzoeken")
        }
    }
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(60.dp)
        .clickable { },
        horizontalArrangement = Arrangement.Start) {
        Column(modifier = Modifier
            .fillMaxHeight()
            .width(78.dp)
            .padding(start = 20.dp, end = 20.dp)) {
            Icon(painter = painterResource(R.drawable.paper_plane),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxSize())
        }
        Column(modifier = Modifier
            .fillMaxHeight(),
            verticalArrangement = Arrangement.Center) {
            Text("ophaalverzoeken")
        }
    }
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(60.dp)
        .clickable { },
        horizontalArrangement = Arrangement.Start) {
        Column(modifier = Modifier
            .fillMaxHeight()
            .width(78.dp)
            .padding(start = 20.dp, end = 20.dp)) {
            Icon(painter = painterResource(R.drawable.rating),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxSize())
        }
        Column(modifier = Modifier
            .fillMaxHeight(),
            verticalArrangement = Arrangement.Center) {
            Text("rate deze app")
        }
    }
}