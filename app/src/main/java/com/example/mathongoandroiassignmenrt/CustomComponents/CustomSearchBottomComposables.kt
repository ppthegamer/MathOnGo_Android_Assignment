package com.example.mathongoandroiassignmenrt.CustomComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.mathongoandroiassignmenrt.DataModels.Recipe
import com.example.mathongoandroiassignmenrt.DataModels.SimilarRecipe
import com.example.mathongoandroiassignmenrt.R
import com.example.mathongoandroiassignmenrt.screens.ExpandableTextContainer
import com.example.mathongoandroiassignmenrt.styles.InfoCardText
import com.example.mathongoandroiassignmenrt.styles.fourteen400TextStyle
import com.example.mathongoandroiassignmenrt.styles.fourteen700TextStyle
import com.example.mathongoandroiassignmenrt.styles.twelve500TextStyle

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CustomIngredientsComposable(items: List<Recipe>) {
    FlowRow(
        maxItemsInEachRow = 3, verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        items.forEach { value ->
            value.extendedIngredients.forEach {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .clip(shape = CircleShape)
                            .size(90.dp)
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(
                                model = "https://i.imgur.com/QJVcxop.jpeg",

                                ),
                            contentDescription = "image",
                            modifier = Modifier
                                .size(108.dp),
                            contentScale = ContentScale.Crop,
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = it.name,
                        style = twelve500TextStyle,
                        color = Color.Black
                    )
                }
            }

        }

    }
}

@Composable
fun CustomFullRecipeComposable(recipePopularItem: List<Recipe>) {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {

        Text(
            text = "Instructions",
            style = fourteen700TextStyle,
            modifier = Modifier
                .align(Alignment.Start)

        )

        Text(
            text = recipePopularItem.get(0).instructions,
            fontWeight = FontWeight.W400,
            fontSize = 14.sp,
            color = Color(0xFF606F89)
        )

        Text(
            text = "Equipments",
            style = fourteen700TextStyle,
            modifier = Modifier
                .align(Alignment.Start)

        )
        Spacer(modifier = Modifier.height(10.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            content = {

                recipePopularItem.get(0).analyzedInstructions.forEach { instruction ->
                    // Iterate over steps within the instruction
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
                                            placeholder = painterResource(
                                                R.drawable.no_image_available
                                            )
                                        ),
                                        contentDescription = "image URL",
                                        modifier = Modifier
                                            .size(108.dp),
                                        contentScale = ContentScale.Crop,
                                    )
                                }
                                Spacer(modifier = Modifier.height(10.dp))
                                Text(
                                    text = step.equipment.get(it).name,
                                    style = twelve500TextStyle,
                                    color = Color.Black
                                )
                            }
                        }
                    }
                }

            })
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Quick Summary",
            style = fourteen700TextStyle,
            modifier = Modifier
                .align(Alignment.Start)

        )

        Text(
            text = recipePopularItem.get(0).summary,
            style = fourteen400TextStyle,
            color = Color(0xFF606F89)
        )

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

@Composable
fun CustomSimiarRecipeComposable(item: List<SimilarRecipe>) {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp), content = {
        item.forEachIndexed { index, value ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp).border(width = 1.dp, shape = RoundedCornerShape(12.dp), color = Color(0xFFE7F0F8))
                    .clip(shape = RoundedCornerShape(15.dp))
                    .clickable {  }
            ) {

                Row(modifier = Modifier.fillMaxSize()) {
                    Image(
                        painter = rememberAsyncImagePainter(model = "https://i.imgur.com/QJVcxop.jpeg",placeholder = painterResource(
                            R.drawable.no_image_available)
                        ),
                        contentDescription = "image URL",
                        modifier = Modifier
                            .width(100.dp)
                            .height(100.dp),
                        contentScale = ContentScale.Crop,
                    )
                    Spacer(modifier = Modifier.width(15.dp))
                    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
                        Text(
                            text = item[index].title,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.W600,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )
                        InfoCardText(text = "Ready in ${item[index].readyInMinutes} min", color = Color.Black)
                    }
                }

            }
        }


    })
}