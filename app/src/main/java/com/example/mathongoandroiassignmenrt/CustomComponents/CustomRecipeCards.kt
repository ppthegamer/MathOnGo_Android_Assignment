package com.example.mathongoandroiassignmenrt.CustomComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.mathongoandroiassignmenrt.DataModels.Recipe
import com.example.mathongoandroiassignmenrt.R
import com.example.mathongoandroiassignmenrt.styles.CardSubtitleText
import com.example.mathongoandroiassignmenrt.styles.CardTitleText
import com.example.mathongoandroiassignmenrt.styles.InfoCardText

@Composable
fun AllRecipesCard(item: List<Recipe>, i: Int, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp).border(width = 1.dp, shape = RoundedCornerShape(12.dp), color = Color(0xFFE7F0F8))
            .clip(shape = RoundedCornerShape(15.dp))
            .clickable { onClick() }
    ) {

        Row(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = rememberAsyncImagePainter(model = item[i].image,placeholder = painterResource(
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
                    text = item[i].title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W600,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                InfoCardText(text = "Ready in ${item[i].cookingMinutes} min", color = Color.Black)
            }
        }

    }
}
@Composable
fun RecipeCard(recipeItem: Recipe, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(156.dp)
            .clip(shape = RoundedCornerShape(15.dp))
            .clickable { onClick() }

    ) {

        Image(
            painter = rememberAsyncImagePainter(model = recipeItem.image,placeholder = painterResource(
                id = R.drawable.no_image_available
            )),
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
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth()
                    .padding(15.dp),
            ) {
                CardTitleText(text = recipeItem.title)
                CardSubtitleText(text = "Ready in ${recipeItem.cookingMinutes} min")

            }
        }


    }
}

