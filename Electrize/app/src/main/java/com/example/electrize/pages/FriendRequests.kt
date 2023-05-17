package com.example.electrize.pages

/**
 * @author Rune
 */

import android.annotation.SuppressLint
import android.graphics.Paint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.electrize.EnergeticScreen
import com.example.electrize.EnergeticViewModel
import com.example.electrize.R
import com.example.electrize.data.DataSource
import com.example.electrize.dataStructures.Account
import com.example.electrize.dataStructures.Address
import com.example.electrize.ui.theme.ElectrizeTheme

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun friendRequests(viewModel: EnergeticViewModel, focusManager: FocusManager) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                focusManager.clearFocus()
                showBottomSheet = true
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_person_add_alt_1_24),
                    contentDescription = "Stuur vriendschapsverzoek",
                    modifier = Modifier
                        .size(27.dp)
                )
            }
        },
    ) {
        var incomming by remember {
            mutableStateOf(viewModel.getIncomingFriendRequests())
        }
        var incommingCount by remember {
            mutableStateOf(incomming.size)
        }
        var outgoing by remember {
            mutableStateOf(viewModel.getOutgoingFriendRequests())
        }
        var outgoingCount by remember {
            mutableStateOf(outgoing.size)
        }
        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { showBottomSheet = false },
                sheetState = sheetState
            ) {
                LazyColumn() {
                    item{
                        Row(modifier = Modifier.padding(horizontal = 8.dp)){
                            searchBar()
                        }
                    }
                    DataSource.Accounts.forEach {
                        item {
                            if (!DataSource.requestExists(it._accountId)) {
                                var account = it
                                Surface(
                                    color = MaterialTheme.colorScheme.surface,
                                    shape = RoundedCornerShape(8.dp),
                                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface),
                                    shadowElevation = 8.dp,
                                    modifier = Modifier.padding(8.dp)
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .height(100.dp)
                                            .fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically,
                                    ) {
                                        Column(
                                        ) {
                                            Image(
                                                painter = painterResource(id = account._imageId),
                                                contentDescription = "",
                                                modifier = Modifier.size(100.dp)
                                            )
                                        }
                                        Column(
                                            modifier = Modifier.fillMaxHeight(),
                                        ) {
                                            Row(

                                            ) {
                                                Text(
                                                    text = account._firstName + " " + account._lastName,
                                                    modifier = Modifier.padding(10.dp),
                                                    fontSize = 18.sp
                                                )
                                            }
                                            Row(
                                                verticalAlignment = Alignment.Top
                                            ) {
                                                Text(
                                                    text = "GebruikersId: " + account._accountId.toString()
                                                        .chunked(4).joinToString(" "),
                                                    modifier = Modifier.padding(start = 10.dp),
                                                    fontSize = 12.sp
                                                )
                                            }
                                        }
                                        Column(
                                            modifier = Modifier
                                                .fillMaxHeight()
                                                .fillMaxWidth(),
                                            horizontalAlignment = Alignment.End
                                        ) {
                                            IconButton(
                                                onClick = {
                                                    focusManager.clearFocus()
                                                    viewModel.addOutgoingFriendrequest(
                                                        account._accountId
                                                    )
                                                    outgoing = viewModel.getOutgoingFriendRequests()
                                                    outgoingCount = outgoing.size
                                                    showBottomSheet = false
                                                },
                                                modifier = Modifier.padding(top = 26.dp)
                                            ) {
                                                Icon(
                                                    painter = painterResource(id = R.drawable.baseline_person_add_alt_1_24),
                                                    contentDescription = "",
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if(incommingCount > 0 || outgoingCount > 0){
            LazyColumn() {
                item {
                    Text(
                        text = stringResource(id = R.string.incoming_friend_requests),
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 24.sp,
                        modifier = Modifier.padding(start = 8.dp, end = 10.dp, top = 10.dp)
                    )
                }
                if(incomming.size > 0) {
                    items(incomming) { value ->
                        incomingRequest(data = value, viewModel = viewModel, onClickAccept = {
                            focusManager.clearFocus()
                            DataSource.acceptContactRequest(
                                value._accountId,
                                viewModel._currentData._account._accountId
                            )
                            incomming = viewModel.getIncomingFriendRequests()
                            incommingCount = incomming.size
                        },
                            onClickReject = {
                                focusManager.clearFocus()
                                DataSource.removeContactRequest(
                                    value._accountId,
                                    viewModel._currentData._account._accountId
                                )
                                incomming = viewModel.getIncomingFriendRequests()
                                incommingCount = incomming.size
                            })
                    }
                } else {
                    item{
                        noRequestMessage(message = stringResource(id = R.string.no_incoming_request))
                    }
                }
                item {
                    Text(
                        text = stringResource(id = R.string.outgoing_friend_requests),
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 24.sp,
                        modifier = Modifier.padding(start = 8.dp, end = 10.dp, top = 10.dp)
                    )
                }
                if(outgoing.size > 0){
                    items(outgoing) { value ->
                        outgoingRequest(data = value, viewModel, onClick = {
                            focusManager.clearFocus()
                            DataSource.removeContactRequest(viewModel._currentData._account._accountId, value._accountId)
                            outgoing = viewModel.getOutgoingFriendRequests()
                            outgoingCount = outgoing.size
                        })
                    }
                } else {
                    item{
                        noRequestMessage(message = stringResource(id = R.string.no_exiting_request))
                    }
                }
            }
        } else {
            Column(
                modifier = Modifier.fillMaxHeight()
            ) {
                Spacer(modifier = Modifier.weight(0.4f))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ){
                    Image(
                        painter = painterResource(id = R.drawable.cat_staring),
                        contentDescription = "",
                        modifier = Modifier.size(190.dp)
                    )
                }
                Spacer(modifier = Modifier.weight(0.1f))
                Row(
                    modifier = Modifier.fillMaxWidth()
                ){
                    Text(
                        text = stringResource(id = R.string.empty_friendrequests_header),
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
                        text = stringResource(id = R.string.empty_friendrequests_message),
                        modifier = Modifier.padding(start = 50.dp, end = 50.dp),
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = Modifier.weight(0.6f))
                Row(){
                    ExtendedFloatingActionButton(onClick = { showBottomSheet = true }, modifier = Modifier
                        .padding(30.dp, 10.dp)
                        .weight(1.0f)) {
                        Text(text = stringResource(id = R.string.send_friend_requests))
                    }
                }
                Spacer(modifier = Modifier.weight(0.1f))
            }
        }
    }
}

@Composable
private fun incomingRequest(data: Account, viewModel: EnergeticViewModel, onClickAccept: () -> Unit, onClickReject: () -> Unit){
    Surface(
        color = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface),
        shadowElevation = 8.dp,
        modifier = Modifier.padding(8.dp)
    ){
        Row(modifier = Modifier.fillMaxWidth()){
            Column() {
                Image(
                    painter = painterResource(id = data._imageId),
                    contentDescription = "",
                    modifier = Modifier.size(150.dp)
                )
            }
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .height(150.dp)
                    .padding(10.dp)
            ) {
                Row(){
                    Text(
                        text = data._firstName + " " + data._lastName,
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                }
                Row() {
                    Text(
                        text = "GebruikersId: " + data._accountId.toString().chunked(4).joinToString(" "),
                        fontSize = 15.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                }
                Row(){
                    Button(
                        onClick = {
                            onClickAccept()
                                  },
                        shape = RoundedCornerShape(5.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                        modifier = Modifier.padding(end = 5.dp)
                    ) {
                        Text(
                            text = "Voeg toe",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                    Button(
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.errorContainer),
                        onClick = {
                            onClickReject()
                          },
                        shape = RoundedCornerShape(5.dp),
                        modifier = Modifier.padding(start = 5.dp)
                    ){
                        Text(
                            text = "Wijs af",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onErrorContainer,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun outgoingRequest(data: Account, viewModel: EnergeticViewModel, onClick: () -> Unit){
    Surface(
        color = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface),
        shadowElevation = 8.dp,
        modifier = Modifier.padding(8.dp)
    ){
        Row(modifier = Modifier.fillMaxWidth()){
            Column() {
                Image(
                    painter = painterResource(id = data._imageId),
                    contentDescription = "",
                    modifier = Modifier.size(150.dp)
                )
            }
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .height(150.dp)
                    .padding(10.dp)
            ) {
                Row(){
                    Text(
                        text = data._firstName + " " + data._lastName,
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                }
                Row() {
                    Text(
                        text = "GebruikersId: " + data._accountId.toString().chunked(4).joinToString(" "),
                        fontSize = 15.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                }
                Row(){
                    Button(
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.errorContainer),
                        onClick = {
                            onClick()
                                  },
                        shape = RoundedCornerShape(5.dp),
                        modifier = Modifier.padding(start = 5.dp)
                    ){
                        Text(
                            text = "Cancel",
                            color = MaterialTheme.colorScheme.onErrorContainer,
                        )
                    }
                }
            }
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

@Composable
private fun searchBar() {
    var text by remember { mutableStateOf("") }
    val interactionSource = remember {
        MutableInteractionSource()
    }

    BasicTextField(
        value = text,
        onValueChange = {
            text = it
        },
        singleLine = true,
        modifier = Modifier
            .fillMaxSize()
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(50.dp)
            )
            .height(40.dp),
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
        interactionSource = interactionSource,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = {

        })
    ) { innerTextField ->
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.search),
                contentDescription = "Zoek",
                modifier = Modifier
                    .fillMaxHeight()
                    .width(40.dp)
                    .padding(8.dp)
            )
            Box(
                modifier = Modifier
                    .weight(1.0f)
            ) {
                innerTextField()
            }
        }
    }
}
