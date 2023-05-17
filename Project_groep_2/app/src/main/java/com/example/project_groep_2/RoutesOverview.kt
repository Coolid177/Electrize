package com.example.project_groep_2

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import java.util.Calendar

@Composable
fun routesOverview(modifier: Modifier = Modifier, routes: List<Route>) {
    var presentRoutes = mutableListOf<Route>()
    var futureRoutes = mutableListOf<Route>()
    var pastRoutes = mutableListOf<Route>()
    for(route: Route in routes){
        if(Calendar.DAY_OF_MONTH == route.getDeliveryDate().day && Calendar.YEAR == route.getDeliveryDate().year && Calendar.MONTH == route.getDeliveryDate().month){
            presentRoutes.add(route)
        } else if (Calendar.DAY_OF_MONTH < route.getDeliveryDate().day && Calendar.YEAR <= route.getDeliveryDate().year && Calendar.MONTH <= route.getDeliveryDate().month){
            pastRoutes.add(route)
        } else {
        futureRoutes.add(route)
        }
    }
    Column(Modifier.fillMaxWidth()) {
        routeRowName(name = stringResource(id = R.string.Routes_present), routes = presentRoutes)
        routeRowName(name = stringResource(id = R.string.Routes_future), routes = futureRoutes)
        routeRowName(name = stringResource(id = R.string.Routes_past), routes = pastRoutes)
    }
}

@Composable
fun routeRowName(name: String, routes: List<Route>){
    var showList by remember(name) { mutableStateOf(true) }
    Row(horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .clickable { showList = !showList }
            .border(1.dp, color = Color.Black),
        verticalAlignment = Alignment.CenterVertically){
        Text(text = name,
            modifier = Modifier.padding(start = 10.dp))
        IconButton(onClick = { showList = !showList },
                   modifier = Modifier.padding(end = 10.dp)) {
            if(showList){
                Icon(painter = painterResource(id = R.drawable.expand_less), contentDescription = "Show less")
            }
            else{
                Icon(painter = painterResource(id = R.drawable.expand_more), contentDescription = "Show more")
            }
        }
    }
    if(showList) {
        LazyColumn{
            items(routes.size){ route ->
                routeRow(routes[route])
            }
        }
    }
}

@Composable
fun routeRow(route : Route){
    val route: Route = route
    Row(modifier = Modifier.fillMaxWidth().clickable { /* Todo */ }, horizontalArrangement  = Arrangement.SpaceAround) {
        Text(modifier = Modifier.padding(10.dp), text = route.getDeliveryDate().day.toString() + "/" + route.getDeliveryDate().month.toString() + "/" + route.getDeliveryDate().year.toString())
        Text(modifier = Modifier.padding(10.dp), text = route.getDuration().toString())
        Text(modifier = Modifier.padding(10.dp), text = route.getDistance().toString())
        Text(modifier = Modifier.padding(10.dp), text = route.getTotalPrice().toString()+"€")
    }
}