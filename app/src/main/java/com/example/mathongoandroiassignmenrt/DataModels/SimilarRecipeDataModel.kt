package com.example.mathongoandroiassignmenrt.DataModels

import com.google.gson.annotations.SerializedName

data class SimilarRecipe(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("imageType") val imageType: String,
    @SerializedName("readyInMinutes") val readyInMinutes: Int,
    @SerializedName("servings") val servings: Int,
    @SerializedName("sourceUrl") val sourceUrl: String
)