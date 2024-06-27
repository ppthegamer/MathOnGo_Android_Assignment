package com.example.mathongoandroiassignmenrt.styles

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp


val fourteen400TextStyle = androidx.compose.ui.text.TextStyle(fontSize = 14.sp, fontWeight = FontWeight.W400)
val twelve500TextStyle = androidx.compose.ui.text.TextStyle(fontSize = 12.sp, fontWeight = FontWeight.W500)
val twelve400TextStyle = androidx.compose.ui.text.TextStyle(fontSize = 12.sp, fontWeight = FontWeight.W400)
val fourteenFour700TextStyle = androidx.compose.ui.text.TextStyle(fontSize = 14.sp, fontWeight = FontWeight.W700)
val fourteen700TextStyle = androidx.compose.ui.text.TextStyle(fontSize = 14.sp, fontWeight = FontWeight.W700)
val twenty500TextStyle = androidx.compose.ui.text.TextStyle(fontSize = 20.sp, fontWeight = FontWeight.W500)
val eighteen500Black = androidx.compose.ui.text.TextStyle(
    fontSize = 18.sp,
    fontWeight = FontWeight.W500,
    color = Color.Black
)


@Composable
fun TitleText(text: String) {
    Text(
        text = text,
        fontWeight = FontWeight.W500,
        fontSize = 16.sp
    )
}

@Composable
fun SubtitleText(text: String) {
    Text(
        text = text,
        style = twelve400TextStyle
    )
}

@Composable
fun SectionTitleText(text: String) {
    Text(
        text = text,
        style = fourteenFour700TextStyle



    )
}

@Composable
fun CardTitleText(text: String) {
    Text(
        text = text,
        fontSize = 14.sp,
        fontWeight = FontWeight.W600,
        color = Color.White,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1
    )
}

@Composable
fun CardSubtitleText(text: String) {
    Text(
        text = text,
        style = twelve400TextStyle,
        color = Color.White
    )
}

@Composable
fun InfoCardText(text: String, color: Color) {
    Text(
        text = text,
        style = twelve400TextStyle,
        color = color
    )
}

@Composable
fun InstructionText(text: String) {
    Text(
        text = text,textAlign = TextAlign.Justify,
        style = fourteen400TextStyle,
        color = Color(0xFF606F89)
    )
}
