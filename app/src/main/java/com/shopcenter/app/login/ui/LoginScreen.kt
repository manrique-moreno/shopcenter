package com.shopcenter.app.login.ui

import android.provider.Settings.Secure.getString
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.shopcenter.app.R
import kotlinx.coroutines.delay

@Preview(showBackground = true)
@Composable
fun LoginScreen() {
    val customFont = FontFamily(Font(R.font.alba____))
    var rotatedX by remember { mutableStateOf(false) }
    var rotatedY by remember { mutableStateOf(false) }
    var animacionTexto by remember { mutableStateOf(100.dp) }

    LaunchedEffect(Unit) {
        rotatedY = true
        delay(2500)
        rotatedX = true
        animacionTexto = 0.dp
    }

    val rotarY by animateFloatAsState(
        targetValue = if (rotatedY) 360f else 0f,
        animationSpec = tween(durationMillis = 2500)
    )

    val rotarX by animateFloatAsState(
        targetValue = if (rotatedX) 20f else 0f,
        animationSpec = tween(durationMillis = 2000)
    )

    val animateTexto by animateDpAsState(
        targetValue = animacionTexto,
        animationSpec = tween(durationMillis = 2000)
    )


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue),
        contentAlignment = Alignment.Center
    ) {
        /*Image(
            painter = rememberAsyncImagePainter(url=),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )*/
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
                color = Color.White,
                modifier = Modifier.fillMaxWidth()

            )
            Text(
                text = "ORIGINALS",
                textAlign = TextAlign.Center,
                fontSize = 30.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.25.em,
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (-150).dp)
            )
        }
        Text(
            text = "La Casa de las Zapatillas @SoloOriginales",
            color = Color.White,
            textAlign = TextAlign.Center,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .offset(y = animateTexto)
                .background(
                    color = Color.Black,
                    shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                )
                .padding(vertical = 10.dp)

        )
        FloatingActionButton(onClick = {

        }, modifier = Modifier.align(Alignment.BottomEnd).padding(40.dp)) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "")
        }
    }
}

fun addGoogle(){
    /*val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(getString(R.string.default_web_client_id))
        .requestEmail()
        .build()

    val googleSignInClient = GoogleSignIn.getClient(this, gso)*/

}