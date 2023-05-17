package com.example.electrize.pages

import androidx.compose.runtime.Composable
import com.example.electrize.EnergeticViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.electrize.EnergeticScreen
import com.example.electrize.R
import com.example.electrize.data.DataSource
import com.example.electrize.dataStructures.BoughtProduct
import com.example.electrize.dataStructures.Product
import com.example.electrize.dataStructures.Route
import com.example.electrize.dataStructures.RouteType
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun customizeRoute(
    viewModel: EnergeticViewModel,
    navController: NavController,
    focusManager: FocusManager
){
    var shoppingCartProducts = viewModel.findShoppingCartProducts()

    val productNames = mutableListOf<String>()
    shoppingCartProducts.keys.forEach {
        productNames.add(it._productName)
    }

    var routeType = remember {
        mutableStateOf(RouteType.Cheapest)
    }
    var customIndex = remember {
        mutableStateOf(0)
    }
    var expanded by remember {
        mutableStateOf(false)
    }
    var selectedProductString = remember {
        mutableStateOf("")
    }
    var selectedProduct = remember {
        mutableStateOf( productNames.first() )
    }
    var selectedRouteCost = remember {
        mutableStateOf(0.0f)
    }
    var selectedRouteDistance = remember {
        mutableStateOf(0.0f)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background),
    ){
        Box(
            modifier = Modifier.weight(0.40f),
        ) {
            //Page title
            Text(
                text = stringResource(id = R.string.customize_route_title),
                fontSize = 27.sp,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .padding(10.dp, 5.dp),

            )
        }
        Box(
            modifier = Modifier.weight(0.60f),
        ) {
            ChooseRouteButtons(routeType, selectedRouteCost, selectedRouteDistance, focusManager)
        }
        Column(
            modifier = Modifier
                .weight(2.5f)
                .fillMaxWidth(),
        ){
            if (routeType.value == RouteType.Custom){
                Box(
                    modifier = Modifier
                        .padding(30.dp, 15.dp, 30.dp, 0.dp)
                ) {
                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = {
                            focusManager.clearFocus()
                            expanded = !expanded
                        },
                        modifier = Modifier
                            .fillMaxWidth(),
                    ) {
                        OutlinedTextField(
                            value = selectedProductString.value,
                            placeholder = { Text(text = selectedProduct.value) },
                            onValueChange = { selectedProductString.value = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .width(IntrinsicSize.Max)
                                .menuAnchor(), //https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#ExposedDropdownMenuBox(kotlin.Boolean,kotlin.Function1,androidx.compose.ui.Modifier,kotlin.Function1)
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                            keyboardActions = KeyboardActions(onDone = {focusManager.clearFocus()}),
                            singleLine = true,
                            shape = RoundedCornerShape(5.dp, 5.dp, 0.dp, 0.dp),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                containerColor = MaterialTheme.colorScheme.background,
                                cursorColor = MaterialTheme.colorScheme.primaryContainer,
                                focusedBorderColor = MaterialTheme.colorScheme.primaryContainer,
                                unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                            )
                        )

                        //Filter the options based on the input of the text field value
                        val filteredOptions = productNames.filter { it.contains(selectedProductString.value, ignoreCase = true) }
                        if (filteredOptions.isNotEmpty()){
                            ExposedDropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false }
                            ) {
                                filteredOptions.forEach { selectionOption ->
                                    DropdownMenuItem(
                                        text = { Text(selectionOption) },
                                        onClick = {
                                            focusManager.clearFocus()
                                            selectedProductString.value = ""
                                            selectedProduct.value = selectionOption
                                            if (customIndex.value == 0){
                                                customIndex.value = 1
                                            } else {
                                                customIndex.value = 0
                                            }
                                            expanded = false
                                        },
                                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                                    )

                                }
                            }
                        }
                    }
                }
            } else {
                Spacer(modifier = Modifier
                    .padding(0.dp, 15.dp, 0.dp, 0.dp))
            }
            RouteMockupImage(routeType.value, customIndex, selectedRouteCost, selectedRouteDistance, focusManager)
        }
        Column(
            modifier = Modifier.weight(0.5f),
        ) {
            ShowTotalDistance(selectedRouteDistance.value)
            ShowTotalCost(selectedRouteCost.value)
        }
        Button(
            onClick = { 
                focusManager.clearFocus()
                var max = 0
                for (r in DataSource.Routes) {
                    if (r._routeId > max) {
                        max = r._routeId
                    }
                }
                var productAmountPairs = viewModel._currentData._shoppingCart._productAmountList
                var boughtProducts = mutableListOf<BoughtProduct>()
                for (cartEntry in productAmountPairs.iterator()) {
                    boughtProducts.add(BoughtProduct(cartEntry.key, 0, cartEntry.value))
                }
                var tempRoute = Route(max+1, routeType.value, Date(), Date(), viewModel._currentData._account._accountId, boughtProducts)
                viewModel.setTempRoute(tempRoute)
                navController.navigate("${EnergeticScreen.Payment}") 
                },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp, 5.dp)
                .weight(0.5f),
            shape = RoundedCornerShape(5.dp, 5.dp, 5.dp, 5.dp),
        ) {
            Text(
                text = stringResource(id = R.string.customize_route_button),
                color = MaterialTheme.colorScheme.onPrimaryContainer,
            )
        }
    }
}

@Composable
fun ChooseRouteButtons(routeType: MutableState<RouteType>, selectedRouteCost: MutableState<Float>, selectedRouteDistance: MutableState<Float>, focusManager: FocusManager){
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp, 0.dp)
    ) {
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
            ),
            onClick = {
                focusManager.clearFocus()
                routeType.value = RouteType.Cheapest
                selectedRouteCost.value = 20.0f
                selectedRouteDistance.value = 30.0f
                      },
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
                .weight(1.0f),
            shape = RoundedCornerShape(5.dp, 5.dp, 5.dp, 5.dp),
        ) {
            Text(
                text = stringResource(id = R.string.customize_route_cheapest),
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                textAlign = TextAlign.Center,
            )
        }

        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
            ),
            onClick = {
                focusManager.clearFocus()
                routeType.value = RouteType.Shortest
                selectedRouteCost.value = 35.99f
                selectedRouteDistance.value = 0.10f
                      },
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
                .weight(1.0f),
            shape = RoundedCornerShape(5.dp, 5.dp, 5.dp, 5.dp),
        ) {
            Text(
                text = stringResource(id = R.string.customize_route_shortest),
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                textAlign = TextAlign.Center
            )
        }

        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
            ),
            onClick = {
                focusManager.clearFocus()
                routeType.value = RouteType.Custom
                selectedRouteCost.value = 24.99f
                selectedRouteDistance.value = 25.0f
                      },
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
                .weight(1.0f),
            shape = RoundedCornerShape(5.dp, 5.dp, 5.dp, 5.dp),
        ){
            Text(
                text = stringResource(id = R.string.customize_route_own),
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun RouteMockupImage(routeType: RouteType, customIndex: MutableState<Int>, selectedRouteCost: MutableState<Float>, selectedRouteDistance: MutableState<Float>, focusManager: FocusManager){
    var topRounding: Dp
    if (routeType == RouteType.Custom){
        topRounding = 0.dp
    } else {
        topRounding = 5.dp
    }

    Image(
        painter = painterResource(id = GetRouteMockImage(routeType, customIndex = customIndex.value)),
        contentDescription = "Mockup image",
        contentScale = ContentScale.FillHeight,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(30.dp, 0.dp, 30.dp, 15.dp)
            .background(Color(0xFFF5F7F8), RoundedCornerShape(topRounding, topRounding, 5.dp, 5.dp))
            .border(
                1.dp,
                MaterialTheme.colorScheme.primaryContainer,
                RoundedCornerShape(topRounding, topRounding, 5.dp, 5.dp)
            )
            .clip(RoundedCornerShape(topRounding, topRounding, 5.dp, 5.dp))
            .clickable {
                focusManager.clearFocus()
                if (customIndex.value == 0){
                    customIndex.value = 1
                    selectedRouteCost.value = 20.0f
                    selectedRouteDistance.value = 4.0f
                } else {
                    customIndex.value = 0
                    selectedRouteCost.value = 15.0f
                    selectedRouteDistance.value = 5.0f
                }
            },
    )
}

@Composable
fun ShowTotalCost(totalCost: Float){
    Row(
        modifier = Modifier
            .padding(30.dp, 5.dp)
    ) {
        Text(
            text = "Totale prijs:",
            textAlign = TextAlign.Left,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f) //Horizontal weight
        )
        Text(
            text = "€${totalCost}",
            textAlign = TextAlign.Right,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f) //Horizontal weight
        )
    }
}

@Composable
fun ShowTotalDistance(totalDistance: Float){
    Row(
        modifier = Modifier
            .padding(30.dp, 5.dp)
    ) {
        Text(
            text = "Totale afstand:",
            textAlign = TextAlign.Left,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f) //Horizontal weight
        )
        Text(
            text = "${totalDistance} km",
            textAlign = TextAlign.Right,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f) //Horizontal weight
        )
    }
}

fun GetRouteMockImage(routeType: RouteType, customIndex: Int = 0) : Int{
    if (routeType == RouteType.Cheapest){
        return R.drawable.cheapest_coolblue_fnac_mediamarkt
    }
    if (routeType == RouteType.Shortest){
        return R.drawable.shortest_coolblue_fnac
    }
    if (customIndex == 0) {
        return R.drawable.custom_route_one
    }
    return R.drawable.custom_route_two
}