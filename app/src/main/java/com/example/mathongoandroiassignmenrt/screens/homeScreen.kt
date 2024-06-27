package com.example.mathongoandroiassignmenrt.screens


import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.mathongoandroiassignmenrt.CustomComponents.AllRecipesCard
import com.example.mathongoandroiassignmenrt.CustomComponents.NativeAdComposableNew
import com.example.mathongoandroiassignmenrt.CustomComponents.RecipeCard
import com.example.mathongoandroiassignmenrt.CustomComponents.ShimmerRowLoading
import com.example.mathongoandroiassignmenrt.ViewModels.HomeViewModel
import com.example.mathongoandroiassignmenrt.constant.NATIVE_AD_ID
import com.example.mathongoandroiassignmenrt.navigation.NavigationModel
import com.example.mathongoandroiassignmenrt.styles.SubtitleText
import com.example.mathongoandroiassignmenrt.styles.TitleText
import com.example.mathongoandroiassignmenrt.styles.fourteenFour700TextStyle
import com.google.gson.Gson


@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = viewModel(),
    navController: NavHostController,


    ) {
val context = LocalContext.current
    LaunchedEffect(Unit) {
        homeViewModel.getPopularRecipe()
        homeViewModel.loadNativeAd(context = context, NATIVE_AD_ID)
    }
    val recipePopularItem by homeViewModel.recipePopularList.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp, start = 10.dp, end = 10.dp)
    ) {
        TitleText(text = "\uD83D\uDC4B Hey <user first name>")

        Spacer(modifier = Modifier.height(2.dp))
        SubtitleText(text = "Discover tasty and healthy recipes")
        Spacer(modifier = Modifier.height(15.dp))
        DisabledSearchBar() {
            navController.navigate(NavigationModel.SearchScreen.route)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Popular Recipes", style = fourteenFour700TextStyle)
        Spacer(modifier = Modifier.height(10.dp))
        if (recipePopularItem.isEmpty()) {
            ShimmerRowLoading()
        } else {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp), content = {
                items(recipePopularItem.size) {

                    RecipeCard(recipePopularItem.get(it), onClick = {
                        val recipeJson = Uri.encode(Gson().toJson(recipePopularItem[it]))
                        navController.navigate(NavigationModel.RecipeItemScreen.route + "/${recipeJson}")
                    })
                }
            })

        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "All Recipes", style = fourteenFour700TextStyle)
        Spacer(modifier = Modifier.height(10.dp))

            LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp), content = {
                items(recipePopularItem.size) {


                    AllRecipesCard(recipePopularItem, it) {
                        val recipeJson = Uri.encode(Gson().toJson(recipePopularItem[it]))
                        navController.navigate(NavigationModel.RecipeItemScreen.route + "/${recipeJson}")
                    }
                    if ((it + 1) % 5 == 0) {
                        NativeAdComposableNew(context = context, state = homeViewModel.state)
                    }
                }
            })



    }

}

@Composable
fun DisabledSearchBar(

    onClick: () -> Unit
) {

    TextField(
        value = "", onValueChange = {},
        enabled = false,
        placeholder = { Text(text = "Search any recipe") },

        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()

            },
        colors = TextFieldDefaults.colors(
            disabledContainerColor = Color(0xFFD8E3F1),
            disabledIndicatorColor = Color.Transparent,
        ),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
            )

        },
        shape = RoundedCornerShape(15.dp),
    )
}

