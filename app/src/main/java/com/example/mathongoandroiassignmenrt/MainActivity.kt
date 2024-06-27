package com.example.mathongoandroiassignmenrt


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mathongoandroiassignmenrt.DataModels.Recipe
import com.example.mathongoandroiassignmenrt.Repository.FavouritesRepo
import com.example.mathongoandroiassignmenrt.Repository.RecipeRepo
import com.example.mathongoandroiassignmenrt.Repository.SearchRepo
import com.example.mathongoandroiassignmenrt.ViewModels.FavouritesViewModel
import com.example.mathongoandroiassignmenrt.ViewModels.HomeViewModel
import com.example.mathongoandroiassignmenrt.ViewModels.RecipeItemViewModel
import com.example.mathongoandroiassignmenrt.ViewModels.SearchViewModel
import com.example.mathongoandroiassignmenrt.navigation.BottomNavigationModel

import com.example.mathongoandroiassignmenrt.navigation.NavigationModel
import com.example.mathongoandroiassignmenrt.room.FavouritesRoomDataBase
import com.example.mathongoandroiassignmenrt.screens.FavouritesScreen
import com.example.mathongoandroiassignmenrt.screens.HomeScreen
import com.example.mathongoandroiassignmenrt.screens.RecipeItemScreen
import com.example.mathongoandroiassignmenrt.screens.SearchScreen
import com.example.mathongoandroiassignmenrt.screens.SplashScreen
import com.example.mathongoandroiassignmenrt.ui.theme.MathOnGoAndroiAssignmenrtTheme
import com.google.gson.Gson

class MainActivity : ComponentActivity() {
    val database by lazy { FavouritesRoomDataBase.getDatabase(this) }
    val favouritesRepo by lazy { FavouritesRepo(database.favoriteRecipeDao()) }
    private val homeViewModel: HomeViewModel by viewModels {
        MainViewModelFactory(
            RecipeRepo(),
            null,null
        )
    }
    private val searchViewModel: SearchViewModel by viewModels {
        MainViewModelFactory(
            null,
            SearchRepo(),favouritesRepo
        )
    }
    private val favouritesViewModel: FavouritesViewModel by viewModels {
        MainViewModelFactory(
            null,
           null,favouritesRepo
        )
    }
    private val recipeItemViewModel: RecipeItemViewModel by viewModels {
        MainViewModelFactory(
            null,
            null,favouritesRepo
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MathOnGoAndroiAssignmenrtTheme (darkTheme = false,dynamicColor = false){
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(navController = navController)


                }
            }
        }
    }

    @Composable
    fun MainScreen(navController: NavHostController) {
        NavigationGraph(navController = navController, paddingValues = null)
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    fun HomeScreenComposable() {
        val navController = rememberNavController()
        val bottomBarNavScreen =
            listOf(BottomNavigationModel.HomeScreen, BottomNavigationModel.FavouritesScreen)
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        val showBottomBar = when (currentDestination?.route) {
            BottomNavigationModel.HomeScreen.route,
            BottomNavigationModel.FavouritesScreen.route -> true

            else -> false
        }
        Scaffold(bottomBar = {
            if (showBottomBar) NavigationBar(containerColor = Color.White) {
                bottomBarNavScreen.forEach { screenItems ->
                    NavigationBarItem(colors = NavigationBarItemDefaults.colors(
                        indicatorColor = MaterialTheme.colorScheme.primary,
                        selectedTextColor = MaterialTheme.colorScheme.primary,
                        selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                        unselectedTextColor = MaterialTheme.colorScheme.secondary,
                        unselectedIconColor = MaterialTheme.colorScheme.secondary,
                    ),
                        selected = currentDestination?.hierarchy?.any { it.route == screenItems.route } == true,
                        onClick = {
                            navController.navigate(screenItems.route) {
                                navController.graph.findStartDestination().route?.let {
                                    popUpTo(it) {
                                        saveState = true
                                    }

                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = screenItems.icon,
                                contentDescription = "icon"
                            )
                        },
                        label = {
                            Text(
                                text = screenItems.title
                            )
                        })
                }
            }
        }) { paddingValues ->
            NavHost(
                navController = navController,
                startDestination = BottomNavigationModel.HomeScreen.route,
                modifier = Modifier.padding(paddingValues)
            ) {
                composable(route = BottomNavigationModel.HomeScreen.route) {
                    HomeScreen(homeViewModel = homeViewModel, navController)
                }
                composable(route = BottomNavigationModel.FavouritesScreen.route) {
                    FavouritesScreen(favouritesViewModel=favouritesViewModel,)
                }
                composable(route = NavigationModel.RecipeItemScreen.route + "/{recipeItem}",
                    arguments = listOf(
                        navArgument("recipeItem") {
                            type = NavType.StringType
                        }
                    )) {

                    val recipeJson = it.arguments?.getString("recipeItem")
                    val recipeItem = Gson().fromJson(recipeJson, Recipe::class.java)

                    RecipeItemScreen(recipeItem, paddingValues, recipeItemViewModel = recipeItemViewModel)

                }
                composable(route = NavigationModel.SearchScreen.route) {
                    SearchScreen(searchViewModel = searchViewModel, navController)
                }
            }

        }
    }

    @Composable
    fun NavigationGraph(navController: NavHostController, paddingValues: PaddingValues?) {
        NavHost(
            navController = navController,
            startDestination = NavigationModel.SplashScreen.route
        ) {

            composable(route = NavigationModel.SplashScreen.route) {
                SplashScreen(navController)
            }
            composable(route = NavigationModel.MainScreen.route) {
                HomeScreenComposable()
            }

        }
    }
}