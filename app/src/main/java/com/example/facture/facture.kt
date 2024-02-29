package com.example.facture

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.times
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.navigation.NavHostController

@Composable
fun facture(navController: NavHostController, login: String?, mdp: String){

        val quantity = remember { mutableStateOf("") }
        val unitPrice = remember { mutableStateOf("") }
        val amountHT = remember { mutableStateOf("") }
        val tvaRate = remember { mutableStateOf("") }
        val radioValue = remember { mutableStateOf("") }
        val discount = remember { mutableStateOf("") }
        val isLoyalCustomer = remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 60.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

                // Afficher le nom d'utilisateur (login)
            Text(text = "Bonjour  ${login ?: "Non spécifié"}", fontSize = 20.sp, color = Color.Black)

                // Ajouter un espace vertical
            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Facture",fontSize = 24.sp,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 22.dp),

            )

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                TextField(
                    value = quantity.value,
                    onValueChange = { quantity.value = it.filter { char -> char.isDigit() } },
                    label = { Text("Quantité") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { /* Handle next action if needed */ }
                    )
                )

                TextField(
                    value = unitPrice.value,
                    onValueChange = { unitPrice.value = it.filter { char -> char.isDigit() } },
                    label = { Text("Prix unitaire") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { /* Handle next action if needed */ }
                    )
                )

                TextField(
                    value = amountHT.value,
                    onValueChange = { amountHT.value = it.filter { char -> char.isDigit() } },
                    label = { Text("Montant HT") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { /* Handle next action if needed */ }
                    )
                )

                TextField(
                    value = tvaRate.value,
                    onValueChange = { tvaRate.value = it.filter { char -> char.isDigit() } },
                    label = { Text("Taux TVA") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { /* Handle done action if needed */ }
                    )
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Client fidèle")
                    RadioButton(
                            selected = radioValue.value == "Client Fidèle",
                            onClick = { radioValue.value = "Client Fidèle" },
                    )
                    Text("Client Infidèle")
                    RadioButton(
                        selected = radioValue.value == "Client Infidèle",
                        onClick = { radioValue.value = "Client Infidèle" },
                    )
                }
                TextField(
                    value = discount.value,
                    onValueChange = { discount.value = it.filter { char -> char.isDigit() } },
                    label = { Text("Remise") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { /* Handle done action if needed */ }
                    )
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = {
                            // Calcul du montant HT
                            val quantityValue = quantity.value.toFloatOrNull() ?: 0f
                            val unitPriceValue = unitPrice.value.toFloatOrNull() ?: 0f
                            val amountHTValue = quantityValue * unitPriceValue
                            val tvaRateValue = tvaRate.value.toFloatOrNull() ?: 0f


                            // Calcul du montant TTC
                            val discountValue = discount.value.toFloatOrNull() ?: 0f // Récupérer la valeur de la remise
                            val discountRate = if (radioValue.value == "Client Fidèle") discountValue else 0f // Appliquer la remise uniquement si le client est fidèle
                            val TTCValue = amountHTValue + amountHTValue * tvaRateValue / 100 - amountHTValue * discountRate / 100

                            // Navigation vers la page TTC avec les valeurs calculées
                            navController.navigate("TTC/$TTCValue/$amountHTValue")
                        },
                        modifier = Modifier
                            .padding(16.dp)
                            .height(IntrinsicSize.Min)
                    ) {
                        Text("Calculer TTC")
                    }

                    Button(
                        onClick = {
                            // Remettre à zéro les champs du formulaire
                            quantity.value = ""
                            unitPrice.value = ""
                            amountHT.value = ""
                            tvaRate.value = ""
                            isLoyalCustomer.value = false
                        },
                        modifier = Modifier
                            .padding(16.dp)
                            .height(IntrinsicSize.Min)
                    ) {
                        Text("Remise à zéro")
                    }
                }
            }
            // Bouton pour revenir à l'écran de connexion
            Button(onClick = {
                navController.navigate("login")
            }) {
                Text(text = "Retour à l'écran de connexion", fontSize = 20.sp)
            }
        }
    }

