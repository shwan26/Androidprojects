package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ArtSpaceApp(

                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp(modifier: Modifier) {

    val drawables = listOf(
        R.drawable.artwork1,
        R.drawable.artwork2,
        R.drawable.artwork3
    )

    val artists = listOf(
        R.string.artist1,
        R.string.artist2,
        R.string.artist3
    )

    val paintings = listOf(
        R.string.painting1,
        R.string.painting2,
        R.string.painting3
    )

    var currentIndex by remember { mutableStateOf(1) }

    Column (modifier = modifier
        .padding(16.dp)
        .fillMaxSize()
        .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Image(
            painter = painterResource(id = drawables[currentIndex]),
            contentDescription = ""
        )
        Text(
            text = stringResource(id = artists[currentIndex])

        )
        Text(
            text = stringResource(id = paintings[currentIndex])

        )
        Row {
            Button(onClick = {
                if (currentIndex > 0) {
                    currentIndex--
                }
                else {
                    currentIndex = 2
                }
            }) {
                Text("Previous")
            }
            Button(onClick = {
                if (currentIndex < drawables.size-1) {
                    currentIndex++
                }
                else {
                    currentIndex = 0
                }
            }) {
                Text("Next")
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceTheme {
        ArtSpaceApp(modifier = Modifier)
    }
}