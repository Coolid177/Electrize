package com.example.electrize.pages

/**
 * @author Rune
 */

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.electrize.EnergeticScreen
import com.example.electrize.EnergeticViewModel
import com.example.electrize.R
import com.example.electrize.data.DataSource
import com.example.electrize.dataStructures.CollectionRequest
import com.example.electrize.ui.theme.ElectrizeTheme

@Composable
fun retrievalRequest(viewModel: EnergeticViewModel, navController: NavController, focusManager: FocusManager){
    var incomming by remember{ mutableStateOf(viewModel.getIncomingCollectionRequest()) }
    var incommingCount by remember { mutableStateOf(incomming.size) }
    var exiting by remember{ mutableStateOf(viewModel.getOutgoingCollectionRequest()) }
    var exitingCount by remember { mutableStateOf(exiting.size) }
    if(incomming.isNotEmpty() || exiting.isNotEmpty()) {
        LazyColumn(
            modifier = Modifier.background(MaterialTheme.colorScheme.background).fillMaxHeight()
        ){
            item{
                Text(
                    text = stringResource(id = R.string.incomingcollection_requests),
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 27.sp,
                    modifier = Modifier
                        .padding(start = 10.dp)
                )
            }
            if(incomming.isNotEmpty()) {
                items(incomming) { incommingReq ->
                    inComingRetrievalRequestItem(item = incommingReq, navController = navController, update = {
                        incomming = viewModel.getIncomingCollectionRequest()
                        incommingCount = incomming.size
                    })
                }
            } else {
                item{
                    noRequestMessage(message = stringResource(id = R.string.no_incoming_request))
                }
            }
            item{
                Text(
                    text = stringResource(id = R.string.outgoingcollection_requests),
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 27.sp,
                    modifier = Modifier
                        .padding(start = 10.dp)
                )
            }
            if(exiting.isNotEmpty()) {
                items(exiting) { exitReq ->
                    exitingRetrievalRequestItem(item = exitReq, focusManager = focusManager, update = {
                        exiting = viewModel.getOutgoingCollectionRequest()
                        exitingCount = exiting.size
                    })
                }
            } else {
                item{
                    noRequestMessage(message = stringResource(id = R.string.no_exiting_request))
                }
            }
        }
    } else {
        emptyMessage(navController)
    }
}

@Composable
private fun emptyMessage(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxHeight()
    ) {
        Spacer(modifier = Modifier.weight(0.4f))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            Image(
                painter = painterResource(id = R.drawable.cat_hammer),
                contentDescription = "",
                modifier = Modifier.size(190.dp)
            )
        }
        Spacer(modifier = Modifier.weight(0.1f))
        Row(
            modifier = Modifier.fillMaxWidth()
        ){
            Text(
                text = stringResource(id = R.string.empty_retrievalrequest_header),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(start = 50.dp, end = 50.dp)
                    .fillMaxWidth(),
                fontSize = 25.sp
            )
        }
        Spacer(modifier = Modifier.weight(0.1f))
        Row(
            modifier = Modifier.fillMaxWidth()
        ){
            Text(
                text = stringResource(id = R.string.empty_retrievalrequest_message),
                modifier = Modifier.padding(start = 50.dp, end = 50.dp),
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.weight(0.6f))
        Row(){
            ExtendedFloatingActionButton(onClick = { navController.navigate(EnergeticScreen.Routes.name) }, modifier = Modifier
                .padding(30.dp, 10.dp)
                .weight(1.0f)) {
                Text(text = stringResource(id = R.string.go_to_routes))
            }
        }
        Spacer(modifier = Modifier.weight(0.1f))
    }
}

@Composable
private fun inComingRetrievalRequestItem(item: CollectionRequest, navController: NavController, update: () -> Unit = {}){
    var data: CollectionRequest = item
    Surface(
        color = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface),
        shadowElevation = 8.dp,
        modifier = Modifier.padding(8.dp).clickable{ navController.navigate("${EnergeticScreen.InspectIncomingRoute.name}/${data._collectionRequestId}")}
    ){
        Row(modifier = Modifier.fillMaxWidth()){
            Column() {
                Image(
                    painter = painterResource(id = DataSource.findAccount(data._senderId)!!._imageId),
                    contentDescription = "",
                    modifier = Modifier.size(150.dp)
                )
            }
            Column(
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .height(150.dp)
                    .padding(10.dp)
            ) {
                Text(
                    text = DataSource.findAccount(data._senderId)!!._firstName + " " + DataSource.findAccount(data._senderId)!!._lastName,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(bottom = 5.dp)
                )
                Text(
                    text = data._sendDate.day.toString() + "/" + data._sendDate.month.toString() + "/" + data._sendDate.year.toString() + " " + data._sendDate.hours.toString() + ":" + data._sendDate.minutes.toString(),
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 13.sp
                )
                Text(
                    text = stringResource(id = R.string.collectionrequest_message),
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 12.sp,
                    lineHeight = 16.sp
                )
            }
        }
    }
}

@Composable
private fun exitingRetrievalRequestItem(item: CollectionRequest, focusManager: FocusManager, update: () -> Unit = {}){
    var data: CollectionRequest = item
    Box() {
        Surface(
            color = MaterialTheme.colorScheme.surface,
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface),
            shadowElevation = 8.dp,
            modifier = Modifier.padding(8.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Column() {
                    Image(
                        painter = painterResource(id = DataSource.findAccount(data._receiverId)!!._imageId),
                        contentDescription = "",
                        modifier = Modifier.size(150.dp)
                    )
                }
                Column(
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier
                        .height(150.dp)
                        .padding(10.dp)
                ) {
                    Text(
                        text = DataSource.findAccount(data._receiverId)!!._firstName + " " + DataSource.findAccount(
                            data._receiverId
                        )!!._lastName,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(bottom = 5.dp)
                    )
                    Text(
                        text = data._sendDate.day.toString() + "/" + data._sendDate.month.toString() + "/" + data._sendDate.year.toString() + " " + data._sendDate.hours.toString() + ":" + data._sendDate.minutes.toString(),
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 13.sp
                    )
                    Row() {
                        Text(
                            text = stringResource(id = R.string.collectionrequest_message),
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = 12.sp,
                            lineHeight = 16.sp
                        )
                    }
                }
            }
        }
        FloatingActionButton(
            onClick = {
                focusManager.clearFocus()
                DataSource.removeCollectionRequest(data._collectionRequestId)
                update()
            },
            containerColor = MaterialTheme.colorScheme.errorContainer,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(50.dp),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_delete_24),
                tint = MaterialTheme.colorScheme.onErrorContainer,
                contentDescription = "",
                modifier = Modifier.padding(0.dp)
            )
        }
    }
}

@Composable
private fun noRequestMessage(message: String){
    Surface(
        color = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface),
        shadowElevation = 8.dp,
        modifier = Modifier.padding(8.dp)
    ){
        Text(
            text = message,
            modifier = Modifier.padding(16.dp),
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
    }
}