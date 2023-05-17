package com.example.electrize.pages

/**
 * @author Rune
 */

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.electrize.EnergeticViewModel
import com.example.electrize.R
import com.example.electrize.dataStructures.Account
import com.example.electrize.dataStructures.Settings
import com.example.electrize.dataStructures.theme
import com.example.electrize.ui.theme.ElectrizeTheme
import kotlin.math.exp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun settings(viewModel: EnergeticViewModel = EnergeticViewModel(), focusManager: FocusManager){
    var data: Account = viewModel._currentData._account
    var settings: Settings = viewModel._currentData._settings
    var firstNameInput = remember { mutableStateOf(data._firstName)}
    var lastNameInput = remember { mutableStateOf(data._lastName)}
    var cityInput = remember { mutableStateOf(data._addressId._city)}
    var postalCodeInput = remember { mutableStateOf(data._addressId._postalCode)}
    var address1Input = remember { mutableStateOf(data._addressId._addressLine1)}
    var address2Input = remember { mutableStateOf(data._addressId._addressLine2)}
    var emailInput = remember { mutableStateOf(data._email)}
    var phoneInput = remember { mutableStateOf(data._phoneNumber)}
    var darkModechecked = remember { mutableStateOf(settings._theme.value)}
    var languageInput = remember { mutableStateOf("")}
    var selectedLanguageInput = remember { mutableStateOf(settings._language)}
    var languageLabel = remember { mutableStateOf("Taal: " + settings._language) }
    val languageList = listOf<String>("Nederlands", "Engels")

    LazyColumn(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
    ) {
        item{
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                Surface(
                    color = MaterialTheme.colorScheme.onSurface,
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.dp, Color.LightGray),
                    shadowElevation = 8.dp,
                    modifier = Modifier.padding(8.dp)
                ) {
                    Image(
                        painter = painterResource(id = data._imageId),
                        contentDescription = "",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .size(150.dp)
                            .border(BorderStroke(1.dp, Color.Black)),
                    )
                }
            }
        }
        item {
            Text(
                text = "Account details",
                color = MaterialTheme.colorScheme.onSurface,
                style = typography.headlineSmall,
                modifier = Modifier.padding(8.dp)
            )
        }
        item{
            Surface(
                color = MaterialTheme.colorScheme.onSurface,
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, Color.LightGray),
                shadowElevation = 8.dp,
                modifier = Modifier.padding(8.dp)
            ) {
                TextField(
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    value = firstNameInput.value,
                    onValueChange = {
                                    firstNameInput.value = it
                                    viewModel._currentData._account._firstName = it
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    label = { Text("Voornaam") },
                    placeholder = { Text("e.x. John") },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_account_circle_24),
                            contentDescription = "",
                            modifier = Modifier.size(30.dp)
                        )
                    }
                )
            }
        }
        item{
            Surface(
                color = MaterialTheme.colorScheme.onSurface,
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, Color.LightGray),
                shadowElevation = 8.dp,
                modifier = Modifier.padding(8.dp)
            ) {
                TextField(
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    value = lastNameInput.value,
                    onValueChange = {
                        lastNameInput.value = it
                        viewModel._currentData._account._lastName = it
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    label = { Text("Achternaam") },
                    placeholder = { Text("e.x. Doe") },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_account_circle_24),
                            contentDescription = "",
                            modifier = Modifier.size(30.dp)
                        )
                    }
                )
            }
        }
        item{
            Surface(
                color = MaterialTheme.colorScheme.onSurface,
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, Color.LightGray),
                shadowElevation = 8.dp,
                modifier = Modifier.padding(8.dp)
            ) {
                TextField(
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    value = cityInput.value,
                    onValueChange = {
                        cityInput.value = it
                        viewModel._currentData._account._addressId._city = it
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    label = { Text("Plaats") },
                    placeholder = { Text("e.x. Hasselt") },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_home_24),
                            contentDescription = "",
                            modifier = Modifier.size(30.dp)
                        )
                    }
                )
            }
        }
        item{
            Surface(
                color = MaterialTheme.colorScheme.onSurface,
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, Color.LightGray),
                shadowElevation = 8.dp,
                modifier = Modifier.padding(8.dp)
            ) {
                TextField(
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    value = postalCodeInput.value,
                    onValueChange = {
                        postalCodeInput.value = it
                        viewModel._currentData._account._addressId._postalCode = it
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    label = { Text("Postcode") },
                    placeholder = { Text("e.x. 3500") },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_home_24),
                            contentDescription = "",
                            modifier = Modifier.size(30.dp)
                        )
                    }
                )
            }
        }
        item{
            Surface(
                color = MaterialTheme.colorScheme.onSurface,
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, Color.LightGray),
                shadowElevation = 8.dp,
                modifier = Modifier.padding(8.dp)
            ) {
                TextField(
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    value = address1Input.value,
                    onValueChange = {
                        address1Input.value = it
                        viewModel._currentData._account._addressId._addressLine1 = it
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    label = { Text("Address lijn 1") },
                    placeholder = { Text("e.x. Dorpsstraat 1") },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_home_24),
                            contentDescription = "",
                            modifier = Modifier.size(30.dp)
                        )
                    }
                )
            }
        }
        item{
            Surface(
                color = MaterialTheme.colorScheme.onSurface,
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, Color.LightGray),
                shadowElevation = 8.dp,
                modifier = Modifier.padding(8.dp)
            ) {
                TextField(
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    value = address2Input.value,
                    onValueChange = {
                        address2Input.value = it
                        viewModel._currentData._account._addressId._addressLine2 = it
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    label = { Text("Address lijn 2") },
                    placeholder = { Text("e.x. 1B") },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_home_24),
                            contentDescription = "",
                            modifier = Modifier.size(30.dp)
                        )
                    }
                )
            }
        }
        item{
            Text(
                text = "Contact informatie",
                color = MaterialTheme.colorScheme.onSurface,
                style = typography.headlineSmall,
                modifier = Modifier.padding(8.dp))
        }
        item{
            Surface(
                color = MaterialTheme.colorScheme.onSurface,
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, Color.LightGray),
                shadowElevation = 8.dp,
                modifier = Modifier.padding(8.dp)
            ) {
                TextField(
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    value = emailInput.value,
                    onValueChange = {
                        emailInput.value = it
                        viewModel._currentData._account._email = it
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    label = { Text("Email") },
                    placeholder = { Text("e.x. john.doe@gmail.com") },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_email_24),
                            contentDescription = "",
                            modifier = Modifier.size(30.dp)
                        )
                    }
                )
            }
        }
        item{
            Surface(
                color = MaterialTheme.colorScheme.onSurface,
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, Color.LightGray),
                shadowElevation = 8.dp,
                modifier = Modifier.padding(8.dp)
            ) {
                TextField(
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    value = phoneInput.value,
                    onValueChange = {
                        phoneInput.value = it
                        viewModel._currentData._account._phoneNumber = it
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    label = { Text("Telefoonnummer") },
                    placeholder = { Text("e.x. + 32477302081") },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_smartphone_24),
                            contentDescription = "",
                            modifier = Modifier.size(30.dp)
                        )
                    }
                )
            }
        }
        item{
            Text(
                text = "Andere",
                color = MaterialTheme.colorScheme.onSurface,
                style = typography.headlineSmall,
                modifier = Modifier.padding(8.dp)
            )
        }
        item{
            Surface(
                color = MaterialTheme.colorScheme.onSurface,
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, Color.LightGray),
                shadowElevation = 8.dp,
                modifier = Modifier.padding(8.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surface)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = if (viewModel._currentData._settings._theme == theme.DARK) R.drawable.baseline_mode_night_24 else R.drawable.baseline_wb_sunny_24),
                            contentDescription = "",
                            modifier = Modifier.padding(5.dp).size(30.dp),
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = stringResource(id = R.string.theme),
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier
                                .padding(start = 5.dp)
                        )
                    }
                    Switch(
                        checked = darkModechecked.value,
                        onCheckedChange = {
                            focusManager.clearFocus()
                            darkModechecked.value = it
                            if (it){
                                viewModel._currentData._settings._theme = theme.DARK
                            } else {
                                viewModel._currentData._settings._theme = theme.LIGHT
                            }

                                          },
                        modifier = Modifier.padding(end = 5.dp)
                    )
                }
            }
        }
        item{
            Surface(
                color = MaterialTheme.colorScheme.onSurface,
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, Color.LightGray),
                shadowElevation = 8.dp,
                modifier = Modifier.padding(8.dp)
            ) {
                var expanded by remember { mutableStateOf(false) }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surface)
                ){
                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = {
                            expanded = !expanded
                        },
                        modifier = Modifier
                            .fillMaxWidth(),
                    ) {
                        TextField(
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = MaterialTheme.colorScheme.surface
                            ),
                            value = languageInput.value,
                            label = { Text(languageLabel.value) },
                            placeholder = { Text(text = selectedLanguageInput.value)},
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_language_24),
                                    contentDescription = "",
                                    modifier = Modifier.size(30.dp)
                                )
                            },
                            onValueChange = {
                                languageInput.value = it
                                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .width(IntrinsicSize.Max)
                                .onFocusChanged {
                                    if (it.isFocused) {
                                        languageLabel.value = "Taal"
                                    } else {
                                        languageLabel.value =
                                            "Taal: " + viewModel._currentData._settings._language
                                    }
                                }
                                .menuAnchor(), //https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#ExposedDropdownMenuBox(kotlin.Boolean,kotlin.Function1,androidx.compose.ui.Modifier,kotlin.Function1)
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                            singleLine = true,
                        )

                        //Filter the options based on the input of the text field value
                        val filteredOptions = languageList.filter { it.contains(languageInput.value, ignoreCase = true) }
                        if (filteredOptions.isNotEmpty()){
                            ExposedDropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false }
                            ) {
                                filteredOptions.forEach { selectionOption ->
                                    DropdownMenuItem(
                                        text = { Text(selectionOption) },
                                        onClick = {
                                            selectedLanguageInput.value = selectionOption
                                            languageInput.value = ""
                                            viewModel._currentData._settings._language = selectionOption
                                            expanded = false
                                        },
                                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}