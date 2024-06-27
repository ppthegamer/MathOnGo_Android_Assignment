package com.example.mathongoandroiassignmenrt.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mathongoandroiassignmenrt.CustomComponents.AllRecipesCard
import com.example.mathongoandroiassignmenrt.ViewModels.FavouritesViewModel

@Composable
fun FavouritesScreen(
    favouritesViewModel: FavouritesViewModel = viewModel(),

    ) {
    val favoriteRecipes by favouritesViewModel.allRecipe.observeAsState(initial = emptyList())

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.padding(top = 10.dp, start = 10.dp, end = 10.dp).fillMaxSize(),
        content = {
            items(favoriteRecipes) {
                AllRecipesCard(item = it.recipe, i = 0) {}

            }
        })
}