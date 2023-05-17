package com.example.project_groep_2

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.project_groep_2.ui.theme.Project_groep_2Theme
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun ChooseRoutePagePreview() {
    Project_groep_2Theme {
        Surface {
            ChooseRoutePage()
        }
    }
}

@Composable
fun ChooseRoutePage() {
    Column(
        modifier = Modifier
    ){
        Box(modifier = Modifier.weight(0.3f)) {
            PageTitle(title = "Kies uw route")
        }
        Box(modifier = Modifier.weight(1.0f)) {
            ChooseRouteButtons()
        }
        Box(modifier = Modifier.weight(2.0f)){
            RouteMockupImage()
        }
        Box(modifier = Modifier.weight(0.3f)){
            ShowTotalCost(5.0f)
        }

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp, 5.dp)
                .weight(0.3f),
        ) {
            Text(
                text = "Verder naar checkout",
            )
        }
    }
}

@Composable
fun ChooseRouteButtons(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp, 0.dp)
    ) {
        Button(
            onClick = { /*TODO*/},
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
                .weight(1.0f),
        ) {
            Text(
                text = "Korstre route"
            )
        }

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
                .weight(1.0f),
        ) {
            Text(
                text = "Goedkoopste route"
            )
        }

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
                .weight(1.0f),
        ){
            Text(
                text = "Zelf route samenstellen"
            )
        }
    }
}

@Composable
fun RouteMockupImage(){
    var mockupImage = R.drawable.maps_parking_coolblue_fnac

    Image(
        painter = painterResource(id = mockupImage),
        contentDescription = "Mockup image",
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(30.dp, 15.dp)
    )
}

@Composable
fun ShowTotalCost(totalCost: Float){
    Row(
        modifier = Modifier
            .padding(30.dp, 5.dp)
    ) {
        Text(
            text = "Total:",
            textAlign = TextAlign.Left,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f) //Horizontal weight
        )
        Text(
            text = "€${totalCost}",
            textAlign = TextAlign.Right,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f) //Horizontal weight
        )
    }
}