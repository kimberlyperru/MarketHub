package com.perru.markethub.ui.screens.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


@Composable
fun OnboardingScreen1(navController: NavController) { //Style
    Column(modifier = Modifier.fillMaxSize().padding(20.dp)) {
        Text(
            text = "Welcome to MarketHub",
            fontSize = 30.sp,
            color = Color.Red,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.5.sp
            )

        Text(
            text = "Shop with us",
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.SemiBold,
            color = Color.Blue,
            letterSpacing = 0.5.sp,
            fontSize = 20.sp
        )
        Text(
            text = "Please activate e-com service in my Debit/Credit with below mentioned details. I hereby undertake to indemnify the Bank against any fraudulent use, claim, loss or any kind of responsibility that may arise to the Bank in future due to activation of e-com service.",
            fontSize =  13.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            lineHeight = 18.sp,
            textAlign = TextAlign.Justify

        )
    }

















}


@Preview(showBackground = true)
@Composable
fun OnboardingScreen1Preview(){
    OnboardingScreen1(rememberNavController())

}