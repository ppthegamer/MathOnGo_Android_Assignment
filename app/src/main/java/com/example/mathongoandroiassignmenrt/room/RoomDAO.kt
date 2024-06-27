package com.example.mathongoandroiassignmenrt.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteRecipeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(favorite: FavoriteRecipeDataModel)

    @Delete
    suspend fun removeFavorite(favorite: FavoriteRecipeDataModel)

    @Query("SELECT * FROM favorite_recipes")
    fun getAllFavorites(): LiveData<List<FavoriteRecipeDataModel>>
}

