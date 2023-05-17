package com.example.project_groep_2

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun routeDetails(order: Route){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 20.dp, end = 20.dp)) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(stringResource(id = R.string.Ordered_on))
            Text(order.getOrderedOnDate().day.toString() + "/" + order.getOrderedOnDate().month.toString() + "/" + order.getOrderedOnDate().year.toString())
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp), horizontalArrangement = Arrangement.SpaceBetween){
            Text(stringResource(id = R.string.Retrieve_on))
            Text(order.getDeliveryDate().day.toString() + "/" + order.getDeliveryDate().month.toString() + "/" + order.getDeliveryDate().year.toString())
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp), horizontalArrangement = Arrangement.SpaceBetween){
            Text(stringResource(id = R.string.Length_of_route))
            Text(order.getDistance().toString() + "km")
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp), horizontalArrangement = Arrangement.SpaceBetween){
            Text(stringResource(id = R.string.Route_duration))
            Text(order.getDuration().toString() + " minutes")
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp), horizontalArrangement = Arrangement.SpaceBetween){
            Text(stringResource(id = R.string.Number_of_shops))
            Text(order.getNumberOfshops().toString())
        }
        Text(text = stringResource(id = R.string.Products), fontSize = 20.sp)
        for (product: ProductOrder in order.getProducts()) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
                Column(modifier = Modifier.padding(top= 5.dp, end = 10.dp, bottom = 5.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.mouse),
                        contentDescription = "",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .size(130.dp)
                            .border(BorderStroke(1.dp, Color.Black))
                    )
                }
                Column() {
                    Text(product.getProduct().getName())
                    Text(product.getProductPrice().toString() + "€")
                    if (product.getQuantity() > 1) {
                        Text((product.getProductPrice() * product.getQuantity()).toString() + "€")
                    }
                    Text(product.getStore())
                }
                Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.End) {
                    Text(text = product.getQuantity().toString(), fontSize = 30.sp)
                }
            }
        }
        Text(text = stringResource(id = R.string.Total_price) + order.getTotalPrice() + "€")
        Button(onClick = { /*TODO*/ }) {
            Text(text = stringResource(id = R.string.View_route))
        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = stringResource(id = R.string.Start_route))
        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = stringResource(id = R.string.Send_retrievalrequest))
        }
    }
}