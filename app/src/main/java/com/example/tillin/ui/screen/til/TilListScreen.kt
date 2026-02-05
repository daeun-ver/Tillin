package com.example.tillin.ui.screen.til

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.tillin.ui.theme.PrimaryColor
import com.example.tillin.ui.theme.White

@Composable
fun TilListScreen(onCreate:() -> Unit) {
    Scaffold(
        modifier = Modifier,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onCreate() },
                containerColor = PrimaryColor,
                contentColor = White
            ) {
                Icons.Default.Add
            }
        }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize()
            .padding(padding)) {
            Text("TilListScreen")
        }
    }
}