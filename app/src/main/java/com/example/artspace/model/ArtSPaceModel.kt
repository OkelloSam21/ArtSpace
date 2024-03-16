package com.example.artspace.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.artspace.ArtWork
import com.example.artspace.R

class ArtWorkViewModel : ViewModel() {
    val artWorks = listOf(
        ArtWork(R.drawable.painting1, R.string.painting1_title, R.string.painting1_artist),
        ArtWork(R.drawable.painting2, R.string.painting2_title, R.string.painting2_artist),
        ArtWork(R.drawable.painting3, R.string.painting3_title, R.string.painting3_artist),
        ArtWork(R.drawable.painting4, R.string.painting4_title, R.string.painting4_artist),
        ArtWork(R.drawable.painting5, R.string.painting5_title, R.string.painting5_artist),
    )

    var currentArtWork by mutableIntStateOf(0)

    fun onPreviousClick() {
        if (currentArtWork > 0) currentArtWork--
    }

    fun onNextClick() {
        if (currentArtWork < artWorks.size - 1) currentArtWork++
    }
}