package com.example.jetweatherapp.screens.settings

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetweatherapp.repository.WeatherDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class SettingsViewModal @Inject constructor(private  val repository: WeatherDbRepository) : ViewModel() {


    private  val _unitList = MutableStateFlow<List<com.example.jetweatherapp.model.Unit>>(emptyList())
    val unitList = _unitList.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getUnits().distinctUntilChanged().collect{
                if(it.isNullOrEmpty()){
                    Log.d("SET", ":emtpy settings ")
                }
                else {
                    _unitList.value = it
                }
            }
        }
    }


     fun insertUnit(unit : com.example.jetweatherapp.model.Unit) = viewModelScope.launch { repository.insertUnit(unit = unit)}

     fun updateUnit(unit : com.example.jetweatherapp.model.Unit) = viewModelScope.launch { repository.updateUnit(unit = unit)}

     fun  deleteAllUnit() = viewModelScope.launch {repository.deleteAllUnit()  }

     fun deleteUnit(unit : com.example.jetweatherapp.model.Unit) = viewModelScope.launch { repository.deleteUnit(unit = unit ) }




}