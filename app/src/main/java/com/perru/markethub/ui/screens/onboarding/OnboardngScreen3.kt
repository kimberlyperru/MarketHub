package com.perru.markethub.ui.screens.onboarding


import androidx.compose.foundation.Image
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.perru.markethub.R
import com.perru.markethub.navigation.ROUT_HOME
import com.perru.markethub.navigation.ROUT_ONBOARDING3
import kotlinx.coroutines.launch

// 1. Defining colors matching "pages.jpeg"
val PrimaryPurple = Color(0xFF915F78)
val TextDark = Color(0xFF1A1A2E)
val TextGray = Color(0xFF7D7D8E)

data class OnboardingPage(
    val title: String,
    val description: String,
    val imageRes: Int
)

val onboardingPages = listOf(
    OnboardingPage(
        title = "Shop and discover your favourite items",
        description = "Explore the latest fashion trends and find your unique style with our curated collection.",
        imageRes = R.drawable.cart
    ),
    OnboardingPage(
        title = "Easy and safe payment",
        description = "Shop your favorite items with just a few taps. with our secure payment system.",
        imageRes = R.drawable.pay
    ),
    OnboardingPage(
        title = "Fast Delivery",
        description = "Get your orders delivered to your doorstep in no time. Fast and reliable service.",
        imageRes = R.drawable.delivery
    )
)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen3(navController: NavController) {
    val pagerState = rememberPagerState { 3 }
    val coroutineScope = rememberCoroutineScope()



        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
        // THE HORIZONTAL PAGER (This handles the swiping)
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { position ->
            val page = onboardingPages[position]

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Image from "pages.jpeg"
                Image(
                    painter = painterResource(id = page.imageRes),
                    contentDescription = null,
                    modifier = Modifier
                        .size(250.dp)
                        .clip(RoundedCornerShape(20.dp))
                )

                Spacer(modifier = Modifier.height(40.dp))

                Text(
                    text = page.title,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextDark,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = page.description,
                    fontSize = 15.sp,
                    color = TextGray,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
            }
        }

        // 3. Indicator Dots
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(vertical = 24.dp)
        ) {
            repeat(3) { index ->
                val isSelected = pagerState.currentPage == index
                Box(
                    modifier = Modifier
                        .size(width = if (isSelected) 16.dp else 8.dp, height = 8.dp)
                        .clip(CircleShape)
                        .background(if (isSelected) Color.Red else TextGray.copy(alpha = 0.3f))
                )
            }
        }

        // 4. Bottom Buttons Navigation
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (pagerState.currentPage < 2) {
                TextButton(onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(2)
                    }
                }) {
                    Text(text = "Skip", color = TextGray, fontSize = 16.sp)
                }
            } else {
                Spacer(modifier = Modifier.width(50.dp))
            }

            Button(
                onClick = {
                    if (pagerState.currentPage < 2) {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    } else {
                        navController.navigate(ROUT_HOME) {
                            popUpTo(ROUT_ONBOARDING3) { inclusive = true }
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = if (pagerState.currentPage == 2) "Start!" else "Next",
                    color = Color.White
                )
            }
        }
    }
}


@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun OnboardingScreen3Preview() {
    OnboardingScreen3(rememberNavController())
}
