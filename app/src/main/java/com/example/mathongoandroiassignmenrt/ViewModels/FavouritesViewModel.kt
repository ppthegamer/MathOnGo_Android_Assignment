package com.example.mathongoandroiassignmenrt.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mathongoandroiassignmenrt.Repository.FavouritesRepo
import com.example.mathongoandroiassignmenrt.room.FavoriteRecipeDataModel
import kotlinx.coroutines.launch

class FavouritesViewModel(private val repo:FavouritesRepo):ViewModel() {

    val allRecipe: LiveData<List<FavoriteRecipeDataModel>> = repo.favouriteRecipe

}