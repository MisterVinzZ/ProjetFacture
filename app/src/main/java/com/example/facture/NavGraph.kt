package com.example.facture


import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import login


@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login"){
        composable(route = "login"){
            login(navController)
        }
        composable(
            route = "facture/{log1}/{mdp1}",
            arguments = listOf(
                navArgument(name = "log1") {
                    type = NavType.StringType
                },
                navArgument(name = "mdp1") {
                    type = NavType.StringType
                },
            )
        ) { backStackEntry ->
            facture(
                navController,
                login = backStackEntry.arguments?.getString("log1"),
                mdp = backStackEntry.arguments?.getString("mdp1").toString()
            )
        }
        composable(
            route = "TTC/{TTC1}/{amountHT}",
            arguments = listOf(
                navArgument("TTC1") {
                    type = NavType.FloatType
                },
                navArgument("amountHT") {  // Ajout de l'argument pour le montant HT
                    type = NavType.FloatType
                }
            )
        ) { backStackEntry ->
            val TTC1 = backStackEntry.arguments?.getFloat("TTC1") ?: 0f
            val amountHT = backStackEntry.arguments?.getFloat("amountHT") ?: 0f  // Récupérer le montant HT
            TTC(navController = navController, TTC1 = TTC1.toString(), amountHT = amountHT.toString()) // Passer le montant HT en tant que chaîne de caractères
        }


    }
}

