package com.example.jetweatherapp.screens.settings

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetweatherapp.repository.WeatherDbRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingsViewModal @Inject constructor(private  val repository: WeatherDbRepository) : ViewModel() {


    private  val _unitList = MutableStateFlow<List<Unit>>(emptyList())
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


    suspend fun insertUnit(unit : Unit) = viewModelScope.launch { repository.insertUnit(unit = unit)}

    suspend fun updateUnit(unit : Unit) = viewModelScope.launch { repository.updateUnit(unit = unit)}

    suspend fun  deleteAllUnit() = viewModelScope.launch {repository.deleteAllUnit()  }

    suspend fun deleteUnit(unit : Unit) = viewModelScope.launch { repository.deleteUnit(unit = unit ) }




}