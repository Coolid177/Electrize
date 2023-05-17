package com.example.electrize.components

/**
 * @author Lukas
 */

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.electrize.EnergeticScreen
import com.example.electrize.EnergeticViewModel
import com.example.electrize.R
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun header(
    scaffoldState: ScaffoldState,
    scope: CoroutineScope,
    onQRCodeButtonClicked: () -> Unit = {},
    navController: NavController,
    focusManager: FocusManager
    ) {
    Surface(
        shadowElevation = 30.dp,
        tonalElevation = 50.dp,
        shape = RectangleShape,
    ) {
        Row(modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .fillMaxWidth()
            .height(45.dp)
            .padding(bottom = 5.dp, top = 5.dp)) {
            Column(verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.2f)) {
                hamburgerMenu(scaffoldState, scope)
            }

            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val noSearchBarPagesList = listOf<String>(
                EnergeticScreen.ShoppingCart.name,
                EnergeticScreen.CustomizeRoute.name,
                EnergeticScreen.Payment.name,
                EnergeticScreen.Routes.name,
                EnergeticScreen.Login.name,
            )

            if (!noSearchBarPagesList.contains(navBackStackEntry?.destination?.route)) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.75f)
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    searchBar(navController, onSearch = {
                        navController.navigate("${EnergeticScreen.SearchResults.name}")
                        focusManager.clearFocus()
                    })
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .padding(all = 5.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    IconButton(
                        onClick = onQRCodeButtonClicked,
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.qrcode_scanner),
                            contentDescription = "qr code scanner",
                            modifier = Modifier
                                .fillMaxSize()
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun searchBar(
    navController: NavController,
    onSearch: () -> Unit = {} ,
    placeholderText: String = "Zoeken",
) {
    var text by remember { mutableStateOf("") }
    val interactionSource = remember {
        MutableInteractionSource()
    }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    if (interactionSource.collectIsFocusedAsState().value && EnergeticScreen.Search.name != navBackStackEntry?.destination?.route) {
        navController.navigate("${EnergeticScreen.Search.name}")
    }

    BasicTextField(
        value = text,
        onValueChange = {
            text = it
        },
        singleLine = true,
        modifier = Modifier
            .fillMaxSize()
            .border(width = 1.dp, color = MaterialTheme.colorScheme.primary,shape = RoundedCornerShape(50.dp)),
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
        interactionSource = interactionSource,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = {
            onSearch()
        })
    ) { innerTextField ->
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.search),
                contentDescription = "zoeken",
                modifier = Modifier
                    .fillMaxHeight()
                    .width(40.dp)
                    .padding(8.dp)
            )
            Box(
                modifier = Modifier
                    .weight(1.0f)
            ) {
                if (text.isEmpty()) {
                    Text(
                        placeholderText,
                        style = LocalTextStyle.current.copy(
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),
                        )
                    )
                }
                innerTextField()
            }
        }
    }
}
