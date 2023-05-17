package com.example.electrize.pages

/**
 * @author Rune
 */

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.electrize.EnergeticScreen
import com.example.electrize.EnergeticViewModel
import com.example.electrize.R
import com.example.electrize.data.DataSource
import com.example.electrize.dataStructures.Route
import java.math.RoundingMode
import java.util.Calendar
@Composable
fun routes(viewModel: EnergeticViewModel, navController: NavController, focusManager: FocusManager) {
    var presentRoutes = viewModel.getPresentRoutes()
    var futureRoutes = viewModel.getFutureRoutes()
    var pastRoutes = viewModel.getPastRoutes()
    Column(Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        Accordion(title = stringResource(id = R.string.routes_present), routes = presentRoutes, navController = navController, focusManager = focusManager)
        Accordion(title = stringResource(id = R.string.routes_future), routes = futureRoutes, navController = navController, focusManager = focusManager)
        Accordion(title = stringResource(id = R.string.routes_past), routes = pastRoutes, navController = navController, focusManager = focusManager)
    }
}

@Composable
private fun AccordionHeader(title: String, onTapped:() -> Unit = {}, isExpanded: Boolean = false){
    val degrees = if (isExpanded) 180f else 0f
    Surface(
        color = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface),
        shadowElevation = 8.dp
    ){
        Row(
            modifier = Modifier
                .clickable { onTapped() }
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = title,
                modifier = Modifier.weight(1f),
                color = MaterialTheme.colorScheme.onSurface,
            )
            Surface(
                shape = CircleShape,
                color = MaterialTheme.colorScheme.surfaceTint,
            ){
                Icon(
                    painter = painterResource(id = R.drawable.baseline_expand_more_24),
                    contentDescription = "",
                    modifier = Modifier.rotate(degrees),
                    tint = MaterialTheme.colorScheme.onSurface,
                )
            }
        }
    }
}

@Composable
private fun AccordionElement(route: Route, navController: NavController, focusManager: FocusManager){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(8.dp)
            .clickable(onClick = {
                focusManager.clearFocus()
                navController.navigate("${EnergeticScreen.Routes.name}/${route._routeId}")
            })
            .fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ){
        Text(
            modifier = Modifier.padding(bottom = 10.dp, top = 10.dp).weight(0.25f),
            text = route._routeCollectionDate.day.toString() + "/" + route._routeCollectionDate.month.toString() + "/" + route._routeCollectionDate.year.toString(),
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier.padding(bottom = 10.dp, top = 10.dp).weight(0.25f),
            text = route.getRouteDuration().toString(),
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center
        )
        Text(modifier = Modifier.padding(bottom = 10.dp, top = 10.dp).weight(0.25f),
            text = route.getRouteDistance().toString() + "Km",
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center
        )
        Text(modifier = Modifier.padding(bottom = 10.dp, top = 10.dp).weight(0.25f),
            text = route.getRoutePrice().toBigDecimal().setScale(2, RoundingMode.UP).toFloat().toString()+"€",
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun Accordion(title: String, routes: List<Route>, navController: NavController, focusManager: FocusManager){
    var expanded by remember {
        focusManager.clearFocus()
        mutableStateOf(false)
    }
    AccordionHeader(title = title, isExpanded = expanded, onTapped = {
        focusManager.clearFocus()
        expanded = !expanded}
    )
    AnimatedVisibility(visible = expanded) {
        Surface(
            color = MaterialTheme.colorScheme.surface,
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface),
            shadowElevation = 1.dp,
            modifier = Modifier.padding(8.dp)
        ) {
            LazyColumn{
                items(routes.size){route->
                    AccordionElement(route = routes[route], navController, focusManager = focusManager)
                    Divider(color= MaterialTheme.colorScheme.onSurface, thickness = 1.dp)
                }
            }
        }
    }
}