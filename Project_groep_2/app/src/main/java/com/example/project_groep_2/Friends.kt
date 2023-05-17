package com.example.project_groep_2

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
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

@Composable
fun friends(data: Profile){
    var data: Profile = data
    LazyColumn(modifier = Modifier.padding(start = 10.dp, end = 10.dp, bottom = 10.dp, top = 10.dp)){
        item {
            Text(text = stringResource(id = R.string.Friends),
                fontSize = 27.sp)
        }
        item{
            Divider(color = Color.Gray, thickness = 1.dp,
                modifier = Modifier.fillMaxWidth())
        }
        items(data.getFriends().size){index->
            friendItem(data = data.getFriends()[index])
        }
    }
}

@Composable
fun friendItem(data: Profile){
    var data: Profile = data
    Row(modifier = Modifier.padding(top = 10.dp), verticalAlignment = Alignment.CenterVertically) {
        Column(modifier = Modifier.padding(end = 10.dp)) {
            Image(
                painter = painterResource(id = R.drawable.profile_picture_man),
                contentDescription = "",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(110.dp)
                    .border(BorderStroke(1.dp, Color.Black))
            )
        }
        Column() {
            Text(text = data.getName() + " " + data.getLastName())
            Text(stringResource(id = R.string.Userid) + ": " + data.getId().slice(IntRange(0,3)) + " " + data.getId().slice(IntRange(4, 7))  + " " + data.getId().slice(
                IntRange(8,11)
            ))
        }
    }
    Divider(color = Color.Gray, thickness = 1.dp,
        modifier = Modifier.fillMaxWidth().padding(top = 10.dp))
}