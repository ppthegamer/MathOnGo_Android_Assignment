package com.example.mathongoandroiassignmenrt.ViewModels

import androidx.lifecycle.ViewModel
import com.example.mathongoandroiassignmenrt.Repository.FavouritesRepo
import com.example.mathongoandroiassignmenrt.room.FavoriteRecipeDataModel

class RecipeItemViewModel(private val favouritesRepo: FavouritesRepo):ViewModel() {

    suspend fun addRecipe(favourites: FavoriteRecipeDataModel) {
       favouritesRepo.addRecipe(favourites)

    }
}