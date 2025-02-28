package com.example.assignment2

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import coil3.compose.rememberAsyncImagePainter
import com.example.assignment2.CharacterViewModel

@Composable
fun CharacterListScreen(navController: NavController, viewModel: CharacterViewModel) {
    val characters = viewModel.characters.collectAsState().value
    val currentPage = viewModel.page.collectAsState().value

    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val (previousButton, pageNumber, nextButton, characterList) = createRefs()

        // Page navigation controls (Previous, Page number, Next)
        Row(
            modifier = Modifier
                .constrainAs(previousButton) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
                .clickable { viewModel.loadPreviousPage() }
                .padding(8.dp)
        ) {
            Text(
                text = "Previous",
                color = if (currentPage > 1) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(
                    alpha = 0.5f
                )
            )
        }

        Text(
            text = "Page $currentPage",
            modifier = Modifier
                .constrainAs(pageNumber) {
                    top.linkTo(parent.top)
                    start.linkTo(previousButton.end)
                    end.linkTo(nextButton.start)
                }
                .padding(8.dp)
        )

        Row(
            modifier = Modifier
                .constrainAs(nextButton) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }
                .clickable { viewModel.loadNextPage() }
                .padding(8.dp)
        ) {
            Text(
                text = "Next",
                color = if (currentPage < 42) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(
                    alpha = 0.5f
                )
            )
        }

        // Character list
        LazyColumn(
            modifier = Modifier
                .constrainAs(characterList) {
                    top.linkTo(previousButton.bottom) // Positioned below the page navigation
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
                .padding(8.dp) // Add padding to the list items
        ) {
            items(characters) { character ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable {
                            navController.navigate(Screen.CharacterDetail.createRoute(character.id))
                        }
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(character.image),
                        contentDescription = null,
                        modifier = Modifier.size(50.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(text = character.name)
                        Text(text = character.species)
                    }
                }
            }
        }
    }

}
