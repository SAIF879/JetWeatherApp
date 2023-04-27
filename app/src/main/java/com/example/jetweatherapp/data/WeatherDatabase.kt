package com.example.jetweatherapp.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.jetweatherapp.model.Favourite


@Database(entities = [Favourite::class , Unit::class] , version = 2 , exportSchema = false)
abstract class WeatherDatabase : RoomDatabase(){
     abstract fun WeatherDAo() : WeatherDao

}