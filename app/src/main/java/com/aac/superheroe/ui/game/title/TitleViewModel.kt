package com.aac.superheroe.ui.game.title

import android.util.Log
import androidx.lifecycle.ViewModel

class TitleViewModel : ViewModel() {
    var titleText = ""
    var linea1Text = ""
    var linea2Text = ""

    init {

        titleText = "¿Quien ganaria en un combate?"
        linea1Text = "Elige el superheroe que es mejor."
        linea2Text = "¿Quieres intentarlo...?"
        Log.i("ScoreViewModel", "Final score is $titleText")
    }

}
