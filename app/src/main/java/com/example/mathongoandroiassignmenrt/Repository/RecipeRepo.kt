package com.example.mathongoandroiassignmenrt.Repository



import com.example.mathongoandroiassignmenrt.DataModels.RecipeResponse
import com.example.mathongoandroiassignmenrt.api.RetrofitClient


class RecipeRepo {


    suspend fun getPopularResponse(): RecipeResponse {
        val service = RetrofitClient.getInstance()
        val _recipeList = service.getPopularRecipes()

        return _recipeList
    }
}