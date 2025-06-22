package com.pdm.viridis

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

import com.google.firebase.FirebaseApp


import com.pdm.viridis.ui.theme.ViridisTheme


@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		FirebaseApp.initializeApp(this)
		enableEdgeToEdge()
		setContent {
			ViridisTheme {
				AppNavigation()
			}
		}
	}
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun MainActivityPreview() {
	ViridisTheme {
		AppNavigation()
	}
}

