package com.example.electrize.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.electrize.R
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import java.nio.file.WatchEvent
import java.text.DecimalFormat
import kotlin.math.exp
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun filterMenu(visibility: MutableState<Boolean>, focusManager: FocusManager) {
    if (visibility.value) {
        Surface(
            shadowElevation = 30.dp,
            tonalElevation = 50.dp,
            shape = RectangleShape,
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .zIndex(10.0f),
            color = MaterialTheme.colorScheme.background
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 30.dp, end = 30.dp)
            ) {
                item {
                    Row(
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .fillParentMaxWidth()
                            .fillParentMaxHeight(0.3f)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth(0.5f)
                        ) {
                            Text(
                                text = "Kleur",
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        }
                        Column(
                            horizontalAlignment = Alignment.End,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth()
                        ) {
                            var expanded = remember { mutableStateOf(false) }
                            var selectedItem = remember { mutableStateOf("alle kleuren") }

                            ExposedDropdownMenuBox(
                                expanded = expanded.value,
                                onExpandedChange = {
                                    expanded.value = !expanded.value
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(0.dp, 5.dp),
                            ) {
                                TextField(
                                    colors = TextFieldDefaults.textFieldColors(
                                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                                    ),
                                    readOnly = true,
                                    value = selectedItem.value,
                                    onValueChange = {
                                        selectedItem.value = it
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(8.dp))
                                        .border(
                                            1.dp,
                                            MaterialTheme.colorScheme.onSurfaceVariant,
                                            RoundedCornerShape(8.dp)
                                        )
                                        .width(IntrinsicSize.Max)
                                        .menuAnchor(), //https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#ExposedDropdownMenuBox(kotlin.Boolean,kotlin.Function1,androidx.compose.ui.Modifier,kotlin.Function1)
                                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded.value) },
                                    singleLine = true,
                                )
                                ExposedDropdownMenu(
                                    expanded = expanded.value,
                                    onDismissRequest = {
                                        focusManager.clearFocus()
                                        expanded.value = false
                                    },
                                ) {
                                    DropdownMenuItem(
                                        text = {
                                            Text(
                                                text = "wit",
                                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                            )
                                               },
                                        onClick = { selectedItem.value = "wit" })
                                    DropdownMenuItem(
                                        text = {
                                            Text(
                                                text = "grijs",
                                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                            )
                                        },
                                        onClick = { selectedItem.value = "grijs" })
                                    DropdownMenuItem(
                                        text = {
                                            Text(
                                                text = "zwart",
                                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                            )
                                        },
                                        onClick = { selectedItem.value = "zwart" })
                                    DropdownMenuItem(
                                        text = {
                                            Text(
                                                text = "groen",
                                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                            )
                                        },
                                        onClick = { selectedItem.value = "groen" })
                                    DropdownMenuItem(
                                        text = {
                                            Text(
                                                text = "blauw",
                                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                            )
                                        },
                                        onClick = { selectedItem.value = "blauw" })
                                    DropdownMenuItem(
                                        text = {
                                            Text(
                                                text = "geel",
                                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                            )
                                        },
                                        onClick = { selectedItem.value = "geel" })
                                    DropdownMenuItem(
                                        text = {
                                            Text(
                                                text = "andere",
                                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                            )
                                        },
                                        onClick = { selectedItem.value = "andere" })
                                    DropdownMenuItem(
                                        text = {
                                            Text(
                                                text = "alle kleuren",
                                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                            )
                                        },
                                        onClick = { selectedItem.value = "all kleuren" })
                                }
                            }
                        }
                    }
                }
                item {
                    Row(
                        modifier = Modifier
                            .fillParentMaxWidth()
                            .fillParentMaxHeight(0.4f)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            var sliderPosition = remember { mutableStateOf(0f..100f) }
                            Row(
                                modifier = Modifier
                                    .fillMaxHeight(0.5f)
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start
                            ) {
                                Text(
                                    text = "Prijs",
                                    modifier = Modifier
                                        .weight(1.0f)
                                        .padding(horizontal = 2.dp),
                                    textAlign = TextAlign.Left
                                )
                                Spacer(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(0.5f)
                                )

                                val decimalFormat = DecimalFormat("#.##")
                                OutlinedTextField(
                                    value = decimalFormat.format(sliderPosition.value.start).toString(),
                                    modifier = Modifier
                                        .weight(3.0f)
                                        .fillMaxWidth()
                                        .padding(horizontal = 2.dp),
                                    trailingIcon = {
                                        Text(text = "€")
                                    },
                                    colors = TextFieldDefaults.outlinedTextFieldColors(
                                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                                    ),
                                    keyboardOptions = KeyboardOptions(
                                        keyboardType = KeyboardType.NumberPassword,
                                        imeAction = ImeAction.Done
                                    ),
                                    keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                                    singleLine = true,
                                    onValueChange = {
                                        if (it.toFloat() <= sliderPosition.value.endInclusive) {
                                            sliderPosition.value =
                                                it.toFloat()..sliderPosition.value.endInclusive
                                        } else {
                                            sliderPosition.value =
                                                sliderPosition.value.endInclusive..sliderPosition.value.endInclusive
                                        }
                                    })

                                Text(
                                    text = " - ",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(0.5f)
                                )

                                OutlinedTextField(
                                    value = decimalFormat.format(sliderPosition.value.endInclusive).toString(),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(3.0f)
                                        .padding(horizontal = 2.dp),
                                    trailingIcon = {
                                        Text(text = "€")
                                    },
                                    colors = TextFieldDefaults.outlinedTextFieldColors(
                                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                                    ),
                                    keyboardOptions = KeyboardOptions(
                                        keyboardType = KeyboardType.NumberPassword,
                                        imeAction = ImeAction.Done
                                    ),
                                    keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                                    singleLine = true,
                                    onValueChange = {
                                        if (it.toFloat() >= sliderPosition.value.start) {
                                            sliderPosition.value =
                                                sliderPosition.value.start..it.toFloat()
                                        } else {
                                            sliderPosition.value =
                                                sliderPosition.value.start..sliderPosition.value.start
                                        }
                                    })
                            }
                            Row(
                                modifier = Modifier
                                    .padding(0.dp, 0.dp)
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.Top,
                            ) {
                                RangeSlider(
                                    value = sliderPosition.value,
                                    onValueChange = { sliderPosition.value = it },
                                    valueRange = 0f..100f,
                                    onValueChangeFinished = {
                                        focusManager.clearFocus()
                                    },
                                )
                            }
                        }
                    }
                }
                item {
                    Column(
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .fillParentMaxWidth()
                            .fillParentMaxHeight(0.4f)
                    ) {
                        Text(
                            text = "Min rating",
                            modifier = Modifier.padding(bottom = 10.dp),
                        )
                        var sliderPosition = remember { mutableStateOf(1f) }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start,
                        ) {
                            for (i in 1..5) {
                                var starColor: Color
                                if (i <= sliderPosition.value.roundToInt()) {
                                    starColor = Color(0xFFFFC107)
                                } else {
                                    starColor = MaterialTheme.colorScheme.onSurfaceVariant
                                }
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_star_rate_24),
                                    contentDescription = "",
                                    tint = starColor,
                                    modifier = Modifier
                                        .weight(1.0f),
                                )
                            }
                        }
                        Slider(
                            value = sliderPosition.value,
                            onValueChange = {
                                sliderPosition.value = it
                            },
                            valueRange = 1.0f..5.0f,
                            steps = 3,
                            onValueChangeFinished = {
                                focusManager.clearFocus()
                            },
                            modifier = Modifier.padding(0.dp),
                        )
                    }
                }
            }
        }
    }
}