package com.example.assignment2

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.assignment2.RickAndMortyApi

class CharacterViewModel: ViewModel() {
    private val api = RickAndMortyApi()

    private val _characters = MutableStateFlow<List<Character>>(emptyList())
    val characters: StateFlow<List<Character>> = _characters

    private val _page = MutableStateFlow(1)
    val page: StateFlow<Int> = _page

    init {
        fetchCharacters()
    }

    fun fetchCharacters() {
        viewModelScope.launch {
            try {
                val response = api.getCharacters(_page.value)
                _characters.value = response.results
            } catch (e: Exception) {
                // Log the error
                Log.e("CharacterViewModel", "Error fetching characters: ${e.message}")
            }
        }
    }


    fun loadNextPage() {
        if (_page.value < 42) {
            _page.value++
            fetchCharacters()
        }

    }

    fun loadPreviousPage() {
        if (_page.value > 1) {
            _page.value--
            fetchCharacters()
        }
    }
}
