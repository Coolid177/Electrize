package com.example.electrize.pages

import android.graphics.Paint.Align
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.electrize.Energetic
import com.example.electrize.EnergeticScreen
import com.example.electrize.EnergeticViewModel
import com.example.electrize.R
import kotlinx.coroutines.launch
import kotlin.math.log

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun login (viewModel: EnergeticViewModel, navController: NavController, loggedIn: MutableState<Boolean>, loggedInMessage: Boolean = true) {
    var showLoggedInMessage = remember { mutableStateOf(loggedInMessage)}
    var login = remember { mutableStateOf(true)}
    var emailInput = remember { mutableStateOf("")}
    var passwordInput = remember { mutableStateOf("")}

    var firstNameInput = remember { mutableStateOf("")}
    var lastNameInput = remember { mutableStateOf("")}
    var cityInput = remember { mutableStateOf("")}
    var postalCodeInput = remember { mutableStateOf("")}
    var address1Input = remember { mutableStateOf("")}
    var address2Input = remember { mutableStateOf("")}
    var phoneInput = remember { mutableStateOf("")}

    var loginFieldWeight: Float = 4.0f

    if (showLoggedInMessage.value) {
        loginFieldWeight = 2.0f
    }
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .padding(30.dp, 0.dp),
    ) {
        if (showLoggedInMessage.value){
            Column(modifier = Modifier
                .padding(0.dp, 5.dp)
                .weight(2.0f)
                .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom,
            ) {
                Text(
                    text = "U moet ingelogd zijn om deze pagina te bezoeken",
                    color = MaterialTheme.colorScheme.onErrorContainer,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .border(1.dp, MaterialTheme.colorScheme.onErrorContainer, RoundedCornerShape(5.dp))
                        .background(MaterialTheme.colorScheme.errorContainer, RoundedCornerShape(5.dp))
                        .clip(RoundedCornerShape(5.dp))
                        .padding(10.dp, 30.dp)
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .padding(0.dp, 5.dp)
                .weight(loginFieldWeight),
            verticalArrangement = Arrangement.Bottom,
        ) {
            var titleText: String
            if (login.value) {
                titleText = "Log in"
            } else {
                titleText = "Sign up"
            }
            item {
                Text(
                    text = titleText,
                    fontSize = 27.sp, modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 10.dp),
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
            item {
                OutlinedTextField(
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    value = emailInput.value,
                    onValueChange = {
                        emailInput.value = it
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    label = { Text("Email") },
                    placeholder = { Text("e.x. jhon.doe@gmail.com") },
                )
            }
            item {
                OutlinedTextField(
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    value = passwordInput.value,
                    onValueChange = {
                        passwordInput.value = it
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    label = { Text("Wachtwoord") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    placeholder = { Text("e.x. Paswoord123 (niet gebruiken)") },
                )
            }
            if (!login.value) {
                item {
                    OutlinedTextField(
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        ),
                        value = firstNameInput.value,
                        onValueChange = {
                            firstNameInput.value = it
                        },
                        modifier = Modifier
                            .fillMaxWidth(),
                        label = { Text("Voornaam") },
                        placeholder = { Text("e.x. John") },
                    )
                }

                item {
                    OutlinedTextField(
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        ),
                        value = lastNameInput.value,
                        onValueChange = {
                            lastNameInput.value = it
                        },
                        modifier = Modifier
                            .fillMaxWidth(),
                        label = { Text("Achternaam") },
                        placeholder = { Text("e.x. Doe") },
                    )
                }

                item {
                    Row() {
                        OutlinedTextField(
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = MaterialTheme.colorScheme.surface
                            ),
                            value = cityInput.value,
                            onValueChange = {
                                cityInput.value = it
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(0.60f),
                            shape = RoundedCornerShape(5.dp, 0.dp, 0.dp, 5.dp),
                            label = { Text("Plaats") },
                            placeholder = { Text("e.x. Hasselt") },
                        )
                        OutlinedTextField(
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = MaterialTheme.colorScheme.surface
                            ),
                            value = postalCodeInput.value,
                            onValueChange = {
                                postalCodeInput.value = it
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(0.40f),
                            shape = RoundedCornerShape(0.dp, 5.dp, 5.dp, 0.dp),
                            label = { Text("Postcode") },
                            placeholder = { Text("e.x. 3500") },
                        )
                    }
                }

                item {
                    Row() {
                        OutlinedTextField(
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = MaterialTheme.colorScheme.surface
                            ),
                            value = address1Input.value,
                            onValueChange = {
                                address1Input.value = it
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(0.60f),
                            shape = RoundedCornerShape(5.dp, 0.dp, 0.dp, 5.dp),
                            label = { Text("Address") },
                            placeholder = { Text("e.x. Straat 24") },
                        )
                        OutlinedTextField(
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = MaterialTheme.colorScheme.surface
                            ),
                            value = address2Input.value,
                            onValueChange = {
                                address2Input.value = it
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(0.40f),
                            shape = RoundedCornerShape(0.dp, 5.dp, 5.dp, 0.dp),
                            label = { Text("bus") },
                            placeholder = { Text("e.x. bus 2") },
                        )
                    }
                }

                item {
                    OutlinedTextField(
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        ),
                        value = phoneInput.value,
                        onValueChange = {
                            phoneInput.value = it
                        },
                        modifier = Modifier
                            .fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        label = { Text("Telefoonnummer") },
                        placeholder = { Text("e.x. 0498123456") },
                    )
                }
            }
        }

        var mainButtonText: String
        var subButtonText: String

        if (login.value) {
            mainButtonText = "Log in"
            subButtonText = "Nog geen account? Maak er hier een aan."
        } else {
            mainButtonText = "Registreer nu"
            subButtonText = "Al een account? Log hier in"
        }

        Column(
            modifier = Modifier
                .weight(1.0f)
                .padding(0.dp, 20.dp),
        ) {
            Button(
                onClick = {
                    viewModel._currentData._logged_in = true
                    loggedIn.value = true
                    navController.navigate("${viewModel._currentData._destined_page}")
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.60f)
                    .padding(0.dp, 5.dp)
            ) {
                Text(
                    text = mainButtonText,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                )
            }

            Button(
                onClick = {
                    showLoggedInMessage.value = false
                    login.value = !login.value
                          },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.background
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.40f)
                    .padding(0.dp, 5.dp),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    text = subButtonText,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
        }
    }
}