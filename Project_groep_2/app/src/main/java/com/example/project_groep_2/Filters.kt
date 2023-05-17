package com.example.project_groep_2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

@Composable
fun filterMenu(visibility: MutableState<Boolean>) {
    if (visibility.value) {
        LazyColumn(modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(Color.White)
            .zIndex(10.0f)) {
            item {
                Row() {
                    Column() {

                    }
                    Column() {

                    }
                }
            }
            item {
                Row() {
                    Column() {

                    }
                    Column() {

                    }
                }
            }
            item {
                Row() {
                    Column() {

                    }
                    Column() {

                    }
                }
            }
            item {
                Row() {
                    Column() {

                    }
                    Column() {

                    }
                }
            }
            item {
                Row() {
                    Column() {

                    }
                    Column() {

                    }
                }
            }
            item {
                Row() {
                    Column() {

                    }
                    Column() {

                    }
                }
            }
        }
    }
}