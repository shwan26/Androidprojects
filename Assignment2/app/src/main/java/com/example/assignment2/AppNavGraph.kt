package com.example.assignment2

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

sealed class Screen(val route: String) {
    object CharacterList : Screen("character_list")
    object CharacterDetail : Screen("character_detail/{characterId}") {
        fun createRoute(characterId: Int) = "character_detail/$characterId"
    }
}

@Composable
fun AppNavGraph(
    navController: NavHostController,
    viewModel: CharacterViewModel,
    modifier: Modifier = Modifier
) {
    NavHost(navController = navController, startDestination = Screen.CharacterList.route, modifier = modifier) {
        composable(Screen.CharacterList.route) {
            CharacterListScreen(navController, viewModel)
        }
        composable(Screen.CharacterDetail.route) { backStackEntry ->
            val characterId = backStackEntry.arguments?.getString("characterId")?.toInt() ?: -1
            CharacterDetailScreen(characterId = characterId)
        }
    }
}
