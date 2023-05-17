package com.example.electrize.pages

import android.Manifest
import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.electrize.EnergeticViewModel
import com.example.electrize.R
import com.example.electrize.ui.theme.ElectrizeTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.NavController
import com.example.electrize.EnergeticScreen
import com.example.electrize.MainActivity
import com.example.electrize.data.DataSource
import com.example.electrize.dataStructures.Route
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun payment(
    viewModel: EnergeticViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
        .fillMaxSize(),
    focusManager: FocusManager,
    notificationPermissionState: PermissionState,
    showSnackbar: () -> Unit
) {
    var currentRoute = viewModel.findCurrentRoute()
    var totalPrice = 0.0f
    if (currentRoute != null){
        totalPrice = currentRoute.getRoutePrice()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        var value by remember {
            mutableStateOf("")
        }

        //Page title
        Text(
            text = stringResource(id = R.string.payment_title),
            fontSize = 27.sp, modifier = Modifier.padding(10.dp),
            color = MaterialTheme.colorScheme.onBackground,
        )

        //Page subtitle
        var totalPriceString: String = stringResource(id = R.string.payment_total_price) + String.format("%.2f", totalPrice)
        Text(
            text = totalPriceString,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(10.dp, 7.dp, 10.dp, 20.dp)
        )

        couponCodeFields(focusManager)

        paymentOption(paymentOptionName = "PayPall", paymentOptionPicture = R.drawable.paypal, navController = navController, focusManager = focusManager, notificationPermissionState = notificationPermissionState, viewModel = viewModel, showSnackbar = showSnackbar)
        paymentOption(paymentOptionName = "Creditcard", paymentOptionPicture = R.drawable.mastercard, navController = navController, focusManager = focusManager, notificationPermissionState = notificationPermissionState, viewModel = viewModel, showSnackbar = showSnackbar)
        paymentOption(paymentOptionName = "Bancontact", paymentOptionPicture = R.drawable.payconiq, navController = navController, focusManager = focusManager, notificationPermissionState = notificationPermissionState, viewModel = viewModel, showSnackbar = showSnackbar)
        paymentOption(paymentOptionName = "Visa", paymentOptionPicture = R.drawable.visa, navController = navController, focusManager = focusManager, notificationPermissionState = notificationPermissionState, viewModel = viewModel, showSnackbar = showSnackbar)

    }
}

@Composable
fun couponCodeFields(focusManager: FocusManager) {
    var couponValue by remember {
        mutableStateOf("")
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp, 10.dp, 15.dp, 10.dp)
            .height(IntrinsicSize.Max)
    ) {
        OutlinedTextField(
            value = couponValue,
            onValueChange = { newCouponValue: String ->
                couponValue = newCouponValue
            },
            placeholder = {
                    Text(
                        text = stringResource(id = R.string.payment_coupon_searchbar_placeholder),
                        color = MaterialTheme.colorScheme.onBackground,
                    )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp, 0.dp, 0.dp, 0.dp)
                .height(IntrinsicSize.Max)
                .weight(0.6f),
            shape = RoundedCornerShape(5.dp, 0.dp, 0.dp, 5.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.primaryContainer,
                unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                cursorColor = MaterialTheme.colorScheme.primaryContainer,
            )
        )
        Button(
            onClick = {
                      focusManager.clearFocus()
                      couponValue = "" //Clean the coupon
            },
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primaryContainer),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(0.dp, 0.dp, 15.dp, 0.dp)
                .height(IntrinsicSize.Max)
                .weight(0.4f),
            shape = RoundedCornerShape(0.dp, 5.dp, 5.dp, 0.dp),
        ) {
            Text(
                text = stringResource(id = R.string.payment_coupon_button),
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                textAlign = TextAlign.Center,
            )
            }
    }
}

@SuppressLint("MissingPermission")
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun paymentOption(paymentOptionName: String, paymentOptionPicture: Int, navController: NavController, focusManager: FocusManager, notificationPermissionState: PermissionState, viewModel: EnergeticViewModel, showSnackbar: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(30.dp, 10.dp)
    ){
        Image(
            painter = painterResource(id = paymentOptionPicture),
            contentScale = ContentScale.Fit,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.3f)
                .clip(shape = RoundedCornerShape(5.dp, 0.dp, 0.dp, 5.dp))
                .padding(0.dp, 0.dp, 0.dp, 0.dp)
                .background(color = MaterialTheme.colorScheme.primaryContainer)
                .clickable { /*TODO*/ }
                .size(100.dp)
        )
        val context = LocalContext.current
        Button(
            onClick = {
                showSnackbar()
                var builder = NotificationCompat.Builder(context, "Energetic Payment")
                    .setSmallIcon(R.drawable.baseline_star_rate_24)
                    .setContentTitle("Leave reviews")
                    .setStyle(NotificationCompat.BigTextStyle()
                        .bigText("Remember to leave reviews on your bought products"))
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                with(NotificationManagerCompat.from(context)) {
                    if (notificationPermissionState.status.isGranted) {
                        notify(3500, builder.build())
                    }
                    else {
                        notificationPermissionState.launchPermissionRequest()
                    }
                }
                focusManager.clearFocus()
                navController.backQueue.clear()
                DataSource.Routes.add(viewModel.getTempRoute()!!)
                viewModel._currentData._routes.add(viewModel.getTempRoute()!!._routeId)
                viewModel._currentData._shoppingCart._productAmountList.clear()
                viewModel.clearTempRoute()
                navController.navigate("${EnergeticScreen.Home}")
              },
            shape = RoundedCornerShape(0.dp, 5.dp, 5.dp, 0.dp),
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primaryContainer),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .weight(0.7f)
                .padding(0.dp, 0.dp, 0.dp, 0.dp),
        ) {
            Text(
                text = paymentOptionName,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontSize = 20.sp,
            )
        }
    }
}