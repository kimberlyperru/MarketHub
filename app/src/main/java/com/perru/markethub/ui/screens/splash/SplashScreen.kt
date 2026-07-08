package com.perru.markethub.ui.screens.splash

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.perru.markethub.R
import com.perru.markethub.navigation.ROUT_HOME
import com.perru.markethub.navigation.ROUT_SPLASH
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.perru.markethub.navigation.ROUT_ONBOARDING3
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun SplashScreen(navController: NavController){
    //Navigation
    LaunchedEffect(Unit) {
        delay(2000.milliseconds)
        navController.navigate(ROUT_ONBOARDING3) {
            popUpTo(ROUT_SPLASH) { inclusive = true }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().background(Color.Magenta),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

        ){

        Image(
            painter = painterResource(id = R.drawable.cart ),
            contentDescription = null,
            modifier = Modifier
                .size(250.dp)
                .clip(RoundedCornerShape(20.dp))
        )




    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {

    SplashScreen(rememberNavController())
}
