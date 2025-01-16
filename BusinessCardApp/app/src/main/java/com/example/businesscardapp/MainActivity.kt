package com.example.businesscardapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscardapp.ui.theme.BusinessCardAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BusinessCardAppTheme {
                BusinessCard()

            }
        }
    }
}

@Composable
fun BusinessCard() {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(100.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Namecard()
        Spacer(modifier = Modifier.size(20.dp))
        InformationCard()
    }

}

@Composable
private fun Namecard() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val image = painterResource(R.drawable.ic_launcher_foreground)
        Image(
            painter = image,
            contentDescription = "Image",
            alignment = Alignment.Center,
            modifier = Modifier
                .size(200.dp)
        )
        Text(
            text = "Shwan",
            fontSize = 20.sp
        )
        Text(
            text = "Android Developer"
        )
    }
}

@Composable
private fun InformationCard(){
    Column {
        Row {
            val callIcon = painterResource(R.drawable.call)
            Image(
                painter = callIcon,
                contentDescription = "Call Icon"
            )
            Text(
                text = "0987654321"
            )
        }

        Row {
            val shareIcon = painterResource(R.drawable.share)
            Image(
                painter = shareIcon,
                contentDescription = "@shwan-first"
            )
            Text(
                text = "@shwan-first"
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BusinessCardAppTheme {
        BusinessCard()
    }
}