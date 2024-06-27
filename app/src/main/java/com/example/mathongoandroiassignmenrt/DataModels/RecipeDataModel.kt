package com.example.mathongoandroiassignmenrt.DataModels

import com.google.gson.annotations.SerializedName


data class RecipeResponse(
    @SerializedName("recipes") val recipes: List<Recipe>
)

data class Recipe(
    @SerializedName("vegetarian") val vegetarian: Boolean,
    @SerializedName("vegan") val vegan: Boolean,
    @SerializedName("glutenFree") val glutenFree: Boolean,
    @SerializedName("dairyFree") val dairyFree: Boolean,
    @SerializedName("veryHealthy") val veryHealthy: Boolean,
    @SerializedName("cheap") val cheap: Boolean,
    @SerializedName("veryPopular") val veryPopular: Boolean,
    @SerializedName("sustainable") val sustainable: Boolean,
    @SerializedName("lowFodmap") val lowFodmap: Boolean,
    @SerializedName("weightWatcherSmartPoints") val weightWatcherSmartPoints: Int,
    @SerializedName("gaps") val gaps: String,
    @SerializedName("preparationMinutes") val preparationMinutes: Int?,
    @SerializedName("cookingMinutes") val cookingMinutes: Int?,
    @SerializedName("aggregateLikes") val aggregateLikes: Int,
    @SerializedName("healthScore") val healthScore: Int,
    @SerializedName("creditsText") val creditsText: String,
    @SerializedName("license") val license: String,
    @SerializedName("sourceName") val sourceName: String,
    @SerializedName("pricePerServing") val pricePerServing: Double,
    @SerializedName("extendedIngredients") val extendedIngredients: List<ExtendedIngredient>,
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("readyInMinutes") val readyInMinutes: Int,
    @SerializedName("servings") val servings: Int,
    @SerializedName("sourceUrl") val sourceUrl: String,
    @SerializedName("image") val image: String,
    @SerializedName("imageType") val imageType: String,
    @SerializedName("summary") val summary: String,
    @SerializedName("cuisines") val cuisines: List<String>,
    @SerializedName("dishTypes") val dishTypes: List<String>,
    @SerializedName("diets") val diets: List<String>,
    @SerializedName("occasions") val occasions: List<String>,
    @SerializedName("instructions") val instructions: String,
    @SerializedName("analyzedInstructions") val analyzedInstructions: List<AnalyzedInstruction>,
    @SerializedName("originalId") val originalId: Any?,
    @SerializedName("spoonacularScore") val spoonacularScore: Double,
    @SerializedName("spoonacularSourceUrl") val spoonacularSourceUrl: String
)

data class ExtendedIngredient(
    @SerializedName("id") val id: Int,
    @SerializedName("aisle") val aisle: String,
    @SerializedName("image") val image: String,
    @SerializedName("consistency") val consistency: String,
    @SerializedName("name") val name: String,
    @SerializedName("nameClean") val nameClean: String,
    @SerializedName("original") val original: String,
    @SerializedName("originalName") val originalName: String,
    @SerializedName("amount") val amount: Double,
    @SerializedName("unit") val unit: String,
    @SerializedName("meta") val meta: List<String>,
    @SerializedName("measures") val measures: Measures
)

data class Measures(
    @SerializedName("us") val us: Us,
    @SerializedName("metric") val metric: Metric
)

data class Us(
    @SerializedName("amount") val amount: Double,
    @SerializedName("unitShort") val unitShort: String,
    @SerializedName("unitLong") val unitLong: String
)

data class Metric(
    @SerializedName("amount") val amount: Double,
    @SerializedName("unitShort") val unitShort: String,
    @SerializedName("unitLong") val unitLong: String
)

data class AnalyzedInstruction(
    @SerializedName("name") val name: String,
    @SerializedName("steps") val steps: List<Step>
)

data class Step(
    @SerializedName("number") val number: Int,
    @SerializedName("step") val step: String,
    @SerializedName("ingredients") val ingredients: List<Ingredient>,
    @SerializedName("equipment") val equipment: List<Equipment>,
    @SerializedName("length") val length: Length?
)

data class Ingredient(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("localizedName") val localizedName: String,
    @SerializedName("image") val image: String
)

data class Equipment(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("localizedName") val localizedName: String,
    @SerializedName("image") val image: String
)

data class Length(
    @SerializedName("number") val number: Int,
    @SerializedName("unit") val unit: String
)



