package com.example.mathongoandroiassignmenrt.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavigationModel (val route:String,val title:String,val icon:ImageVector){

    data object HomeScreen:BottomNavigationModel(route = "home","Home", icon = Icons.Default.Home)
    data object FavouritesScreen:BottomNavigationModel(route = "favourites","Favourites", icon = Icons.Outlined.FavoriteBorder)
}

sealed class NavigationModel(val route:String,){
    data object RecipeItemScreen:NavigationModel(route = "recipe_item_screen")
    data object SearchScreen:NavigationModel(route = "search_screen")
    data object MainScreen:NavigationModel(route = "main_screen")
    data object SplashScreen:NavigationModel(route = "splash_screen")
}