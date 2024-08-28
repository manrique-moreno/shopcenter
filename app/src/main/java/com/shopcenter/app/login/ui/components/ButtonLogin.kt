package com.shopcenter.app.login.ui.components

import android.app.Activity
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.shopcenter.app.R

@Composable
fun ButtonLogin(modifier: Modifier) {

    var showBottomPhone by remember { mutableStateOf(false) }
    val token = "449698127810-5suabflausfg1cdfuf7u1mq573lm3odp.apps.googleusercontent.com"
    val context = LocalContext.current


    // Contrato para recibir el resultado de la autenticación de Google
    val signInLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        println(task)
        try {
            val account = task.getResult(ApiException::class.java)
            val credential = GoogleAuthProvider.getCredential(account?.idToken, null)
            firebaseAuthWithGoogle(credential)
        } catch (e: ApiException) {
            // Manejar error
            println(e.message)
        }
    }

    if (showBottomPhone) {
        BottomPhone(modifier)
    }else{
        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(
                    color = Color.Black.copy(0.6f),
                    shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                )
                .padding(vertical = 20.dp),
        ) {

            Text(text = "Iniciar sesión con",
                color = Color.White,
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.fillMaxWidth())

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painter = painterResource(id = R.drawable.ic_google),
                    contentDescription = "logo_google",
                    modifier = Modifier
                        .size(55.dp)
                        .clip(CircleShape)
                        .border(border = BorderStroke(1.dp, Color.Black), shape = CircleShape)
                        .clickable {
                            //signIn()
                            val opciones = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                                .requestIdToken(token)
                                .requestEmail()
                                .build()
                            val googleSignInClient = GoogleSignIn.getClient(context, opciones)
                            signInLauncher.launch(googleSignInClient.signInIntent)
                        }
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_facebook),
                    contentDescription = "logo_facebook",
                    modifier = Modifier
                        .size(55.dp)
                        .clip(CircleShape)
                        .clickable {

                        }
                )


                Image(
                    painter = painterResource(id = R.drawable.ic_phone),
                    contentDescription = "call",
                    modifier = Modifier
                        .size(55.dp)
                        .clip(CircleShape)
                        .clickable {
                            showBottomPhone = true
                        }
                )

            }
        }
    }


}

@Preview(showBackground = true)
@Composable
private fun BottomPhone(modifier: Modifier = Modifier) {
    var text by remember {
        mutableStateOf("")
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color.Black.copy(0.6f),
                shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
            )
            .padding(vertical = 20.dp),
    ) {

        Text(text = "Ingrese su número de celular",
            color = Color.White,
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.fillMaxWidth())

        Spacer(modifier = modifier.height(20.dp))

        Row(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            TextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("0")}, keyboardActions = KeyboardActions()
            )


            Image(
                painter = painterResource(id = R.drawable.ic_phone),
                contentDescription = "call",
                modifier = Modifier
                    .size(55.dp)
                    .clip(CircleShape)
                    .clickable {

                    }
            )

        }
    }
}

// Función para autenticar con Firebase usando el token de Google
private fun firebaseAuthWithGoogle(credential: AuthCredential) {
    val auth = FirebaseAuth.getInstance()
    auth.signInWithCredential(credential)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                println("Inicio de sesión exitoso")
                Log.d("FirebaseGoogle", "firebaseAuthWithGoogle: ${auth.currentUser?.displayName}")
            } else {
                println("Inicio de sesión fallido")
                Log.d("FirebaseGoogleError", "firebaseAuthWithGoogle - Error: ${auth.currentUser?.displayName}")
            }
        }
}
