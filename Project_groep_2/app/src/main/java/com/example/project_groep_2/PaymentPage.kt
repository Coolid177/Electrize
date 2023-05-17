package com.example.project_groep_2

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.project_groep_2.ui.theme.Project_groep_2Theme
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun PaymentPagePreview() {
    Project_groep_2Theme {
        Surface {
            PaymentPage()
        }
    }
}

@Composable
fun PaymentPage(
    modifier: Modifier = Modifier
        .fillMaxSize()
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ){
        var value by remember {
            mutableStateOf("")
        }
        PageTitle(title = "Selecteer uw betaalmethode")

        CouponCodeFields()


        PaymentOption(paymentOptionName = "PayPall")
        PaymentOption(paymentOptionName = "Creditcard")
        PaymentOption(paymentOptionName = "Bancontact")
        PaymentOption(paymentOptionName = "Visa")

    }
}

@Composable
fun CouponCodeFields() {
    var couponValue by remember {
        mutableStateOf("")
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp, 5.dp)
            .height(IntrinsicSize.Max)
    ) {
        OutlinedTextField(
            value = couponValue,
            onValueChange = { newCouponValue: String ->
                couponValue = newCouponValue
            },
            placeholder = {
                Text(
                    text = "Uw kortingscode",
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp, 0.dp, 5.dp, 0.dp)
                .height(IntrinsicSize.Max)
                .weight(0.7f)
        )
        
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
            .padding(5.dp, 0.dp, 15.dp, 0.dp)
                .weight(0.3f)
        ) {
            Text(
                text = "Voeg toe",
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
fun PaymentOption(paymentOptionName: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .padding(30.dp, 10.dp)
    ){
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.3f)
                .clip(shape = RoundedCornerShape(5.dp, 0.dp, 0.dp, 5.dp))
                .padding(0.dp, 0.dp, 0.dp, 0.dp)
        )

        Button(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(0.dp, 5.dp, 5.dp, 0.dp),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .weight(0.7f)
                .padding(0.dp, 0.dp, 0.dp, 0.dp),
        ) {
            Text(
                text = paymentOptionName,
                fontSize = 20.sp,
            )
        }
    }
}