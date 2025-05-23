package com.example.viridis

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController

import com.example.viridis.navigation.NavGraph
import com.example.viridis.ui.screens.HomeScreen

import com.example.viridis.ui.theme.ViridisTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ViridisTheme {
                val navController = rememberNavController()

                NavGraph(navController = navController)

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainActivityPreview() {
    ViridisTheme {
        val navController = rememberNavController()
        NavGraph(navController = navController)
    }
}
