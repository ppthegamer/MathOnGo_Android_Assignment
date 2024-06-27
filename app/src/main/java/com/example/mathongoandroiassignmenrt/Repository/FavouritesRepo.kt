package com.example.mathongoandroiassignmenrt.Repository

import androidx.annotation.Keep
import androidx.lifecycle.LiveData
import com.example.mathongoandroiassignmenrt.room.FavoriteRecipeDao
import com.example.mathongoandroiassignmenrt.room.FavoriteRecipeDataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Keep
class FavouritesRepo(private val recipe: FavoriteRecipeDao) {


    var favouriteRecipe: LiveData<List<FavoriteRecipeDataModel>> = recipe.getAllFavorites()

    suspend fun addRecipe(favourites: FavoriteRecipeDataModel) {
        withContext(Dispatchers.IO) {
            recipe.addFavorite(favourites)
        }

    }

}


