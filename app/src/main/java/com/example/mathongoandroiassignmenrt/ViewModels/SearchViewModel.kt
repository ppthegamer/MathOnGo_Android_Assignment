package com.example.mathongoandroiassignmenrt.ViewModels


import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mathongoandroiassignmenrt.DataModels.Recipe
import com.example.mathongoandroiassignmenrt.DataModels.SimilarRecipe
import com.example.mathongoandroiassignmenrt.Repository.FavouritesRepo
import com.example.mathongoandroiassignmenrt.Repository.SearchRepo
import com.example.mathongoandroiassignmenrt.room.FavoriteRecipeDataModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchViewModel(val repo: SearchRepo,val favoriteRecipeDao: FavouritesRepo) : ViewModel() {

    var imageAndInfoState by mutableStateOf(true)
    var ingredientsState by mutableStateOf(false)
    var fullRecipeState by mutableStateOf(false)
    var ingredientsBoxState by mutableStateOf(false)
    var fullRecipeBoxState by mutableStateOf(false)
    var similarRecipeBoxState by mutableStateOf(false)
    var similarRecipeState by mutableStateOf(false)
    private val _searchedRecipePopularList = MutableStateFlow<List<Recipe>>(emptyList())
    val searchedRecipePopularList: StateFlow<List<Recipe>> get() = _searchedRecipePopularList
    private val _similarRecipeList = MutableStateFlow<List<SimilarRecipe>>(emptyList())
    val similarRecipeList: StateFlow<List<SimilarRecipe>> get() = _similarRecipeList


    fun updateImageAndInfoState(state: Boolean) {
        imageAndInfoState = state
    }

    fun updateIngredientsState(state: Boolean) {
        ingredientsState = state
    }

    fun updateRecipeState(state: Boolean) {
        fullRecipeState = state
    }

    fun updateIngredientsBoxState(state: Boolean) {
        ingredientsBoxState = state
    }

    fun updateFullRecipeBoxState(state: Boolean) {
        fullRecipeBoxState = state
    }

    fun updateSimilarRecipeState(state: Boolean) {
        similarRecipeState = state
    }

    fun updateSimilarRecipeBoxState(state: Boolean) {
        similarRecipeBoxState = state
    }

    fun getSimilarResult(id: Int) {
        viewModelScope.launch {
         try {
             val data = repo.getSimilarRecipe(id)
             _similarRecipeList.value = data
         }   catch (e:Exception){
             Log.e("error",e.message.toString())

         }
        }
    }

suspend fun addFavourite(favoriteRecipeDataModel: FavoriteRecipeDataModel){
    viewModelScope.launch { favoriteRecipeDao.addRecipe(favoriteRecipeDataModel) }
}
    fun getSearchResult(query: String) {
        viewModelScope.launch {

            val data = repo.getSearchResult(query)
            _searchedRecipePopularList.value = data.recipes

        }
    }
}
