package com.example.viridis

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.viridis.Navigation.NavGraph

import com.example.viridis.ui.theme.ViridisTheme
import com.example.viridis.data.AppProvider
import com.google.firebase.BuildConfig
import com.google.firebase.FirebaseApp

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        enableEdgeToEdge()
        setContent {
            ViridisTheme {
                BuildConfig.VERSION_NAME
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}


