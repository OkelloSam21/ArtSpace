package com.example.artspace

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.artspace.model.ArtWorkViewModel
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpace()
                }
            }
        }
    }
}

@Composable
fun ArtSpace() {
    val viewModel: ArtWorkViewModel = viewModel()
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        ArtWork(painter = viewModel.artWorks[viewModel.currentArtWork].painter)
        ArtWOrkDescriptor(
            title = viewModel.artWorks[viewModel.currentArtWork].titleResId,
            artist = viewModel.artWorks[viewModel.currentArtWork].artistResId,
            LocalContext.current
        )
        DisplayController(
            onPreviousClick = { viewModel.onPreviousClick() },
            onNextClick = { viewModel.onNextClick() }
        )
    }
}

@Composable
fun ArtWOrkDescriptor(
    @StringRes title: Int,
    @StringRes artist: Int,
    context: Context
) {
    Column {
        Text(text = context.getString(title), style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold))
        Text(text = context.getString(artist), style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray))
    }
   
}


@Composable
fun DisplayController(
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit
) {
    Row (
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ){
        Button(onClick = { onPreviousClick() }) {
            Text(text = "Previous")
        }
        Spacer(modifier = Modifier.width(16.dp))
        Button(onClick = { onNextClick() }) {
            Text(text = "Next")
        }
    }
}

data class ArtWork(
    @DrawableRes val painter: Int,
    @StringRes val titleResId: Int,
    @StringRes val artistResId: Int
)