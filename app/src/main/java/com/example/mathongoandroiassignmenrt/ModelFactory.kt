package com.example.mathongoandroiassignmenrt

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mathongoandroiassignmenrt.Repository.FavouritesRepo
import com.example.mathongoandroiassignmenrt.Repository.RecipeRepo
import com.example.mathongoandroiassignmenrt.Repository.SearchRepo
import com.example.mathongoandroiassignmenrt.ViewModels.FavouritesViewModel
import com.example.mathongoandroiassignmenrt.ViewModels.HomeViewModel
import com.example.mathongoandroiassignmenrt.ViewModels.RecipeItemViewModel
import com.example.mathongoandroiassignmenrt.ViewModels.SearchViewModel

class MainViewModelFactory(private val repository:RecipeRepo?,private val searchRepo: SearchRepo?,private val favouritesRepo: FavouritesRepo?):
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return repository?.let { HomeViewModel(it) } as T
        }
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SearchViewModel(searchRepo!!,favouritesRepo!!) as T
        }

        if (modelClass.isAssignableFrom(FavouritesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FavouritesViewModel(favouritesRepo!!) as T
        }
        if (modelClass.isAssignableFrom(RecipeItemViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RecipeItemViewModel(favouritesRepo!!) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}