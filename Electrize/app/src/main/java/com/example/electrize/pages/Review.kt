package com.example.electrize.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.electrize.EnergeticScreen
import com.example.electrize.EnergeticViewModel
import com.example.electrize.R
import com.example.electrize.data.DataSource
import com.example.electrize.dataStructures.Product
import com.example.electrize.dataStructures.Review
import java.util.Date
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun review(viewModel: EnergeticViewModel, navController: NavController, focusManager: FocusManager){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        var getProduct: Product? = DataSource.findProduct(viewModel._currentData._currentProductid)
        if (getProduct == null){
            navController.navigate(EnergeticScreen.Home.name)
        }
        var product: Product = getProduct!!

        var storeList = mutableListOf<String>()
        product._pricingList.forEach{
            DataSource.findStore(it.first)?.let { it1 -> storeList.add(it1._storeName) }
        }

        var expanded = remember { mutableStateOf(false) }
        var storeLabel = remember { mutableStateOf("Winkel") }
        var storeInput = remember { mutableStateOf("") }

        var reviewScore = remember { mutableStateOf(3.0f) }
        var store = remember { mutableStateOf("") }
        var reviewTitle = remember { mutableStateOf("") }
        var reviewDescription = remember { mutableStateOf("") }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Review",
                textAlign = TextAlign.Center,
                fontSize = 27.sp,
                modifier = Modifier.padding(5.dp, 0.dp, 5.dp, 20.dp),
                color = MaterialTheme.colorScheme.onBackground,
            )
        }

        LazyColumn(
            modifier = Modifier
                .padding(30.dp, 5.dp)
                .fillMaxSize()
                .weight(3.0f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
        ) {
            item {
                Text(
                    text = product._productName,
                    textAlign = TextAlign.Center,
                    fontSize = 22.sp,
                    modifier = Modifier.padding(5.dp, 10.dp, 5.dp, 10.dp),
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 10.dp)
                        .background(
                            MaterialTheme.colorScheme.surfaceVariant,
                            RoundedCornerShape(8.dp)
                        )
                        .border(
                            1.dp,
                            MaterialTheme.colorScheme.onSurfaceVariant,
                            RoundedCornerShape(8.dp)
                        )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 5.dp)
                    ) {
                        for(i in 1..reviewScore.value.roundToInt()){
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_star_rate_24),
                                contentDescription = "",
                                tint = Color(0xFFFFC107),
                                modifier = Modifier
                                    .weight(1.0f),
                            )
                        }
                        for(i in reviewScore.value.roundToInt()..4){
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_star_rate_24),
                                contentDescription = "",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier
                                    .weight(1.0f),
                            )
                        }
                    }
                    Slider(
                        value = reviewScore.value,
                        onValueChange = {
                            reviewScore.value = it
                                        },
                        valueRange = 1.0f..5.0f,
                        steps = 3,
                        onValueChangeFinished = {
                            focusManager.clearFocus()
                        },
                        modifier = Modifier.padding(15.dp, 0.dp),
                    )
                }
            }

            item {
                ExposedDropdownMenuBox(
                    expanded = expanded.value,
                    onExpandedChange = {
                        expanded.value = !expanded.value
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 10.dp)
                ) {
                    TextField(
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant
                        ),
                        value = storeInput.value,
                        label = { Text(storeLabel.value) },
                        placeholder = { Text(text = store.value) },
                        onValueChange = {
                            storeInput.value = it
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .border(1.dp, MaterialTheme.colorScheme.onSurfaceVariant, RoundedCornerShape(8.dp))
                            .width(IntrinsicSize.Max)
                            .onFocusChanged {
                                if (it.isFocused) {
                                    storeLabel.value = "Winkel"
                                } else {
                                    storeLabel.value =
                                        "Winkel: " + store.value
                                }
                            }
                            .menuAnchor(), //https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#ExposedDropdownMenuBox(kotlin.Boolean,kotlin.Function1,androidx.compose.ui.Modifier,kotlin.Function1)
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded.value) },
                        singleLine = true,
                    )

                    //Filter the options based on the input of the text field value
                    val filteredOptions =
                        storeList.filter { it.contains(storeInput.value, ignoreCase = true) }
                    if (filteredOptions.isNotEmpty()) {
                        ExposedDropdownMenu(
                            expanded = expanded.value,
                            onDismissRequest = { expanded.value = false }
                        ) {
                            filteredOptions.forEach { selectionOption ->
                                DropdownMenuItem(
                                    text = { Text(selectionOption) },
                                    onClick = {
                                        store.value = selectionOption
                                        storeInput.value = ""
                                        viewModel._currentData._settings._language = selectionOption
                                        expanded.value = false
                                    },
                                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                                )
                            }
                        }
                    }
                }
            }

            item {
                OutlinedTextField(
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    ),
                    value = reviewTitle.value,
                    onValueChange = {
                        reviewTitle.value = it
                    },
                    modifier = Modifier
                        .padding(0.dp, 10.dp)
                        .fillMaxWidth(),
                    label = { Text("Title") },
                    placeholder = { Text("Review title") },
                )
            }

            item {
                OutlinedTextField(
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    ),
                    value = reviewDescription.value,
                    onValueChange = {
                        reviewDescription.value = it
                    },
                    modifier = Modifier
                        .padding(0.dp, 10.dp)
                        .fillMaxWidth(),
                    label = { Text("Beschrijving") },
                    placeholder = { Text("Een korte uitleg over wat je van het product vindt.") },
                    minLines = 5,
                )
            }

            item {
                Button(
                    onClick = {
                        var newReview = Review(reviewTitle = reviewTitle.value, reviewContent = reviewDescription.value, reviewStore = store.value, reviewRating = reviewScore.value, reviewWriterId = viewModel._currentData._account._accountId, reviewDate = Date())
                        product._reviewList.add(newReview)
                        navController.navigate("${EnergeticScreen.Products.name}/${viewModel._currentData._currentProductid}")
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 10.dp)
                ) {
                    Text(
                        text = "Post review",
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.padding(0.dp, 10.dp)
                    )
                }
            }
        }
    }
}