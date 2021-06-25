package com.aac.superheroe.ui.game.title

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.aac.superheroe.R
import com.aac.superheroe.databinding.ActivityTitleBinding
import com.aac.superheroe.ui.game.game.GameActivity
import dagger.hilt.android.AndroidEntryPoint

/** Fragment for the starting or title screen of the app */
@AndroidEntryPoint
class TitleActivity : AppCompatActivity() {

    private val mViewModel: TitleViewModel by viewModels()

    private lateinit var binding: ActivityTitleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTitleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initialize()

        updateText()

        binding.playGameButton.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            runStarActivity(intent)
        }

    }

    /** Methods for updating the UI **/
    private fun initialize() {
        binding.titleText.text = ""
        binding.linea1Text.text = ""
        binding.linea2Text.text = ""

    }

    private fun updateText() {
        binding.titleText.text = mViewModel.titleText
        binding.linea1Text.text = mViewModel.linea1Text
        binding.linea2Text.text = mViewModel.linea2Text
    }

    private fun runStarActivity(intent: Intent) {
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

}