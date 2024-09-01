package com.shopcenter.app.login.ui.components

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import com.shopcenter.app.R
import com.shopcenter.app.login.ui.LoginViewModel
import com.shopcenter.app.ui.theme.black
import com.shopcenter.app.ui.theme.white

@Composable
fun ButtonLogin(modifier: Modifier, loginViewModel: LoginViewModel) {

    //var showBottomPhone by remember { mutableStateOf(false) }
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
            loginViewModel.firebaseAuthWithGoogle(credential)
        } catch (e: ApiException) {
            // Manejar error
            println(e.message)
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = if (isSystemInDarkTheme()) white.copy(0.15f) else black.copy(0.15f),
                shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
            )
            .padding(vertical = 20.dp),
    ) {

        Text(
            text = "Iniciar sesión con",
            color = MaterialTheme.colorScheme.secondary,
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = R.drawable.ic_google),
                contentDescription = "logo_google",
                modifier = Modifier
                    .size(55.dp)
                    .clip(CircleShape)
                    .clickable {
                        //signIn()
                        val opciones = GoogleSignInOptions
                            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
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
                    .clickable { }
            )

            Image(
                painter = painterResource(id = R.drawable.ic_phone),
                contentDescription = "call",
                modifier = Modifier
                    .size(55.dp)
                    .clip(CircleShape)
                    /*.clickable {
                        showBottomPhone = true
                    }*/
            )

        }
    }

    /*if (showBottomPhone) {
        BottomPhone(modifier) { showBottomPhone = false }
    }*/

}