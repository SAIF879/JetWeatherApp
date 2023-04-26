package com.example.jetweatherapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.jetweatherapp.model.Favourite
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {



    // favourite table
    @Query("SELECT * from fav_tbl")
    fun getFavorites(): Flow<List<Favourite>>

    @Query("SELECT * from fav_tbl where city =:city")
    suspend fun getFavById(city: String): Favourite

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: Favourite)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFavorite(favorite: Favourite)

    @Query("DELETE from fav_tbl")
    suspend fun deleteAllFavorites()

    @Delete
    suspend fun deleteFavorite(favorite: Favourite)


    //Unit Table

}


//@Dao
//interface WeatherDao {
//    @Query("SELECT * from fav_tbl")
//    fun getFavorites(): Flow<List<Favorite>>
//
//    @Query("SELECT * from fav_tbl where city =:city")
//    suspend fun getFavById(city: String): Favorite
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertFavorite(favorite: Favorite)
//
//    @Update(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun updateFavorite(favorite: Favorite)
//
//    @Query("DELETE from fav_tbl")
//    suspend fun deleteAllFavorites()
//
//    @Delete
//    suspend fun deleteFavorite(favorite: Favorite)
//
//    // Unit table
//    @Query("SELECT * from settings_tbl")
//    fun getUnits(): Flow<List<Unit>>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertUnit(unit: Unit)
//
//    @Update(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun updateUnit(unit: Unit)
//
//    @Query("DELETE from settings_tbl")
//    suspend fun deleteAllUnits()
//
//    @Delete
//    suspend fun deleteUnit(unit: Unit)
//
//}