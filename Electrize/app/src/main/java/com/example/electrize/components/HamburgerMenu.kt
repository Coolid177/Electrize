package com.example.electrize.components

/**
 * @author Lukas
 */

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.electrize.EnergeticViewModel
import com.example.electrize.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun hamburgerMenu(scaffoldState: ScaffoldState, scope: CoroutineScope) {
   IconButton(onClick = {
        scope.launch {
            scaffoldState.drawerState.apply {
                if (isClosed){
                    open()
                } else close()
             }
        }
    },
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()) {
        Icon(painter = painterResource(R.drawable.hamburger_menu),
            contentDescription = "expand menu",
            tint = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight())
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun hamburgerDrawer(
    loggedIn: MutableState<Boolean>,
    scaffoldState: ScaffoldState,
    scope: CoroutineScope,
    onContactsButtonClicked: () -> Unit = {},
    onSettingsButtonClicked: () -> Unit = {},
    onCompareButtonClicked: () -> Unit = {},
    onFriendRequestButtonClicked: () -> Unit = {},
    onCollectionRequestsClicked: () -> Unit = {},
    onLoginSignupButtonClicked: () -> Unit = {},
    onLogoutButtonClicked: () -> Unit = {},
    ) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        if (! loggedIn.value) {
            NavigationDrawerItem(
                label = {
                    Text(
                        text = stringResource(id = R.string.login_register),
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                },
                selected = false,
                onClick = {
                    onLoginSignupButtonClicked()
                    scope.launch { scaffoldState.drawerState.apply { close() } }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_login_24),
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.size(24.dp)
                    )
                }
            )
        } else {
            NavigationDrawerItem(
                label = {
                    Text(
                        text = stringResource(id = R.string.contacts),
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                },
                selected = false,
                onClick = {
                    onContactsButtonClicked()
                    scope.launch { scaffoldState.drawerState.apply { close() } }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.contacts),
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.size(24.dp)
                    )
                }
            )
            NavigationDrawerItem(
                label = {
                    Text(
                        text = stringResource(id = R.string.settings),
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                },
                selected = false,
                onClick = {
                    onSettingsButtonClicked()
                    scope.launch { scaffoldState.drawerState.apply { close() } }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.settings),
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.size(24.dp)
                    )
                }
            )
            NavigationDrawerItem(
                label = {
                    Text(
                        text = stringResource(id = R.string.friend_requests),
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                },
                selected = false,
                onClick = {
                    onFriendRequestButtonClicked()
                    scope.launch { scaffoldState.drawerState.apply { close() } }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.friend_requests),
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.size(24.dp)
                    )
                }
            )
            NavigationDrawerItem(
                label = {
                    Text(
                        text = stringResource(id = R.string.collection_requests),
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                },
                selected = false,
                onClick = {
                    onCollectionRequestsClicked()
                    scope.launch { scaffoldState.drawerState.apply { close() } }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.paper_plane),
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.size(24.dp)
                    )
                }
            )
        }
        NavigationDrawerItem(
            label = { Text(
                text = stringResource(id = R.string.compare),
                color = MaterialTheme.colorScheme.onBackground,
            )},
            selected = false,
            onClick = {
                onCompareButtonClicked()
                scope.launch { scaffoldState.drawerState.apply { close() } }
            },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.compare),
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.size(24.dp)
                )
            }
        )

        if (loggedIn.value){
            NavigationDrawerItem(
                label = {
                    Text(
                        text = stringResource(id = R.string.logout),
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                },
                selected = false,
                onClick = {
                    onLogoutButtonClicked()
                    scope.launch { scaffoldState.drawerState.apply { close() } }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_logout_24),
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.size(24.dp)
                    )
                }
            )
        }
    }
}