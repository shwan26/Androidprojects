package com.example.assignment2

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter

@Composable
fun CharacterDetailScreen(characterId: Int) {
    // Dummy data (replace with API call or ViewModel logic)
    val character = com.example.assignment2.Character(
        id = characterId,
        name = "Armothy",
        status = "Dead",
        species = "Unknown",
        image = "https://rickandmortyapi.com/api/character/avatar/$characterId.jpeg"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(character.image),
            contentDescription = null,
            modifier = Modifier.size(200.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text("Name: ${character.name}")
        Text("Status: ${character.status}")
        Text("Species: ${character.species}")
    }
}
