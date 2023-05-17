package com.example.project_groep_2.ui.theme

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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.project_groep_2.R
import com.example.project_groep_2.RetrievalrequestData
import com.example.project_groep_2.typeOfRequest

@Composable
fun retrievalRequest(requests: List<RetrievalrequestData>){
    var incoming = mutableListOf<RetrievalrequestData>()
    var exiting = mutableListOf<RetrievalrequestData>()
    for(request: RetrievalrequestData in requests){
        if(request.getTypeOfRequest() == typeOfRequest.RETRIEVAL){
            incoming.add(request)
        } else {
            exiting.add(request)
        }
    }
    Column() {
        inComingRetrievalRequest(data = incoming)
        exitingRetrievalRequest(data = exiting)
    }
}

@Composable
fun inComingRetrievalRequest(data: List<RetrievalrequestData>) {
    Text(text = stringResource(id = R.string.Retrievalrequest), fontSize = 27.sp, modifier = Modifier.padding(start = 10.dp))
    Divider(color = Color.Gray, thickness = 2.dp, modifier = Modifier
        .fillMaxWidth()
        .padding(start = 10.dp, end = 10.dp, bottom = 10.dp))
    LazyColumn(){
        items(data.size){ index->
            inComingRetrievalRequestItem(item = data[index])
        }
    }
}


@Composable
fun inComingRetrievalRequestItem(item: RetrievalrequestData){
    var data: RetrievalrequestData = item
    Row(modifier = Modifier.padding(start = 10.dp, end = 10.dp)){
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
            Row(){
                Column() {
                    Text(text = data.getProfile().getName() + " " + data.getProfile().getLastName() + " - " + data.getSendTime().day.toString() + "/" + data.getSendTime().month.toString() + "/" + data.getSendTime().year.toString() + " - " + data.getSendTime().hours + ":" + data.getSendTime().minutes)
                }
            }
            Row(){
                Text(text = stringResource(id = R.string.Retrievalrequest_message),
                    fontSize = 13.sp,
                    lineHeight = 1.em
                )
            }
        }
    }
    Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier
        .fillMaxWidth()
        .padding(start = 10.dp, end = 10.dp, bottom = 10.dp, top = 10.dp))
}

@Composable
fun exitingRetrievalRequest(data: List<RetrievalrequestData>){
    Text(text = stringResource(id = R.string.Exiting_requests), fontSize = 27.sp, modifier = Modifier.padding(start = 10.dp))
    Divider(color = Color.Gray, thickness = 2.dp, modifier = Modifier
        .fillMaxWidth()
        .padding(start = 10.dp, end = 10.dp, bottom = 25.dp))
    LazyColumn(){
        items(data.size){ index->
            exitingRetrievalRequestItem(item = data[index])
        }
    }
}


@Composable
fun exitingRetrievalRequestItem(item: RetrievalrequestData){
    var data: RetrievalrequestData = item
    Row(modifier = Modifier
        .padding(start = 10.dp, end = 10.dp)
        .fillMaxWidth()){
        Column(modifier = Modifier) {
            Image(
                painter = painterResource(id = R.drawable.profile_picture_man),
                contentDescription = "",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(100.dp)
                    .border(BorderStroke(1.dp, Color.Black))
            )
        }
        Column(modifier = Modifier.padding(start = 10.dp)) {
            Row(){
                Text(
                    text = data.getProfile().getName() + " " + data.getProfile()
                        .getLastName() + " - " + data.getSendTime().day.toString() + "/" + data.getSendTime().month.toString() + "/" + data.getSendTime().year.toString() + " - " + data.getSendTime().hours + ":" + data.getSendTime().minutes
                )
            }
            Row() {
                Column(modifier = Modifier.weight(10f)) {
                    Text(
                        text = stringResource(id = R.string.Retrievalrequest_message),
                        fontSize = 13.sp,
                        lineHeight = 1.em
                    )
                }
                Column() {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            modifier = Modifier.background(Color.Red).border(BorderStroke(1.dp, Color.Black)),
                            painter = painterResource(id = R.drawable.close),
                            contentDescription = "Cancel request")
                    }
                }
            }
        }
    }
    Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier
        .fillMaxWidth()
        .padding(start = 10.dp, end = 10.dp, bottom = 10.dp, top = 10.dp))
}
