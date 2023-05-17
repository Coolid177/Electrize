package com.example.electrize.pages

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.electrize.EnergeticScreen
import com.example.electrize.EnergeticViewModel
import com.example.electrize.R
import com.example.electrize.data.DataSource
import com.example.electrize.dataStructures.Account
import com.example.electrize.dataStructures.BoughtProduct
import com.example.electrize.dataStructures.CollectionRequest
import com.example.electrize.dataStructures.Product
import com.example.electrize.dataStructures.Route
import com.example.electrize.dataStructures.RouteType
import com.example.electrize.ui.theme.ElectrizeTheme
import java.math.RoundingMode
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun route(route: Route?, visitor: Boolean = false, focusManager: FocusManager, collectionId: Int, navController: NavController, viewModel: EnergeticViewModel){
    var account: Account? = route?.let { DataSource.findAccount(it._routeAccountId) }
    var requests by remember {
        mutableStateOf(DataSource.CollectionRequest)
    }
    var requestsCount by remember {
        mutableStateOf(requests.size)
    }
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result -> }
    var test by remember {
        mutableStateOf(requests.filter { it._collectionRequestId == collectionId })
    }
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            sheetState = sheetState
        ) {
            LazyColumn() {
                item{
                    Row(modifier = Modifier.padding(horizontal = 8.dp)){
                        searchBar()
                    }
                }
                DataSource.findFriends(viewModel._currentData._account._accountId).forEach {
                    item {
                        Surface(
                            color = MaterialTheme.colorScheme.surface,
                            shape = RoundedCornerShape(8.dp),
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface),
                            shadowElevation = 8.dp,
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .height(100.dp)
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Column(
                                ) {
                                    if (it != null) {
                                        Image(
                                            painter = painterResource(id = it._imageId),
                                            contentDescription = "",
                                            modifier = Modifier.size(100.dp)
                                        )
                                    }
                                }
                                Column(
                                    modifier = Modifier.fillMaxHeight(),
                                ) {
                                    Row(

                                    ) {
                                        if (it != null) {
                                            Text(
                                                text = it._firstName + " " + it._lastName,
                                                modifier = Modifier.padding(10.dp),
                                                fontSize = 18.sp
                                            )
                                        }
                                    }
                                    Row(
                                        verticalAlignment = Alignment.Top
                                    ) {
                                        if (it != null) {
                                            Text(
                                                text = "GebruikersId: " + it._accountId.toString()
                                                    .chunked(4).joinToString(" "),
                                                modifier = Modifier.padding(start = 10.dp),
                                                fontSize = 12.sp
                                            )
                                        }
                                    }
                                }
                                Column(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .fillMaxWidth(),
                                    horizontalAlignment = Alignment.End
                                ) {
                                    IconButton(
                                        onClick = {
                                            if (it != null) {
                                                DataSource.addCollectionRequest(
                                                    CollectionRequest(
                                                        collectionRequestId = DataSource.CurrentCollectionRequestId,
                                                        routeId = route!!._routeId,
                                                        receiverId = it._accountId,
                                                        senderId = viewModel._currentData._account._accountId
                                                    )
                                                )
                                            }
                                        },
                                        modifier = Modifier.padding(top = 26.dp)
                                    ) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.paper_plane),
                                            contentDescription = "",
                                            modifier = Modifier.size(18.dp)
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
    if (test.isEmpty()) {

    }
    else {
        LazyColumn(
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        ) {
            if(visitor){
                item{
                    Surface(
                        color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(8.dp),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface),
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                        ){
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ){
                                if (account != null) {
                                    Image(
                                        painter = painterResource(id = account._imageId),
                                        contentDescription = "",
                                        contentScale = ContentScale.Fit,
                                        modifier = Modifier
                                            .size(180.dp)
                                            .padding(8.dp)
                                            .clip(shape = RoundedCornerShape(8.dp))
                                    )
                                }
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text("Naam")
                                Text(account?._firstName + " " + account?._lastName)
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ){
                                Text("Adres")
                                if(account?._addressId?._addressLine2 == ""){
                                    account?._addressId?._addressLine1?.let { Text(it) }
                                } else {
                                    Text(account?._addressId?._addressLine1 + "/" + account?._addressId?._addressLine2)
                                }
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ){
                                Text("Gemeente")
                                Text(account?._addressId?._city + " - " + account?._addressId?._postalCode)
                            }
                        }
                    }
                }
            }
            item {
                Surface(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface),
                    modifier = Modifier.padding(8.dp)
                ) {
                    Column(
                        modifier = Modifier.background(MaterialTheme.colorScheme.surface)
                    ) {
                        rowItem(
                            what = stringResource(id = R.string.ordered_on),
                            value = route!!._routePaymentDate.day.toString() + "/" + route!!._routePaymentDate.month.toString() + "/" + route!!._routePaymentDate.year.toString()
                        )
                        rowItem(
                            what = stringResource(id = R.string.collect_on),
                            value = route!!._routeCollectionDate.day.toString() + "/" + route!!._routeCollectionDate.month.toString() + "/" + route!!._routeCollectionDate.year.toString()
                        )
                        rowItem(
                            what = stringResource(id = R.string.type_route),
                            value = route!!._routeType.name
                        )
                        rowItem(
                            what = route!!.getRouteDistance().toString(),
                            value = stringResource(id = R.string.length)
                        )
                        rowItem(
                            what = stringResource(id = R.string.estimated_duration),
                            value = route!!.getRouteDuration().toString()
                        )
                        rowItem(
                            what = stringResource(id = R.string.number_of_shops),
                            value = route!!._routeProducts.size.toString()
                        )
                        rowItem(
                            what = stringResource(id = R.string.total_price),
                            value = calculateTotalPrice(route._routeProducts).toString() + "€"
                        )
                    }
                }
            }
            item{
                Row() {
                    Text(
                        text = "Producten",
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.padding(10.dp),
                        fontSize = 24.sp,
                    )
                }
            }

        item{
            VerticalGrid() {
                route!!._routeProducts.forEach { product ->
                    productItem(product = product, focusManager = focusManager)
                }
            }
        }
            if(!visitor){
                item {
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    ) {
                        Button(
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                            ),
                            onClick = {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.be/maps"))
                                launcher.launch(intent)
                            }
                        ) {
                            Text(
                                text = "Start route",
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                            )
                        }
                        Button(
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                            ),
                            onClick = {
                                showBottomSheet = true
                            }
                        ) {
                            Text(
                                text = "Stuur ophaalverzoek",
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                            )
                        }
                    }
                }
            } else {
                item {
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    ) {
                        Button(
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                            ),
                            onClick = {
                                val routeId = test.get(0)._routeId
                                viewModel.addForeignRoute(routeId)
                                DataSource.removeCollectionRequest(collectionId)
                                requests = DataSource.CollectionRequest
                                requestsCount = requests.size
                                test = requests.filter { it._collectionRequestId == collectionId }
                                navController.navigate("${EnergeticScreen.Routes.name}/${routeId}")
                            },
                            modifier = Modifier
                                .weight(0.5f)
                                .padding(16.dp)
                        ) {
                            Text(
                                text = "Accept",
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                            )
                        }
                        Button(
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.errorContainer,
                            ),
                            onClick = {
                                DataSource.removeCollectionRequest(collectionId)
                                requests = DataSource.CollectionRequest
                                requestsCount = requests.size
                                test = requests.filter { it._collectionRequestId == collectionId }
                                navController.popBackStack()
                            },
                            modifier = Modifier
                                .weight(0.5f)
                                .padding(16.dp)
                        ) {
                            Text(
                                text = "Reject",
                                color = MaterialTheme.colorScheme.onErrorContainer,
                            )
                        }
                    }
                }
            }
        }
    }
}

fun calculateTotalPrice(products: List<BoughtProduct>) : Float{
    var totalPrice: Float = 0.0f
    products.forEach { product->
        DataSource.findProduct(productId = product._productId)?.let {
            getProductPrice(
                storeId = product._storeId,
                itemList = it._pricingList
            )
        }?.let{
            totalPrice += it
        }
    }
    return totalPrice
}

/**
 * from https://github.com/android/compose-samples/blob/main/Jetsnack/app/src/main/java/com/example/jetsnack/ui/components/Grid.kt
 */
@Composable
fun VerticalGrid(
    modifier: Modifier = Modifier,
    columns: Int = 2,
    content: @Composable () -> Unit
) {
    Layout(
        content = content,
        modifier = modifier
    ) { measurables, constraints ->
        val itemWidth = constraints.maxWidth / columns
        // Keep given height constraints, but set an exact width
        val itemConstraints = constraints.copy(
            minWidth = itemWidth,
            maxWidth = itemWidth
        )
        // Measure each item with these constraints
        val placeables = measurables.map { it.measure(itemConstraints) }
        // Track each columns height so we can calculate the overall height
        val columnHeights = Array(columns) { 0 }
        placeables.forEachIndexed { index, placeable ->
            val column = index % columns
            columnHeights[column] += placeable.height
        }
        val height = (columnHeights.maxOrNull() ?: constraints.minHeight)
            .coerceAtMost(constraints.maxHeight)
        layout(
            width = constraints.maxWidth,
            height = height
        ) {
            // Track the Y co-ord per column we have placed up to
            val columnY = Array(columns) { 0 }
            placeables.forEachIndexed { index, placeable ->
                val column = index % columns
                placeable.placeRelative(
                    x = column * itemWidth,
                    y = columnY[column]
                )
                columnY[column] += placeable.height
            }
        }
    }
}

@Composable
private fun productItem(product: BoughtProduct, focusManager: FocusManager){
    val productData: Product? = DataSource.findProduct(product._productId)
    Surface(
        color = Color.White,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color.LightGray),
        shadowElevation = 8.dp,
        modifier = Modifier
            .padding(8.dp)
            .width(150.dp)
    ){
        Column(modifier = Modifier.padding(8.dp)) {
            Row(){
                Image(
                    painter = painterResource(id = productData!!._productImages.first()),
                    contentDescription = ""
                )
            }
            Row(){
                Column() {
                    Text(
                        text = productData!!._productName,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        modifier = Modifier.width(140.dp),

                    )
                    Text(
                        text = getProductPrice(
                            storeId = product._storeId,
                            itemList = productData._pricingList
                        )?.toBigDecimal()?.setScale(2, RoundingMode.UP)?.toFloat().toString() + "€"
                    )
                    Text(
                        text = "Aantal: " + product._quantity.toString()
                    )
                    Text(
                        text = DataSource.findStore(product._storeId)?._storeName.toString()
                    )
                    Text(
                        text = "Totaalprijs: " + (getProductPrice(
                            storeId = product._storeId,
                            itemList = productData._pricingList
                        )?.times(product._quantity)).toString()
                    )
                }
            }
        }
    }
}

private fun getProductPrice(itemList: List<Pair<Int, Float>>, storeId: Int): Float?{
    itemList.forEach {item->
        if(item.first == storeId){
            return item.second
        }
    }
    return null
}

@Composable
private fun rowItem(what: String, value: String){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ){
        Text(text = what)
        Text(text = value)
    }
}

@Composable
private fun searchBar() {
    var text by remember { mutableStateOf("") }
    val interactionSource = remember {
        MutableInteractionSource()
    }

    BasicTextField(
        value = text,
        onValueChange = {
            text = it
        },
        singleLine = true,
        modifier = Modifier
            .fillMaxSize()
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(50.dp)
            )
            .height(40.dp),
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
        interactionSource = interactionSource,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = {

        })
    ) { innerTextField ->
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.search),
                contentDescription = "search",
                modifier = Modifier
                    .fillMaxHeight()
                    .width(40.dp)
                    .padding(8.dp)
            )
            Box(
                modifier = Modifier
                    .weight(1.0f)
            ) {
                innerTextField()
            }
        }
    }
}

