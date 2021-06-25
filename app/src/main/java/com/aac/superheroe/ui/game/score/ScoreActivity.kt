package com.aac.superheroe.ui.game.score

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.aac.superheroe.R
import com.aac.superheroe.databinding.ActivityScoreBinding
import com.aac.superheroe.ui.game.title.TitleActivity
import com.aac.superheroe.utils.AppConstants.MESSAGE_score
import dagger.hilt.android.AndroidEntryPoint

/** Fragment where the final score is shown, after the game is over */
@AndroidEntryPoint
class ScoreActivity : AppCompatActivity() {

    private val mViewModel: ScoreViewModel by viewModels()

    private lateinit var binding: ActivityScoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityScoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mscore = intent.getStringExtra(MESSAGE_score)
        initialize(mscore!!)

        updateText()

        binding.playAgainButton.setOnClickListener {
            val intent = Intent(this, TitleActivity::class.java)
            runStarActivity(intent)
        }

    }

    /** Methods for updating the UI **/
    private fun initialize(score: String) {
        binding.scoreText.text = score
        mViewModel.score = score
    }

    private fun updateText() {
        binding.scoreText.text = mViewModel.score
    }

    private fun runStarActivity(intent: Intent) {
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

}
