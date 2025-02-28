package com.example.assignment2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.assignment2.ui.theme.Assignment2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            Assignment2Theme {
                setContent {
                    Assignment2Theme {
                        val navController = rememberNavController()
                        val viewModel: CharacterViewModel = viewModel()

                        Surface(
                            modifier = Modifier.fillMaxSize()
                                .padding(top=50.dp),
                            color = MaterialTheme.colorScheme.background
                        ) {
                            ConstraintLayoutScreen(navController = navController, viewModel = viewModel)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ConstraintLayoutScreen(navController: NavHostController, viewModel: CharacterViewModel) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (content) = createRefs()

        // Your content here (NavGraph) can be constrained
        AppNavGraph(
            navController = navController,
            viewModel = viewModel,
            modifier = Modifier.constrainAs(content) {
                top.linkTo(parent.top) // Link to parent top, you can set constraints here
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
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
    Assignment2Theme {
        Greeting("Android")
    }
}