package com.example.jetweatherapp.repository

import com.example.jetweatherapp.data.WeatherDao
import com.example.jetweatherapp.model.Favourite
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

class WeatherDbRepository @Inject constructor(private val weatherDao: WeatherDao) {

    fun getFavourites() : Flow<List<Favourite>> = weatherDao.getFavorites()

   suspend fun getFavoritesById(city : String) : Favourite = weatherDao.getFavById(city= city)

    suspend fun insertFavorite(favourite: Favourite)  = weatherDao.insertFavorite(favorite = favourite)

    suspend fun updateFavorite(favourite: Favourite) = weatherDao.updateFavorite(favorite =  favourite)

    suspend fun deleteAllFavourite() = weatherDao.deleteAllFavorites()

    suspend fun deleteFavorite(favourite: Favourite) = weatherDao.deleteFavorite(favorite = favourite)


    // Unit --> Settings get insert update delete deleteAll

    fun getUnits() : Flow<List<Unit>>  = weatherDao.getUnits()

    suspend fun insertUnit(unit : Unit) = weatherDao.insertUnit(unit = unit)

    suspend fun updateUnit(unit : Unit) = weatherDao.updateUnit(unit = unit)

    suspend fun deleteAllUnit() = weatherDao.deleteAllUnits()

    suspend fun deleteUnit(unit : Unit) = weatherDao.deleteUnit(unit = unit)



}