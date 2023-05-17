package com.example.electrize.pages

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.electrize.EnergeticViewModel
import com.example.electrize.data.DataSource
import com.example.electrize.dataStructures.Account
import com.example.electrize.dataStructures.Address
import com.example.electrize.dataStructures.Route
import com.example.electrize.ui.theme.ElectrizeTheme

@Composable
fun inspectRoute(collectionId: Int, navController: NavController, focusManager: FocusManager, viewModel: EnergeticViewModel){
    var route :  Route? = DataSource.findRouteFromCollectionId(collectionId)
    route(route, visitor = true, focusManager = focusManager, collectionId = collectionId, navController = navController, viewModel = viewModel)
}