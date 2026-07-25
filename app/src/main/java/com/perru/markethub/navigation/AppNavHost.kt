package com.perru.markethub.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.perru.markethub.ui.screens.onboarding.OnboardingScreen1
import com.perru.markethub.ui.screens.onboarding.OnboardingScreen2
import com.perru.markethub.ui.screens.onboarding.OnboardingScreen3
import com.perru.markethub.ui.screens.about.AboutScreen
import com.perru.markethub.ui.screens.auth.LoginScreen
import com.perru.markethub.ui.screens.auth.RegisterScreen
import com.perru.markethub.ui.screens.home.HomeScreen
import com.perru.markethub.ui.screens.home.HomeScreen2
import com.perru.markethub.ui.screens.onboarding.OnboardingScreen2Preview
import com.perru.markethub.ui.screens.order.OrderUploadScreen
import com.perru.markethub.ui.screens.order.ViewOrdersScreen
import com.perru.markethub.ui.screens.payment.PaymentScreen
import com.perru.markethub.ui.screens.products.AddProductScreen
import com.perru.markethub.ui.screens.products.UpdateProductScreen
import com.perru.markethub.ui.screens.products.ViewProductScreen
import com.perru.markethub.ui.screens.scaffold.ScaffoldScreen
import com.perru.markethub.ui.screens.service.ServicesScreen
import com.perru.markethub.ui.screens.splash.SplashScreen


@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUT_SPLASH
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(ROUT_HOME) {
            HomeScreen(navController)
        }
        composable(ROUT_HOME2) {
            HomeScreen2(navController)
        }
        composable(ROUT_ABOUT) {
            AboutScreen(navController)
        }

        composable(ROUT_PAYMENT) {
            PaymentScreen(navController)
        }
      composable(ROUT_SERVICES) {
           ServicesScreen(navController)
       }

        composable(ROUT_ONBOARDING1) {
            OnboardingScreen1(navController)
        }
        composable(ROUT_ONBOARDING2) {
            OnboardingScreen2(onStartClicked = { navController.navigate(ROUT_ONBOARDING3) }, navController)
        }
        composable(ROUT_ONBOARDING3) {
            OnboardingScreen3(navController)
        }
        composable(ROUT_SPLASH) {
            SplashScreen(navController)
        }
        composable(ROUT_SCAFFOLD) {
            ScaffoldScreen(navController)
        }
        composable(ROUT_REGISTER) {
            RegisterScreen(navController)
        }
        composable(ROUT_LOGIN) {
            LoginScreen(navController)
        }


        composable(ROUT_UPLOAD_ORDER) {
            OrderUploadScreen(navController)
        }

        composable(ROUT_VIEW_ORDER) {
            ViewOrdersScreen(navController)
        }

        composable(ROUTE_ADD_PRODUCT) { AddProductScreen(navController) }

        composable(ROUTE_VIEW_PRODUCTS) { ViewProductScreen(navController) }

        composable(
            ROUTE_UPDATE_PRODUCT,
            arguments = listOf(navArgument("productId") { type = NavType.StringType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")!!
            UpdateProductScreen(navController, productId)
        }




    }
    }
