import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun login(navController: NavHostController) {

    val textMod = Modifier
        .border(width = 1.dp, color = Color.Red)
        .padding(7.dp)

    // Variables pour stocker les valeurs saisies dans le formulaire
    var loginValue by remember { mutableStateOf("") }
    var mdpValue by remember { mutableStateOf("") }

    // Récupérer le contrôleur du clavier

    // Centrer le contenu verticalement et horizontalement
    Column(
        modifier = Modifier
            .fillMaxSize() // Utilisez fillMaxSize pour occuper tout l'espace disponible
            .padding(16.dp), // Ajoutez un padding pour un espace uniforme autour du formulaire
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        // Formulaire pour saisir le nom
        TextField(
            value = loginValue,
            onValueChange = { loginValue = it },
            label = { Text("Login") },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),

        )

        // Ajouter un espace vertical entre les champs de formulaire
        Spacer(modifier = Modifier.height(16.dp))

        // Formulaire pour saisir le mot de passe
        TextField(
            value = mdpValue,
            onValueChange = { mdpValue = it },
            label = { Text("Mot de passe") },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            visualTransformation = PasswordVisualTransformation()
        )

        // Ajouter un espace vertical avant le bouton de soumission
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            navController.navigate("facture/$loginValue/$mdpValue")
        }){
            Text(text = "Connexion")
        }
    }
}
