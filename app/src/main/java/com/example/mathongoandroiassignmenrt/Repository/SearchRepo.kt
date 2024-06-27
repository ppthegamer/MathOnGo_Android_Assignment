package com.example.mathongoandroiassignmenrt.Repository


import com.example.mathongoandroiassignmenrt.DataModels.RecipeResponse
import com.example.mathongoandroiassignmenrt.DataModels.SimilarRecipe
import com.example.mathongoandroiassignmenrt.api.RetrofitClient

class SearchRepo {

    suspend fun getSearchResult(query:String): RecipeResponse {
        val service = RetrofitClient.getInstance()
        return service.searchRecipe(query)

    }

    suspend fun getSimilarRecipe(id:Int):List<SimilarRecipe>{
        val service =RetrofitClient.getInstance()
        return service.similarRecipe(id)
    }
}