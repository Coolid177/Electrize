package com.example.electrize

/**
 * @author Rune
 */

import android.Manifest
import androidx.activity.result.ActivityResultLauncher
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.FocusInteraction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.electrize.components.fastActionMenu
import com.example.electrize.components.hamburgerDrawer
import com.example.electrize.components.header
import com.example.electrize.data.DataSource
import com.example.electrize.pages.categoryProducts
import com.example.electrize.pages.compare
import com.example.electrize.pages.home
import com.example.electrize.pages.products
import com.example.electrize.pages.routes
import com.example.electrize.pages.shoppingCart
import com.example.electrize.pages.contacts
import com.example.electrize.pages.customizeRoute
import com.example.electrize.pages.friendRequests
import com.example.electrize.pages.inspectRoute
import com.example.electrize.pages.login
import com.example.electrize.pages.payment
import com.example.electrize.pages.product
import com.example.electrize.pages.qRCodeScanner
import com.example.electrize.pages.retrievalRequest
import com.example.electrize.pages.review
import com.example.electrize.pages.route
import com.example.electrize.pages.search
import com.example.electrize.pages.searchResults
import com.example.electrize.pages.settings
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

enum class EnergeticScreen(@StringRes val title: Int){
    Home(title = R.string.home),
    Products(title = R.string.products),
    Routes(title = R.string.routes),
    ShoppingCart(title = R.string.shopping_cart),
    Contacts(title = R.string.contacts),
    Settings(title = R.string.settings),
    Compare(title = R.string.compare),
    FriendRequests(title = R.string.friend_requests),
    CollectionRequests(title = R.string.collection_requests),
    QRCodeScanner(title = R.string.qr_code_scanner),
    CustomizeRoute(title = R.string.customize_route),
    Payment(title = R.string.payment),
    Search(title = R.string.search),
    SearchResults(title = R.string.searchResults),
    CategoryProducts(title = R.string.category_products),
    InspectIncomingRoute(title = R.string.inspect_incoming_route),
    Login(title = R.string.login),
    Review(title = R.string.review),
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun Energetic(modifier: Modifier = Modifier) {
    var navController = rememberNavController()
    val scope = rememberCoroutineScope()
    var data = EnergeticViewModel()
    val cameraPermissionState = rememberPermissionState(
        Manifest.permission.CAMERA
    )
    val notificationPermissionState = rememberPermissionState(
        Manifest.permission.POST_NOTIFICATIONS
    )
    val interactionSource = remember { MutableInteractionSource() }
    val loggedIn = remember { mutableStateOf(data._currentData._logged_in)}
    val focusManager = LocalFocusManager.current
    var snackBarHostState = remember {
        SnackbarHostState()
    }
    val scaffoldState = rememberScaffoldState(snackbarHostState = snackBarHostState)
    val channel = remember { Channel<Int>(Channel.CONFLATED) }
    LaunchedEffect(channel) {
        channel.receiveAsFlow().collect { _ ->
            val result = snackBarHostState.showSnackbar(
                message = "payment succesful!",
                actionLabel = "dismiss"
            )
        }
    }
    Scaffold(
        modifier = Modifier
            .clickable (
                interactionSource = interactionSource,
                indication = null
            )
            {
                focusManager.clearFocus()
            },
        scaffoldState = scaffoldState,
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        topBar = {
            header(
                scaffoldState = scaffoldState,
                scope = scope,
                onQRCodeButtonClicked = {
                    if (!cameraPermissionState.status.isGranted) {
                        cameraPermissionState.launchPermissionRequest()
                    }
                    else {
                        navController.navigate(EnergeticScreen.QRCodeScanner.name)
                    }
                    },
                navController = navController,
                focusManager = focusManager
            )
         },
        bottomBar = {
            fastActionMenu(
                onProductButtonClicked = {
                    focusManager.clearFocus()
                    navController.navigate(EnergeticScreen.Products.name)
                                         },
                onHomeButtonClicked = {
                    focusManager.clearFocus()
                    navController.navigate(EnergeticScreen.Home.name)
                                      },
                onRoutesButtonClicked = {
                    focusManager.clearFocus()
                    navController.navigate(EnergeticScreen.Routes.name)
                                        },
                onShoppingCartButtonClicked = {
                    focusManager.clearFocus()
                    navController.navigate(EnergeticScreen.ShoppingCart.name)
                                              },
                navController = navController
            )
        },
        drawerContent = {
            hamburgerDrawer(
                loggedIn = loggedIn,
                scaffoldState = scaffoldState,
                scope = scope,  
                onCompareButtonClicked = {
                    focusManager.clearFocus()
                    navController.navigate(EnergeticScreen.Compare.name)
                                         },
                onSettingsButtonClicked = {
                    focusManager.clearFocus()
                    navController.navigate(EnergeticScreen.Settings.name)
                                          },
                onContactsButtonClicked = {
                    focusManager.clearFocus()
                    navController.navigate(EnergeticScreen.Contacts.name)
                                          },
                onCollectionRequestsClicked = {
                    focusManager.clearFocus()
                    navController.navigate(EnergeticScreen.CollectionRequests.name)
                },
                onFriendRequestButtonClicked = {
                    focusManager.clearFocus()
                    navController.navigate(EnergeticScreen.FriendRequests.name)
                },
                onLoginSignupButtonClicked = {
                    navController.navigate(EnergeticScreen.Login.name)
                                             },
                onLogoutButtonClicked = {
                    loggedIn.value = false
                    data._currentData._logged_in = false
                    navController.navigate(EnergeticScreen.Home.name)
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = EnergeticScreen.Home.name,
            modifier = modifier.padding(innerPadding)
        ){
            //navbar
            composable(route = EnergeticScreen.Home.name) {
                home(viewModel = data, navController = navController, focusManager = focusManager)
            }
            composable(route = EnergeticScreen.Products.name){
                products(navController = navController, focusManager = focusManager)
            }
            composable(route = EnergeticScreen.Search.name){
                search(navController = navController, focusManager = focusManager)
            }
            composable(route = EnergeticScreen.SearchResults.name){
                searchResults(navController = navController, focusManager = focusManager)
            }
            composable(
                route = "${EnergeticScreen.Products.name}/{productId}",
                arguments = listOf(navArgument("productId") { type = NavType.IntType })
            ) { backStackEntry ->
                val productId = backStackEntry.arguments?.getInt("productId")
                if (productId != null) {
                    product(productId = productId, viewModel = data, navController = navController, focusManager = focusManager)
                }
            }
            composable(
                route = "${EnergeticScreen.CategoryProducts.name}/{categoryId}",
                arguments = listOf(navArgument("categoryId"){type = NavType.IntType})
            ){ backStackEntry ->
                val categoryId = backStackEntry.arguments?.getInt("categoryId")
                if (categoryId != null){
                    categoryProducts(categoryId = categoryId, navController = navController, focusManager = focusManager)
                }
            }
            composable(
                route = "${EnergeticScreen.InspectIncomingRoute.name}/{collectionId}",
                arguments = listOf(navArgument("collectionId"){type = NavType.IntType})
            ){backStackEntry ->
                val collectionId = backStackEntry.arguments?.getInt("collectionId")
                if (collectionId != null){
                    inspectRoute(collectionId = collectionId, navController = navController, focusManager = focusManager, viewModel = data)
                }
            }
            composable(route = EnergeticScreen.Routes.name){
                if (data._currentData._logged_in){
                    routes(viewModel = data, navController = navController, focusManager = focusManager)
                } else {
                    data._currentData._destined_page = EnergeticScreen.Routes
                    login(viewModel = data, navController = navController, loggedIn = loggedIn, loggedInMessage = true)
                }
            }
            composable(
                route = "${EnergeticScreen.Routes.name}/{routeId}",
                arguments = listOf(navArgument("routeId") { type = NavType.IntType })
            ) { backStackEntry ->
                val routeId = backStackEntry.arguments?.getInt("routeId")
                if (routeId != null) {
                    route(route = DataSource.findRoute(routeId), visitor = false, focusManager = focusManager, collectionId = 0, navController = navController, viewModel = data)
                }
            }
            composable(route = EnergeticScreen.ShoppingCart.name){
                if (data._currentData._logged_in) {
                    shoppingCart(viewModel = data, navController = navController, focusManager = focusManager)
                } else {
                    data._currentData._destined_page = EnergeticScreen.ShoppingCart
                    login(viewModel = data, navController = navController, loggedIn = loggedIn, loggedInMessage = true)
                }
            }
            //hamburger menu
            composable(route = EnergeticScreen.Contacts.name){
                contacts(viewModel = data, navController = navController, focusManager = focusManager)
            }
            composable(route = EnergeticScreen.Settings.name){
                settings(viewModel = data, focusManager = focusManager)
            }
            composable(route = EnergeticScreen.Compare.name){
                compare(viewModel = data, navController = navController, focusManager = focusManager)
            }
            composable(route = EnergeticScreen.FriendRequests.name){
                friendRequests(viewModel = data, focusManager = focusManager)
            }
            composable(route = EnergeticScreen.CollectionRequests.name){
                retrievalRequest(viewModel = data, navController, focusManager = focusManager)
            }
            composable(route = EnergeticScreen.QRCodeScanner.name){
                qRCodeScanner(focusManager = focusManager, navController = navController)
            }
            composable(route = EnergeticScreen.CustomizeRoute.name){
                customizeRoute(viewModel = data, navController = navController, focusManager = focusManager)
            }
            composable(route = EnergeticScreen.Payment.name){
                payment(viewModel = data, navController = navController, focusManager = focusManager, notificationPermissionState = notificationPermissionState, showSnackbar = {
                    channel.trySend(0)
                })
            }
            composable(route = EnergeticScreen.Login.name){
                login(viewModel = data, navController = navController, loggedIn = loggedIn, loggedInMessage = false)
            }
            composable(route = EnergeticScreen.Review.name){
                review(viewModel = data, navController = navController, focusManager = focusManager)
            }
        }
    }
}