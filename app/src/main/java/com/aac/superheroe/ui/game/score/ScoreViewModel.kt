package com.aac.superheroe.ui.game.score

import android.util.Log
import androidx.lifecycle.ViewModel

/** ViewModel for the final screen showing the score */
class ScoreViewModel : ViewModel() {
    // The final score
    var score = "0"

    init {
        Log.e("ScoreViewModel", "Final score is $score")
    }
}