package com.shopcenter.app.login.ui.components

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shopcenter.app.R

@Composable
fun ButtonLogin(modifier: Modifier) {

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

                    }
            )

        }
    }


}
