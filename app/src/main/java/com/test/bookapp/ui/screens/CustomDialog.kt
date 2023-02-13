package com.test.bookapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.test.bookapp.ui.theme.AppColor

@Composable
fun CustomDialog(
    message: String,
    onDismiss: () -> Unit
){
    Dialog(
        onDismissRequest = { onDismiss() },
    ) {
        Card (
            elevation = 5.dp,
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .height(150.dp)
                .border(
                    1.dp,
                    color = AppColor,
                    shape = RoundedCornerShape(10.dp)
                )
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = CenterHorizontally
            ) {

                Text(
                    text = "Alert",
                    style = TextStyle(
                        color = Color.White,
                        background = AppColor,
                        fontSize = 18.sp
                    ),
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth()
                        .background(AppColor)
                        .padding(5.dp)
                )


                Text(
                    text = message,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(10.dp)
                        .align(CenterHorizontally)
                )
                Button(onClick = { onDismiss() }) {
                    Text(text = "OK")
                }
            }
        }
    }
}