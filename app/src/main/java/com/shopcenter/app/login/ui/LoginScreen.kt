package com.shopcenter.app.login.ui

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.shopcenter.app.R
import com.shopcenter.app.login.data.UserG
import com.shopcenter.app.login.ui.components.ButtonLogin
import com.shopcenter.app.login.ui.util.LoginUIState
import kotlinx.coroutines.delay

@Preview(showBackground = true)
@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel()
) {

    val uiState by loginViewModel.uiSate.collectAsStateWithLifecycle()


    val customFont = FontFamily(Font(R.font.alba____))
    var rotatedX by remember { mutableStateOf(false) }
    var rotatedY by remember { mutableStateOf(false) }
    var animatedText by remember { mutableStateOf(150.dp) }

    LaunchedEffect(Unit) {
        rotatedY = true
        delay(2500)
        rotatedX = true
        animatedText = 0.dp
    }

    val rotarY by animateFloatAsState(
        targetValue = if (rotatedY) 360f else 0f,
        animationSpec = tween(durationMillis = 2500), label = ""
    )

    val rotarX by animateFloatAsState(
        targetValue = if (rotatedX) 20f else 0f,
        animationSpec = tween(durationMillis = 2000), label = ""
    )

    val animateTexto by animateDpAsState(
        targetValue = animatedText,
        animationSpec = tween(durationMillis = 2000), label = ""
    )


    if (uiState.isLoading){
        CircularProgressIndicator()
    }else{

        when(uiState){
            is LoginUIState.NoUser -> {
                //Toast.makeText(LocalContext.current, "NOOOOO", Toast.LENGTH_SHORT).show()
                Log.d("logViewModel", "firebaseAuthWithGoogle: ${uiState.errorMessage}")
            }
            is LoginUIState.User -> {
                Toast.makeText(LocalContext.current, "OKKKKKKKK", Toast.LENGTH_SHORT).show()
                Log.d("logViewModel", "firebaseAuthWithGoogle: ${(uiState as LoginUIState.User).dataUser?.displayName}")
            }
        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {

        Column(modifier = Modifier.graphicsLayer {
            rotationY = rotarY
            rotationX = rotarX
            cameraDistance = 8 * density
        }) {
            Text(
                text = "Y",
                fontFamily = customFont,
                fontSize = 350.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.fillMaxWidth()

            )
            Text(
                text = "ORIGINALS",
                textAlign = TextAlign.Center,
                fontSize = 30.sp,
                color = MaterialTheme.colorScheme.secondary,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.25.em,
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (-150).dp)
            )
        }

        ButtonLogin(
            modifier = Modifier
                .offset(y = animateTexto)
                .align(Alignment.BottomCenter),
            loginViewModel = loginViewModel
        )
    }
}