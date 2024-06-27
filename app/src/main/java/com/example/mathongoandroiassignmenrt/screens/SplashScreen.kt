package com.example.mathongoandroiassignmenrt.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.mathongoandroiassignmenrt.R
import com.example.mathongoandroiassignmenrt.navigation.NavigationModel

@Composable
fun SplashScreen(navController: NavHostController){
Box (modifier = Modifier.fillMaxSize()){
    Image(painter = painterResource(id = R.drawable.splash_image), contentDescription ="splash image",Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
    Button(
        onClick = {
           navController.navigate(NavigationModel.MainScreen.route){
               popUpTo(NavigationModel.SplashScreen.route){inclusive=true}
           }
        }, modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp)
            .align(Alignment.BottomCenter), shape = RoundedCornerShape(12.dp)){
    Row {
        Text(text = "Continue with google", fontWeight = FontWeight.W600, fontSize = 16.sp)
    }    
    }
    
}
}