package com.example.mathongoandroiassignmenrt.screens


import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.mathongoandroiassignmenrt.CustomComponents.CustomFullRecipeComposable
import com.example.mathongoandroiassignmenrt.CustomComponents.CustomIngredientsComposable
import com.example.mathongoandroiassignmenrt.CustomComponents.CustomSimiarRecipeComposable
import com.example.mathongoandroiassignmenrt.DataModels.Recipe
import com.example.mathongoandroiassignmenrt.ViewModels.SearchViewModel
import com.example.mathongoandroiassignmenrt.room.FavoriteRecipeDataModel
import com.example.mathongoandroiassignmenrt.styles.eighteen500Black
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchScreen(

    searchViewModel: SearchViewModel = viewModel(),
    navController: NavHostController
) {
    val sheetState = rememberModalBottomSheetState()

    var showBottomSheet by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }
     val recipePopularItem by searchViewModel.searchedRecipePopularList.collectAsState()
    var selectedItem by remember { mutableStateOf<Recipe?>(null) }

    Scaffold {
        Column(Modifier.padding(10.dp)) {
            SearchBar(searchText = searchText, onSearchTextChange = {
                searchText = it
                searchViewModel.getSearchResult(searchText)
            }, onClick = { navController.navigateUp() })
            if (searchText.isNotEmpty()) {
                SearchResultList( onItemClick = { selectedRecipe ->

                    selectedItem = selectedRecipe
                    showBottomSheet = true
                },
                    changeState = { showBottomSheet = it },
                    searchResults = recipePopularItem.filter {
                        it.title.contains(
                            searchText,
                            ignoreCase = true
                        )
                    },
                )
            }

        }
        if (showBottomSheet) {
            selectedItem?.let {item-> RecipeBottomSheet(onDismiss = {
                showBottomSheet = it
                searchViewModel.updateImageAndInfoState(true)
                searchViewModel.updateIngredientsState(false)
                searchViewModel.updateRecipeState(false)
                searchViewModel.updateSimilarRecipeState(false)
            }, sheetState = sheetState, recipeItem =  listOf(item), searchViewModel =  searchViewModel) }


        }


    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeBottomSheet(
    onDismiss: (Boolean) -> Unit,
    sheetState: SheetState,
    recipeItem:List<Recipe>,
    searchViewModel: SearchViewModel,
) {
val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    var isFavorite by remember { mutableStateOf(false) }
    val similarRecipeList by searchViewModel.similarRecipeList.collectAsState()
    ModalBottomSheet(containerColor = Color.White,

        onDismissRequest = { onDismiss(false) }, dragHandle = {
            Row(
                Modifier
                    .fillMaxWidth()

                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { onDismiss(false) },
                    modifier = Modifier
                        .size(24.dp)
                        .border(width = 1.dp, shape = CircleShape, color = Color.Black)
                        .padding(2.dp)

                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "back arrow",
                        modifier = Modifier.size(24.dp)
                    )
                }
                Spacer(modifier = Modifier.width(15.dp))
                Text(
                    text = recipeItem.get(0).title,modifier=Modifier.fillMaxWidth(.8f),
                    style = eighteen500Black, maxLines = 1, overflow = TextOverflow.Ellipsis


                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    onClick = {      isFavorite = !isFavorite
                        coroutineScope.launch {
                        searchViewModel.addFavourite(FavoriteRecipeDataModel( recipe = recipeItem,
                            extendedIngredients = recipeItem.get(0).extendedIngredients,
                            analyzedInstructions = recipeItem.get(0).analyzedInstructions,
                            occasions = recipeItem.get(0).occasions))
                    }}, modifier = Modifier
                        .size(30.dp)
                        .clip(shape = CircleShape)
                        .background(
                            Color.White
                        )
                        .padding(5.dp)
                ) {

                    val icon = if (isFavorite) Icons.Default.Favorite else Icons.Outlined.FavoriteBorder
                    val iconColor = if (isFavorite) MaterialTheme.colorScheme.primary else Color.Black


                    Icon(
                        imageVector = icon,
                        contentDescription = "fav", tint = iconColor,
                        modifier = Modifier.size(30.dp)
                    )

                }
            }
        }, sheetState = sheetState
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()

                .padding(WindowInsets.navigationBars.asPaddingValues())
                .clip(shape = RoundedCornerShape(5.dp))
        ) {

            Column(
                modifier = Modifier
                    .verticalScroll(enabled = true, state = scrollState)
                    .padding(bottom = 55.dp)
            ) {

                AnimatedVisibility(visible = searchViewModel.imageAndInfoState) {
                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Image(
                            painter = rememberAsyncImagePainter(model = recipeItem.get(0).image),
                            contentDescription = "image URL",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(360.dp),
                            contentScale = ContentScale.Crop,
                        )
                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            InfoCard(cardHeadingText = "Ready in", cardText = "25 min")
                            InfoCard(cardHeadingText = "Servings", cardText = "2")
                            InfoCard(
                                cardHeadingText = "Price/Serving",
                                cardText = "165 min"
                            )
                        }
                    }

                }
                CustomAnimatedCard(
                    state = searchViewModel.ingredientsState,
                    items = recipeItem,
                    composeItem = {
                        CustomIngredientsComposable(
                            items = recipeItem
                        )
                    },
                    onClick = { searchViewModel.updateIngredientsBoxState(!searchViewModel.ingredientsBoxState) },
                    boxState = searchViewModel.ingredientsBoxState, heading = "Ingredients"
                )
                CustomAnimatedCard(
                    state = searchViewModel.fullRecipeState,
                    items = recipeItem,
                    composeItem = {
                        CustomFullRecipeComposable(
                            recipePopularItem = recipeItem
                        )
                    },
                    onClick = { searchViewModel.updateFullRecipeBoxState(!searchViewModel.fullRecipeBoxState) },
                    boxState = searchViewModel.fullRecipeBoxState, heading = "Full recipe"
                )
                CustomAnimatedCard(
                    state = searchViewModel.similarRecipeState,
                    items = recipeItem,
                    composeItem = {
                        CustomSimiarRecipeComposable(
                            similarRecipeList

                        )
                    },
                    onClick = { searchViewModel.updateSimilarRecipeBoxState(!searchViewModel.similarRecipeBoxState) },
                    boxState = searchViewModel.similarRecipeBoxState, heading = "Similar recipe"
                )

            }
            Button(
                onClick = {
                    if (searchViewModel.imageAndInfoState) {
                        searchViewModel.updateImageAndInfoState(false)
                        searchViewModel.updateIngredientsState(true)
                        searchViewModel.updateRecipeState(false)
                    } else if (searchViewModel.ingredientsState && !searchViewModel.fullRecipeState) {
                        searchViewModel.updateIngredientsBoxState(false)
                        searchViewModel.updateImageAndInfoState(false)
                        searchViewModel.updateIngredientsState(true)
                        searchViewModel.updateRecipeState(true)
                    } else if (searchViewModel.fullRecipeState) {
                        searchViewModel.getSimilarResult(recipeItem.get(0).id)
                        searchViewModel.updateFullRecipeBoxState(false)
                        searchViewModel.updateSimilarRecipeState(true)
                    }
                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp)
                    .align(Alignment.BottomCenter), shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = when {
                        searchViewModel.imageAndInfoState -> "Get ingredients"
                        searchViewModel.ingredientsState && !searchViewModel.fullRecipeState -> "Get full recipe"
                        searchViewModel.fullRecipeState -> "Get similar recipe"
                        else -> "Show Info"
                    }, fontSize = 16.sp, fontWeight = FontWeight.W600
                )
            }
        }
    }
}

@Composable
fun SearchResultItem(recipe: Recipe, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)

            .clickable { onClick() }, verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = Icons.Default.Fastfood, contentDescription = null)
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = recipe.title)
    }
}

@Composable
fun SearchBar(
    searchText: String,
    onSearchTextChange: (String) -> Unit,

    onClick: () -> Unit
) {

    TextField(

        placeholder = { Text(text = "Search any recipe") },
        value = searchText,
        onValueChange = { onSearchTextChange(it) },
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.colors(

            unfocusedContainerColor = Color(0xFFD8E3F1),
            unfocusedIndicatorColor = Color.Transparent,
            focusedContainerColor = Color(0xFFF2F7FD),
            focusedIndicatorColor = Color.Transparent,
        ),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Search",
                modifier = Modifier.clickable { onClick() }
            )

        },
        shape = RoundedCornerShape(15.dp),
    )
}


@Composable
fun SearchResultList(
    searchResults: List<Recipe>,
    changeState: (Boolean) -> Unit,onItemClick: (Recipe) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        items(searchResults) { result ->
            SearchResultItem(recipe = result, onClick = { changeState(true)
            onItemClick(result)})
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CustomAnimatedCard(
    state: Boolean, boxState: Boolean,
    heading: String,
    composeItem: @Composable () -> Unit,
    onClick: () -> Unit,
    items: List<Recipe>
) {
    AnimatedVisibility(visible = state) {
        Box(
            modifier = Modifier
                .padding(15.dp)
                .clickable { onClick() }) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = heading, fontWeight = FontWeight.W500, fontSize = 18.sp)
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "icon"
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                AnimatedVisibility(visible = boxState) {
                    composeItem()
                }

            }

        }
    }
}