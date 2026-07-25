package com.perru.markethub.ui.screens.onboarding

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.perru.markethub.navigation.ROUT_ONBOARDING3
import com.perru.markethub.navigation.ROUT_REGISTER
import kotlinx.coroutines.launch

// Uniform modern brand palette definition
val PrimaryRed = Color(0xFFBA1A1A)
val BackgroundLight = Color(0xFFFBFBFD) // Sleek luxury backdrop
val TextDark = Color(0xFF1C1C2E)
val TextGray = Color(0xFF8E8E9F)

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
        description = "Shop your favorite items with just a few taps with our secure payment system.",
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
            .background(BackgroundLight)
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        // Skip Button Header Layout
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            if (pagerState.currentPage < 2) {
                TextButton(onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(2)
                    }
                }) {
                    Text(
                        text = "Skip",
                        color = TextGray,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            } else {
                // Invisible spacer to preserve layout height bounds across transitions
                Spacer(modifier = Modifier.height(48.dp))
            }
        }

        // SWIPE CONTAINER WITH HIGHER COMPONENT BALANCE
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
                // High Quality Vector Graphic Scaling
                Image(
                    painter = painterResource(id = page.imageRes),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(280.dp)
                        .padding(12.dp)
                )

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = page.title,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextDark,
                    textAlign = TextAlign.Center,
                    lineHeight = 34.sp,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = page.description,
                    fontSize = 15.sp,
                    color = TextGray,
                    textAlign = TextAlign.Center,
                    lineHeight = 22.sp,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }

        // SMOOTH ACCENTED DYNAMIC INDICATORS
        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.padding(vertical = 32.dp)
        ) {
            repeat(3) { index ->
                val isSelected = pagerState.currentPage == index

                // Animates smoothly when users slide between screens
                val widthAnimation by animateDpAsState(
                    targetValue = if (isSelected) 24.dp else 8.dp,
                    label = "width"
                )

                Box(
                    modifier = Modifier
                        .size(width = widthAnimation, height = 8.dp)
                        .clip(CircleShape)
                        .background(
                            if (isSelected) PrimaryRed else TextGray.copy(alpha = 0.25f)
                        )
                )
            }
        }

        // PRIMARY ACTION BUTTON (FULL ACTION STRATEGY)
        Button(
            onClick = {
                if (pagerState.currentPage < 2) {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                } else {
                    navController.navigate(ROUT_REGISTER) {
                        popUpTo(ROUT_ONBOARDING3) { inclusive = true }
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryRed),
            shape = RoundedCornerShape(14.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text(
                text = if (pagerState.currentPage == 2) "Get Started" else "Next",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingScreen3Preview() {
    OnboardingScreen3(rememberNavController())
}