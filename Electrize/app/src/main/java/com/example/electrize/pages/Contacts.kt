package com.example.electrize.pages

/**
 * @author Rune
 */

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.electrize.EnergeticScreen
import com.example.electrize.EnergeticViewModel
import com.example.electrize.R
import com.example.electrize.data.DataSource
import com.example.electrize.dataStructures.Account

@Composable
fun contacts(viewModel: EnergeticViewModel, navController: NavController, focusManager: FocusManager){
    var contacts by remember{ mutableStateOf(viewModel.getContacts())}
    var contacsCount by remember{ mutableStateOf(contacts.size) }
    if(contacsCount > 0){
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 150.dp),
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize()
        ) {
            this.items(contacts) { contact ->
                contactItem(data = contact,  viewModel = viewModel, focusManager = focusManager, update = {
                    contacts = viewModel.getContacts()
                    contacsCount = contacts.size
                })
            }
        }
    } else {
        emptyMessage(navController = navController)
    }
}

@Composable
private fun emptyMessage(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Spacer(modifier = Modifier.weight(0.4f))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            Image(
                painter = painterResource(id = R.drawable.cat_crying),
                contentDescription = "",
                modifier = Modifier.size(190.dp)
            )
        }
        Spacer(modifier = Modifier.weight(0.1f))
        Row(
            modifier = Modifier.fillMaxWidth()
        ){
            Text(
                text = stringResource(id = R.string.empty_contacts_header),
                color = MaterialTheme.colorScheme.onBackground,
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
                text = stringResource(id = R.string.empty_contacts_message),
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(start = 50.dp, end = 50.dp),
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.weight(0.6f))
        Row(){
            ExtendedFloatingActionButton(onClick = { navController.navigate(EnergeticScreen.FriendRequests.name) }, modifier = Modifier
                .padding(30.dp, 10.dp)
                .weight(1.0f)) {
                Text(text = stringResource(id = R.string.go_to_friendrequests))
            }
        }
        Spacer(modifier = Modifier.weight(0.1f))
    }
}

@Composable
private fun contactItem(data: Account, viewModel: EnergeticViewModel, focusManager: FocusManager, update: () -> Unit = {}){
    var data: Account = data
    val openDialog = remember{ mutableStateOf(false) }
    var accountIdToDelete = remember{ mutableStateOf(0) }
    Box(
        contentAlignment = Alignment.TopEnd
    ){
        Surface(
            color = MaterialTheme.colorScheme.background,
            shape = RoundedCornerShape(8.dp),
            //border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurfaceVariant),
            shadowElevation = 8.dp,
            modifier = Modifier.padding(10.dp)
        ) {
            Column(
                Modifier
                    .width(IntrinsicSize.Max)
                    .background(MaterialTheme.colorScheme.surfaceVariant)) {
                Image(
                    painter = painterResource(id = data._imageId),
                    contentDescription = "",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.fillMaxSize()
                )
                Text(
                    text = data._firstName + " " + data._lastName,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .padding(start = 10.dp, end = 10.dp, bottom = 5.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = stringResource(id = R.string.userid) + ": " + data._accountId.toString().chunked(4).joinToString(" "),
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 10.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 5.dp)
                )
            }
        }        
        FloatingActionButton(onClick = {
            focusManager.clearFocus()
            openDialog.value = true
            accountIdToDelete.value = data._accountId
       },
            modifier = Modifier.size(50.dp),
            containerColor = MaterialTheme.colorScheme.errorContainer,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_delete_24),
                contentDescription = "",
                tint = MaterialTheme.colorScheme.onErrorContainer,
            )
        }
    }
    if(openDialog.value){
        AlertDialog(
            onDismissRequest = { openDialog.value = false },
            title = { Text(text = "Verwijder vriend", color = MaterialTheme.colorScheme.onSurface) },
            text = { Text("Bent u zeker dat u deze vriend wilt verwijderen? U kunt deze altijd later opnieuw toevoegen!", color = MaterialTheme.colorScheme.onSurface) },
            confirmButton = {
                OutlinedButton(
                    onClick = {
                        openDialog.value = false
                        DataSource.deleteContact(viewModel._currentData._account._accountId, accountIdToDelete.value)
                        update()
                    },
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                ) {
                    Text("Delete", color = MaterialTheme.colorScheme.onPrimaryContainer)
                }
            },
            dismissButton = {
                OutlinedButton(
                    onClick = {
                        openDialog.value = false
                    },
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                ) {
                    Text("Cancel", color = MaterialTheme.colorScheme.onPrimaryContainer)
                }
            },
            containerColor = MaterialTheme.colorScheme.surface
        )
    }
}

