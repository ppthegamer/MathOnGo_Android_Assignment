package com.example.mathongoandroiassignmenrt.ViewModels


import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mathongoandroiassignmenrt.DataModels.Recipe
import com.example.mathongoandroiassignmenrt.Repository.RecipeRepo
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.nativead.NativeAd
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(val repository: RecipeRepo) : ViewModel() {

    private val _recipePopularList = MutableStateFlow<List<Recipe>>(emptyList())
    val recipePopularList: StateFlow<List<Recipe>> get() = _recipePopularList


    var state by mutableStateOf<NativeAd?>(null)

    fun loadNativeAd(context: Context, adUnitId:String){


        val adLoader = AdLoader.Builder(context, adUnitId)
            .forNativeAd { ad ->
                state = ad
            }
            .withAdListener(object : AdListener() {
                override fun onAdLoaded() {
                    super.onAdLoaded()

                }

                override fun onAdFailedToLoad(error: LoadAdError) {
                    super.onAdFailedToLoad(error)


                }
            })
            .build()
        adLoader.loadAd(AdRequest.Builder().build())

    }
    fun getPopularRecipe() {
        viewModelScope.launch {
            try {
                val data = repository.getPopularResponse()
                _recipePopularList.value = data.recipes
            } catch (e: Exception) {
                Log.e("Error", e.message.toString())
            }
        }
    }


}