package com.example.jetweatherapp.screens.favourite

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetweatherapp.model.Favourite
import com.example.jetweatherapp.repository.WeatherDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavouriteViewModal @Inject constructor(repository: WeatherDbRepository) : ViewModel() {

    private val _favList = MutableStateFlow<List<Favourite>>(emptyList())
    val favList = _favList.asStateFlow()


    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getFavourites().distinctUntilChanged().collect { listOfFavs ->
                if (listOfFavs.isNullOrEmpty()) {
                    Log.d("TAG", ":emtpy favs ")
                } else {
                    _favList.value = listOfFavs
                    Log.d("FAVS", ":{${favList.value}} ")
                }
            }
        }


        fun getFavoritesById(city: String) =
            viewModelScope.launch { repository.getFavoritesById(city = city) }

        fun insertFavorite(favourite: Favourite) =
            viewModelScope.launch { repository.insertFavorite(favourite = favourite) }

        fun updateFavorite(favourite: Favourite) =
            viewModelScope.launch { repository.updateFavorite(favourite = favourite) }

        fun deleteAllFavourite() = viewModelScope.launch { repository.deleteAllFavourite() }

        fun deleteFavorite(favourite: Favourite) =
            viewModelScope.launch { repository.deleteFavorite(favourite = favourite) }


    }

}