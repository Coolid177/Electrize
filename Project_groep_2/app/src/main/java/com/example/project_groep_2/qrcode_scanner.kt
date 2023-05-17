package com.example.project_groep_2

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun qrcodeScanner() {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { header(scaffoldState, scope) },
        bottomBar = { fastActionMenu() },
        drawerContent = { hamburgerDrawer(scaffoldState, scope) }
    ) {
        Column(modifier = Modifier
            .fillMaxSize()) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center) {
                Icon(painter = painterResource(id = R.drawable.qrcode_scanner),
                    contentDescription = "scanned qr-code",
                    modifier = Modifier
                        .fillMaxSize(0.8f),)
            }
            Row(modifier = Modifier
                .fillMaxSize()
                .clickable {  }) {
                Column(modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.33f)
                    .padding(end = 10.dp)) {
                    androidx.compose.material3.Icon(
                        painter = painterResource(id = R.drawable.products),
                        contentDescription = "product",
                        modifier = Modifier
                            .size(90.dp)
                    )
                }
                Column(modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding()) {
                    Text(text = "product")
                }
            }
        }
    }
}