package com.example.project_groep_2

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun settings(data: Profile){
    var data: Profile = data
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround, verticalAlignment = Alignment.Bottom){
            Column(modifier = Modifier
                .weight(2f)
                .padding(start = 10.dp, top = 20.dp)){
                Text(text = stringResource(id = R.string.Personalia), fontSize = 27.sp)
                Divider(color = Color.Gray, thickness = 2.dp, modifier = Modifier.fillMaxWidth())
                Text(text = data.getName() + " " + data.getLastName(), modifier = Modifier.padding(top = 3.dp))
                Text(text = data.getCity() + " " + data.getPostalCode(), modifier = Modifier.padding(top = 3.dp))
                Text(text = data.getStreetName() + " " + data.getHouseNumber(), modifier = Modifier.padding(top = 3.dp))
            }
            Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Bottom) {
                Image(
                    painter = painterResource(id = R.drawable.profile_picture_man),
                    contentDescription = "",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(110.dp)
                        .border(BorderStroke(1.dp, Color.Black))
                )
            }
        }
        Row(modifier = Modifier.fillMaxWidth()){
            Column(modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 35.dp)) {
                Text(text = stringResource(id = R.string.Contact), fontSize = 27.sp)
                Divider(color = Color.Gray, thickness = 2.dp, modifier = Modifier.fillMaxWidth())
                Text(text = data.getEmail(), modifier = Modifier.padding(top = 3.dp))
                Text(text = data.getPhoneNumber(), modifier = Modifier.padding(top = 3.dp))
            }
        }
        Text(text = stringResource(id = R.string.Other), modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 35.dp), fontSize = 27.sp)
        Divider(color = Color.Gray, thickness = 2.dp, modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp))
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp),
        verticalAlignment = Alignment.CenterVertically){
            Column(modifier = Modifier.weight(1f)){
                Text(text = stringResource(id = R.string.Language), modifier = Modifier.padding(top = 3.dp))
            }
            Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.End) {
                val languages = listOf("English", "Nederlands")
                var expanded by remember { mutableStateOf(true) }
                var selectedOptionText by remember { mutableStateOf(languages[1]) }
                var textFieldSize by remember { mutableStateOf(Size.Zero) }

                OutlinedTextField(
                    value = selectedOptionText,
                    onValueChange = {selectedOptionText = it},
                    label = {Text(text = stringResource(id = R.string.Language))},
                    readOnly = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .onGloballyPositioned { coordinates ->
                            textFieldSize = coordinates.size.toSize()
                        },
                    trailingIcon = {
                        if(expanded) {
                            Icon(
                                painter = painterResource(id = R.drawable.expand_less),
                                contentDescription = "Show less",
                                modifier = Modifier.clickable { expanded = !expanded }
                            )
                        } else {
                            Icon(
                                painter = painterResource(id = R.drawable.expand_more),
                                contentDescription = "Show more",
                                modifier = Modifier.clickable { expanded = !expanded }
                            )
                        }
                    }
                )

                /* TODO DROPDOWNMENU */
            }
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp), horizontalArrangement = Arrangement.SpaceBetween){
            Column(modifier = Modifier.weight(1f)) {
                Text(text = stringResource(id = R.string.Dark_theme), modifier = Modifier.padding(top = 3.dp))
            }
            Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.End) {
                val checkedState = remember { mutableStateOf(true) }
                Checkbox(checked = checkedState.value, onCheckedChange = {checkedState.value = it})
            }
        }
    }
}