package com.shopcenter.app.splash.ui

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowInsetsControllerCompat
import com.shopcenter.app.navigation.NavigationApp
import com.shopcenter.app.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()

        setContent {
            AppTheme(dynamicColor = false) {
                SetStatusBarColor()
                NavigationApp()

            }
        }
    }
}

@Composable
fun SetStatusBarColor() {
    val activity = (LocalContext.current as? Activity)
    val darkIcons = !isSystemInDarkTheme()
    val color = MaterialTheme.colorScheme.background
    SideEffect {
        activity?.window?.let { window ->
            window.statusBarColor = color.hashCode()
            WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = darkIcons
        }
    }
}
