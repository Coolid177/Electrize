package com.example.project_groep_2

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun friendRequests(data: Profile){
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { /* ... */ }) {
                Icon(painter = painterResource(id = R.drawable.add_friend), contentDescription = "send friend request", modifier = Modifier
                    .size(27.dp))
            }
        },
    ) {
        LazyColumn() {
            item {
                Text(text = stringResource(id = R.string.Friend_requests), fontSize = 27.sp, modifier = Modifier.padding(start = 10.dp, end = 10.dp))
            }
            item {
                Divider(
                    color = Color.Gray, thickness = 2.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp)
                )
            }
            item {
                Text(text = stringResource(id = R.string.Incoming), fontSize = 24.sp, modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 10.dp))
            }
            item {
                Divider(
                    color = Color.Gray, thickness = 1.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp)
                )
            }
            items(data.getIncomingRequests().size){ index->
                incomingRequest(data = data.getIncomingRequests()[index])
            }
            item {
                Text(text = stringResource(id = R.string.Outgoing), fontSize = 24.sp, modifier = Modifier.padding(start = 10.dp, end = 10.dp))
            }
            item {
                Divider(
                    color = Color.Gray, thickness = 1.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp)
                )
            }
            items(data.getOutgoingRequests().size){ index ->
                outgoingRequest(data = data.getOutgoingRequests()[index])
            }
        }
    }
}

@Composable
fun incomingRequest(data: Profile){
    var data: Profile = data
    Row(modifier = Modifier.padding(top = 10.dp, start = 10.dp, end = 10.dp), verticalAlignment = Alignment.CenterVertically) {
        Column(modifier = Modifier.padding(end = 10.dp)) {
            Image(
                painter = painterResource(id = R.drawable.profile_picture_man),
                contentDescription = "",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(100.dp)
                    .border(BorderStroke(1.dp, Color.Black))
            )
        }
        Column() {
            Text(text = data.getName() + " " + data.getLastName(), fontSize = 20.sp)
            Text(stringResource(id = R.string.Userid) + ": " + data.getId().slice(IntRange(0,3)) + " " + data.getId().slice(IntRange(4, 7))  + " " + data.getId().slice(IntRange(8,11)), fontSize = 12.sp)
        }
        Column() {
            Row() {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(painter = painterResource(id = R.drawable.close), contentDescription = "Reject friend request", modifier = Modifier
                        .background(Color.Red)
                        .size(27.dp))
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(painter = painterResource(id = R.drawable.done), contentDescription = "Accept friend request", modifier = Modifier
                        .background(Color.Green)
                        .size(27.dp))
                }
            }
        }
    }
    Divider(color = Color.Gray, thickness = 1.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, start = 10.dp, end = 10.dp))
}

@Composable
fun outgoingRequest(data: Profile){
    var data: Profile = data
    Row(modifier = Modifier.padding(top = 10.dp, start = 10.dp, end = 10.dp), verticalAlignment = Alignment.CenterVertically) {
        Column(modifier = Modifier.padding(end = 10.dp)) {
            Image(
                painter = painterResource(id = R.drawable.profile_picture_man),
                contentDescription = "",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(100.dp)
                    .border(BorderStroke(1.dp, Color.Black))
            )
        }
        Column() {
            Text(text = data.getName() + " " + data.getLastName(), fontSize = 20.sp)
            Text(stringResource(id = R.string.Userid) + ": " + data.getId().slice(IntRange(0,3)) + " " + data.getId().slice(IntRange(4, 7))  + " " + data.getId().slice(IntRange(8,11)), fontSize = 12.sp)
        }
        Column() {
            Row() {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(painter = painterResource(id = R.drawable.close), contentDescription = "Cancel friend request", modifier = Modifier
                        .background(Color.Red)
                        .size(27.dp))
                }
            }
        }
    }
    Divider(color = Color.Gray, thickness = 1.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, start = 10.dp, end = 10.dp))
}