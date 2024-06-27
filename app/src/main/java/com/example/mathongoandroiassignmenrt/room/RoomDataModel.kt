package com.example.mathongoandroiassignmenrt.room


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.example.mathongoandroiassignmenrt.DataModels.AnalyzedInstruction
import com.example.mathongoandroiassignmenrt.DataModels.ExtendedIngredient
import com.example.mathongoandroiassignmenrt.DataModels.Recipe
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken

@Entity(tableName = "favorite_recipes")
data class FavoriteRecipeDataModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "recipe") val recipe: List<Recipe>,
    @SerializedName("extendedIngredients") val extendedIngredients: List<ExtendedIngredient>,
    @SerializedName("analyzedInstructions") val analyzedInstructions: List<AnalyzedInstruction>,
    @SerializedName("occasions") val occasions: List<String>
)

class Converters {

    @TypeConverter
    fun fromRecipe(recipe: List<Recipe>): String {
        val gson = Gson()
        return gson.toJson(recipe)
    }

    @TypeConverter
    fun toRecipe(recipeString: String): List<Recipe> {
        val gson = Gson()
        val type = object : TypeToken<List<Recipe>>() {}.type
        return gson.fromJson(recipeString, type)
    }
    @TypeConverter
    fun fromExtendedIngredients(value: String): List<ExtendedIngredient> {
        val listType = object : TypeToken<List<ExtendedIngredient>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toExtendedIngredients(list: List<ExtendedIngredient>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromAnalyzedInstructions(value: String): List<AnalyzedInstruction> {
        val listType = object : TypeToken<List<AnalyzedInstruction>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toAnalyzedInstructions(list: List<AnalyzedInstruction>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromStringList(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toStringList(list: List<String>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}