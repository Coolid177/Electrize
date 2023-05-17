package com.example.project_groep_2

import android.graphics.Paint.Align
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project_groep_2.ui.theme.Project_groep_2Theme
import kotlin.math.exp

data class Product1(
    val productName: String = "defaultProduct",
    val productImages: List<Int> = mutableListOf(R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background),
    val productImagesDescription: List<String> = mutableListOf("Image1", "Image2", "Image3"),
    val productSpecificationTypes: List<String> = mutableListOf("Bluetooth", "Weight"),
    val productSpecificationValues: List<String> = mutableListOf("Yes", "34g"),
    val productPricesShops: List<String> = mutableListOf("Mediamarkt - Hasselt", "Fnac - Hasselt"),
    val productPricesValues: List<String> = mutableListOf("14.99", "24.99"),
    val productReviewTitles: List<String> = mutableListOf("Review1", "Review2"),
    val productReviewScores: List<Float> = mutableListOf(2.5f, 4.0f),
    val productReviewBodies: List<String> = mutableListOf("Content of review 1", "Content of review 2"),
)

@Preview(showBackground = true)
@Composable
fun PreviewProductPage() {
    Project_groep_2Theme {
        Surface {
            ProductPage(product = Product1("Mouse"))
        }
    }
}

@Composable
fun ProductPage(
    product: Product1,
    modifier: Modifier = Modifier
        .fillMaxSize()
) {
    Column(modifier = Modifier) {
        //Image
        ProductImages(product.productImages, product.productImagesDescription)

        //Product title
        Text(
            text = product.productName,
            fontSize = 25.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.1f)
                .padding(15.dp)
        )

        //Product review
        //Todo stars instead of score
        Text(
            text = "${CalculateReviewScore(product.productReviewScores)} / 5",
            fontSize = 15.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.1f)
                .padding(5.dp)
        )

        //Specifications, Prices and Reviews
        Column(modifier = Modifier
            .fillMaxWidth()
            .weight(0.6f)
            ) {
            CollapsableInformationCards(
                "Specifications",
                product.productSpecificationTypes,
                product.productSpecificationValues,
                true
            )
            CollapsableInformationCards(
                title = "Prices",
                specificationTypeList = product.productPricesShops,
                specificationValueList = product.productPricesValues
            )
            CollapsibleReviews(
                title = "Reviews",
                reviewTitleList = product.productReviewTitles,
                reviewScoreList = product.productReviewScores,
                reviewBodyList = product.productReviewBodies,
            )
        }

        //Floating buttons on bottom of page
        Row(
            modifier = Modifier
                .align(alignment = Alignment.End),
            verticalAlignment = Alignment.Bottom,
        ) {
            ExtendedFloatingActionButton(
                onClick = { /*TODO*/ },

                )
            {
                Text(text = "Compare")
            }
            ExtendedFloatingActionButton(
                onClick = { /*TODO*/ },
            )
            {
                Text(text = "Add to cart")
            }
        }
    }
}

@Composable
fun ProductImages(imageList: List<Int>, imageDescriptionList: List<String>){
    //Todo make buttons to change highlighted image
    //Todo fill left black square with half of left image
    //Todo fill right black square with half of right image

    var centerImageIndex: Int = 1
    var centerImage: Int = imageList.elementAt(centerImageIndex)
    var centerImageDescription: String = imageDescriptionList.elementAt(centerImageIndex)
    var leftImageIndex: Int = 0
    var leftImage: Int = imageList.elementAt(leftImageIndex)
    var leftImageDescription: String = imageDescriptionList.elementAt(leftImageIndex)
    var rightImageIndex: Int = 2
    var rightImage: Int = imageList.elementAt(rightImageIndex)
    var rightImageDescription: String = imageDescriptionList.elementAt(rightImageIndex)

    Row (
        modifier = Modifier
            .height(IntrinsicSize.Max),
    ) {
        //Left image
        Box(
            modifier = Modifier
                .clip(RectangleShape)
                .size(100.dp, 180.dp)
                .weight(0.2f)
                .background(Color.Black)
                .graphicsLayer {
                    clip = true
                    shape = RectangleShape
                    translationX = 100.dp.toPx()
                }
        ){
            if (centerImageIndex > 0) {
                Image(
                    painter = painterResource(id = leftImage),
                    contentDescription = leftImageDescription,
                    modifier = Modifier
                        .fillMaxSize()
                )
            } else {
                //When no image should be shown fill the place of the image with an empty
                Spacer(
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }

        //Center image
        Image(
            painter = painterResource(id = centerImage),
            contentDescription = centerImageDescription,
            modifier = Modifier
                .fillMaxSize()
                .weight(0.6f)
                .size(200.dp)
        )

        //Right image
        Box(
            modifier = Modifier
                .clip(RectangleShape)
                .size(100.dp, 180.dp)
                .weight(0.2f)
                .background(Color.Black)
                .graphicsLayer {
                    clip = true
                    shape = RectangleShape
                    translationX = 100.dp.toPx()
                }
        ) {
            if (centerImageIndex < (imageList.size - 1)) {
                Image(
                    painter = painterResource(id = rightImage),
                    contentDescription = rightImageDescription,
                    modifier = Modifier
                        .fillMaxSize()
                )
            } else {
                //When no image should be shown fill the place of the image with an empty
                Spacer(
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }
    }
}

@Composable
fun CollapsableInformationCards(title: String, specificationTypeList: List<String>, specificationValueList: List<String>, defaultCollapseState: Boolean = false){
    val expanded = remember { mutableStateOf(defaultCollapseState)}
    Column(modifier = Modifier
        .fillMaxWidth(),
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            //Collapsable card title
            Text(
                text = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.8f),
            )

            //Collapsable card button
            Button(
                onClick = { expanded.value = !expanded.value },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.2f),
            ) {
                if (expanded.value) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_drop_up_icon),
                        contentDescription = "Show ${title}",
                    )
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_drop_down_icon),
                        contentDescription = "Hide ${title}",
                    )
                }
            }
        }

        //If values not hidden
        if (expanded.value){
            //All the given items inside of the collapsable list
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                itemsIndexed(specificationTypeList) { index, item ->
                    InformationCard(
                        specificationType = item,
                        specificationValue = specificationValueList[index]
                    )
                }

            }
        }
    }
}

@Composable
fun InformationCard(specificationType: String, specificationValue: String){
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        //Specification text
        Text(
            text = "${specificationType}: ",
            textAlign = TextAlign.Left,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f)
        )

        //Specification value
        Text(
            text = specificationValue,
            textAlign = TextAlign.Right,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f)
        )
    }
}

//Collapsable reviews because reviews do not use standard information card to show items
@Composable
fun CollapsibleReviews(title: String, reviewTitleList: List<String>, reviewScoreList: List<Float>, reviewBodyList: List<String>, defaultCollapseState: Boolean = false){
    var expanded = remember { mutableStateOf(defaultCollapseState) }
    Column(modifier = Modifier
        .fillMaxWidth(),
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.8f),
            )
            Button(
                onClick = { expanded.value = !expanded.value },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.2f),
            ) {
                if (expanded.value) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_drop_up_icon),
                        contentDescription = "Show ${title}",
                    )
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_drop_down_icon),
                        contentDescription = "Hide ${title}",
                    )
                }
            }
        }
        if (expanded.value){
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                itemsIndexed(reviewTitleList) { index, item ->
                    ReviewCard(
                        item,
                        reviewScoreList[index],
                        reviewBodyList[index],
                    )
                }

            }
        }
    }
}
@Composable
fun ReviewCard(reviewTitle: String, reviewScore: Float, reviewBody: String){
    Column (
        modifier = Modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = reviewTitle,
                textAlign = TextAlign.Left,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.5f)
            )

            Text(
                text = "${reviewScore}/5",
                textAlign = TextAlign.Right,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.5f)
            )
        }

        Text(text = reviewBody)
    }
}

fun CalculateReviewScore(reviewScores: List<Float>): Float {
    var total: Float = 0.0f
    var counter: Int = 0
    reviewScores.forEach {
        total += it
        counter += 1
    }

    return total / counter
}