package com.example.facture

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun TTC(navController: NavHostController, TTC1: String, amountHT: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Le montant HT est de : $amountHT") // Affichage du montant HT
        Text("Le montant TTC est de : $TTC1")
        Button(
            onClick = {
                navController.navigateUp() // Naviguer vers le parent de cette destination
            }
        ) {
            Text("Retour")
        }
    }
}
