package com.example.mathongoandroiassignmenrt.api


import com.example.mathongoandroiassignmenrt.DataModels.RecipeResponse
import com.example.mathongoandroiassignmenrt.DataModels.SimilarRecipe
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {

    @GET("random?apiKey=2695eebcc00c473280ed55b424d7c1ae&number=50")
    @Headers("Content-Type: applicatoin/json")
    suspend fun getPopularRecipes(): RecipeResponse

    @GET("random?apiKey=2695eebcc00c473280ed55b424d7c1ae&number=50")
    @Headers("Content-Type: applicatoin/json")
    suspend fun searchRecipe( @Query("query") userQuery: String):RecipeResponse


    @GET("{id}/similar/?apiKey=2695eebcc00c473280ed55b424d7c1ae")
    @Headers("Content-Type: applicatoin/json")
    suspend fun similarRecipe( @Path("id") id: Int):List<SimilarRecipe>


}