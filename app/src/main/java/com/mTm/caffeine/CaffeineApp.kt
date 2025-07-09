package com.mTm.caffeine

import Sweet
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mTm.caffeine.ui.screen.drinks.DrinkScreen
import com.mTm.caffeine.ui.screen.customizeOrder.OrderCustomizeScreen
import com.mTm.caffeine.ui.screen.finishOrder.FinishOrderScreen
import com.mTm.caffeine.ui.screen.preparingOrder.PreparingOrderScreen
import com.mTm.caffeine.ui.screen.welcome.WelcomeScreen

@Composable
fun CaffeineApp() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Welcome") {
        composable("Welcome") { WelcomeScreen(navController) }

        composable("Drink") { DrinkScreen(navController) }

        composable(
            route = "OrderCustomize/{orderChoice}",
            arguments = listOf(navArgument("orderChoice") { type = NavType.StringType })
        ) { backStackEntry ->
            val userChoice = backStackEntry.arguments?.getString("orderChoice")
            OrderCustomizeScreen(userChoice = userChoice, navController = navController)
        }

        composable(
            route = "PreparingOrder/{cupSize}",
            arguments = listOf(navArgument("cupSize") { type = NavType.StringType })
        ) { backStackEntry ->
            val selectedCupSize = backStackEntry.arguments?.getString("cupSize")
            PreparingOrderScreen(selectedCupSize = selectedCupSize, navController = navController)
        }

        composable("FinishOrder") { FinishOrderScreen(navController = navController) }

        composable("Sweet") { Sweet(navController = navController) }

    }
}