package com.example.mathongoandroiassignmenrt.screens


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.mathongoandroiassignmenrt.DataModels.Recipe
import com.example.mathongoandroiassignmenrt.R
import com.example.mathongoandroiassignmenrt.ViewModels.RecipeItemViewModel
import com.example.mathongoandroiassignmenrt.room.FavoriteRecipeDataModel
import com.example.mathongoandroiassignmenrt.styles.InfoCardText
import com.example.mathongoandroiassignmenrt.styles.InstructionText
import com.example.mathongoandroiassignmenrt.styles.SectionTitleText
import com.example.mathongoandroiassignmenrt.styles.fourteen400TextStyle
import com.example.mathongoandroiassignmenrt.styles.twelve400TextStyle
import com.example.mathongoandroiassignmenrt.styles.twenty500TextStyle
import kotlinx.coroutines.launch


@Composable
fun RecipeItemScreen(
    recipeItem: Recipe,
    paddingValues: PaddingValues,
    recipeItemViewModel: RecipeItemViewModel = viewModel()
) {
    var isFavorite by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = paddingValues.calculateBottomPadding())
            .verticalScroll(state = scrollState, enabled = true),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(360.dp)
        ) {

            Image(
                painter = rememberAsyncImagePainter(model = recipeItem.image),
                contentDescription = "image URL",
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                contentScale = ContentScale.Crop,
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black

                            )
                        )
                    )
            )
            IconButton(
                onClick = {
                    isFavorite=!isFavorite
                    coroutineScope.launch {
                        recipeItemViewModel.addRecipe(
                            FavoriteRecipeDataModel(
                                recipe = listOf(
                                    recipeItem
                                ),
                                extendedIngredients = recipeItem.extendedIngredients,
                                analyzedInstructions = recipeItem.analyzedInstructions,
                                occasions = recipeItem.occasions
                            )
                        )
                    }
                }, modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 15.dp, end = 15.dp)
                    .clip(shape = CircleShape)
                    .background(
                        Color.White
                    )
            ) {
                val icon = if (isFavorite) Icons.Default.Favorite else Icons.Outlined.FavoriteBorder
                val iconColor = if (isFavorite) MaterialTheme.colorScheme.primary else Color.Black
                Icon(icon, "icon", tint =iconColor )
            }
            Text(
                text = recipeItem.title,
                style = twenty500TextStyle,
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(15.dp)
            )

        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp), verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                InfoCard(
                    cardHeadingText = "Ready in",
                    cardText = "${recipeItem.cookingMinutes} min"
                )
                InfoCard(cardHeadingText = "Servings", cardText = "${recipeItem.servings}")
                InfoCard(
                    cardHeadingText = "Price/Serving",
                    cardText = "${recipeItem.pricePerServing} min"
                )
            }
            SectionTitleText(text = "Ingredients")

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(15.dp),
                modifier = Modifier.padding(start = 20.dp),
                content = {
                    items(recipeItem.extendedIngredients.size) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Box(
                                modifier = Modifier
                                    .clip(shape = CircleShape)
                                    .size(108.dp)
                            ) {
                                Image(
                                    painter = rememberAsyncImagePainter(
                                        model = "https://i.imgur.com/QJVcxop.jpeg",
                                        placeholder = painterResource(R.drawable.no_image_available)
                                    ),
                                    contentDescription = "image URL",
                                    modifier = Modifier
                                        .size(108.dp),
                                    contentScale = ContentScale.Crop,
                                )
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            InfoCardText(
                                text = recipeItem.extendedIngredients[it].name,
                                color = Color.Black
                            )
                        }

                    }
                })

            SectionTitleText(text = "Instructions")

            InstructionText(text = recipeItem.instructions)
            SectionTitleText(text = "Equipments")

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(15.dp),
                modifier = Modifier.padding(start = 20.dp),
                content = {

                    recipeItem.analyzedInstructions.forEach { instruction ->

                        instruction.steps.forEach { step ->
                            items(step.equipment.size) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Box(
                                        modifier = Modifier
                                            .clip(shape = CircleShape)
                                            .size(108.dp)
                                    ) {
                                        Image(
                                            painter = rememberAsyncImagePainter(
                                                model = "https://i.imgur.com/QJVcxop.jpeg",
                                                placeholder = painterResource(R.drawable.no_image_available)
                                            ),
                                            contentDescription = "image URL",
                                            modifier = Modifier
                                                .size(108.dp),
                                            contentScale = ContentScale.Crop,
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(10.dp))
                                    InfoCardText(
                                        text = step.equipment[it].name,
                                        color = Color.Black
                                    )
                                }
                            }
                        }
                    }

                })
            SectionTitleText("Quick Summary")

            InstructionText(text = recipeItem.summary)
            ExpandableTextContainer(
                textHeading = "Nutrition",
                textContent = "Lorem ipsum dolor sit amet consectetur. Sagittis facilisis aliquet aenean lorem ullamcorper et. Risus lectus id sed fermentum in. At porta sed ut lorem volutpat elementum mi sollicitudin. Laoreet tempor nullam velit dui amet mauris sed ac sem."
            )
            ExpandableTextContainer(
                textHeading = "Bad for health nutrition",
                textContent = "Lorem ipsum dolor sit amet consectetur. Sagittis facilisis aliquet aenean lorem ullamcorper et. Risus lectus id sed fermentum in. At porta sed ut lorem volutpat elementum mi sollicitudin. Laoreet tempor nullam velit dui amet mauris sed ac sem."
            )
            ExpandableTextContainer(
                textHeading = "Good for health nutrition",
                textContent = "Lorem ipsum dolor sit amet consectetur. Sagittis facilisis aliquet aenean lorem ullamcorper et. Risus lectus id sed fermentum in. At porta sed ut lorem volutpat elementum mi sollicitudin. Laoreet tempor nullam velit dui amet mauris sed ac sem."
            )
        }

    }
}

@Composable
fun ExpandableTextContainer(textContent: String, textHeading: String) {
    var isExpanded by remember { mutableStateOf(false) }
    Box(modifier = Modifier
        .background(Color(0xFFF2F7FD))
        .fillMaxWidth()
        .clickable { isExpanded = !isExpanded }
        .padding(10.dp)) {
        Column {
            Row(horizontalArrangement = Arrangement.Center) {
                Text(
                    text = textHeading,
                    fontWeight = FontWeight.W700,
                    fontSize = 14.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    onClick = { isExpanded = !isExpanded },
                    colors = IconButtonDefaults.filledIconButtonColors(Color(0xFF242E42)),
                    modifier = Modifier
                        .size(15.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = "down arrow",
                        tint = Color.White
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            AnimatedVisibility(visible = isExpanded) {

                Text(
                    text = textContent,
                    textAlign = TextAlign.Justify,
                    style = fourteen400TextStyle,
                    color = Color(0xFF606F89)
                )
            }

        }
    }
}

@Composable
fun InfoCard(cardHeadingText: String, cardText: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .width(104.dp)
            .height(56.dp)
            .border(width = 1.dp, shape = RoundedCornerShape(12.dp), color = Color(0xFFE7F0F8))
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = cardHeadingText,

                style = twelve400TextStyle,

                color = Color.LightGray
            )
            Text(
                text = cardText,
                style = twelve400TextStyle,
                color = MaterialTheme.colorScheme.primary
            )

        }
    }
}
