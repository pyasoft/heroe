package com.aac.superheroe.ui.game.game

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.aac.superheroe.R
import com.aac.superheroe.databinding.ActivityGameBinding
import com.aac.superheroe.domain.model.HeroeComun
import com.aac.superheroe.domain.util.Resource
import com.aac.superheroe.ui.game.score.ScoreActivity
import com.aac.superheroe.utils.AppConstants.MESSAGE_score
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

/** Activity where the game is played */
@AndroidEntryPoint
class GameActivity: AppCompatActivity() {

    private val mViewModel: GameViewModel by viewModels()

    private lateinit var binding: ActivityGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservers()

        initialize()

        updateScoreText()

        binding.correct1Button.setOnClickListener { onCorrect(true) }

        binding.correct2Button.setOnClickListener { onCorrect(false) }

    }

    private fun setupObservers() {
        mViewModel.getAll_Room_LivedataResource().observe(this, Observer { procesarResource(it) })
    }

    private fun procesarResource(lstHeroesResource: Resource<List<HeroeComun>>) {
        when (lstHeroesResource.status) {
            Resource.Status.SUCCESS -> {
                //progressBar.visibility = View.GONE
                //val lstHeroes = lstHeroesResource.data as List<HeroeComun>
                //Log.e("GameActivity", "procesarResource: ${lstHeroes.count()}")
                //mViewModel.cargarlistHeroes(lstHeroes)
                updateHeroe1Text()
                updateHeroe2Text()
            }
            Resource.Status.ERROR -> {
                //Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()
                //progressBar.visibility = View.GONE
                Log.e("GameActivity", "call Error : ${lstHeroesResource.message}")
            }
            Resource.Status.LOADING -> {
                //progressBar.visibility = View.VISIBLE
            }
        }
    }

    /** Methods for buttons presses **/
    private fun onCorrect(bPrimero: Boolean) {
        mViewModel.onCorrect(bPrimero)
        updateHeroe1Text()
        updateHeroe2Text()
        updateScoreText()
    }

    private fun onEndGame() {
        gameFinished()
    }

    /** Methods for updating the UI **/
    private fun initialize() {
        binding.titulo.text = "Quien ganaria en un combate???"

        binding.name1.text = "Batman"
        binding.publisher1.text = "Marvel"
        binding.intelligence1.text = "51"
        binding.strength1.text = "52"
        binding.speed1.text = "53"
        binding.durability1.text = "54"
        binding.power1.text = "55"
        binding.combat1.text = "56"
        binding.imageViewImagen1.loadUrl("https://www.superherodb.com/pictures2/portraits/10/100/1150.jpg")

        binding.name2.text = "Superman"
        binding.publisher2.text = "DC"
        binding.intelligence2.text = "61"
        binding.strength2.text = "62"
        binding.speed2.text = "63"
        binding.durability2.text = "64"
        binding.power2.text = "65"
        binding.combat2.text = "66"
        binding.imageViewImagen2.loadUrl("https://www.superherodb.com/pictures2/portraits/10/100/1151.jpg")
    }

    private fun updateHeroe1Text() {
        binding.name1.text = mViewModel.heroe1.name
        binding.publisher1.text = mViewModel.heroe1.publisher
        binding.intelligence1.text = getString(R.string.intelligence) + " " + mViewModel.heroe1.intelligence
        binding.strength1.text = getString(R.string.strength) + " " + mViewModel.heroe1.strength
        binding.speed1.text = getString(R.string.speed) + " " + mViewModel.heroe1.speed
        binding.durability1.text = getString(R.string.durability) + " " + mViewModel.heroe1.durability
        binding.power1.text = getString(R.string.power) + " " + mViewModel.heroe1.power
        binding.combat1.text = getString(R.string.combat) + " " + mViewModel.heroe1.combat
        binding.imageViewImagen1.loadUrl(mViewModel.heroe1.lg)
    }

    private fun updateHeroe2Text() {
        binding.name2.text = mViewModel.heroe2.name
        binding.publisher2.text = mViewModel.heroe2.publisher
        binding.intelligence2.text = getString(R.string.intelligence) + " " + mViewModel.heroe2.intelligence
        binding.strength2.text = getString(R.string.strength) + " " + mViewModel.heroe2.strength
        binding.speed2.text = getString(R.string.speed) + " " + mViewModel.heroe2.speed
        binding.durability2.text = getString(R.string.durability) + " " + mViewModel.heroe2.durability
        binding.power2.text = getString(R.string.power) + " " + mViewModel.heroe2.power
        binding.combat2.text = getString(R.string.combat) + " " + mViewModel.heroe2.combat
        binding.imageViewImagen2.loadUrl(mViewModel.heroe2.lg)
    }

    private fun updateScoreText() {
        binding.scoreText.text = getString(R.string.score) + " " + mViewModel.score.toString()
        when (mViewModel.lives) {
            2 -> binding.imageViewLive3.setImageResource(resources.getIdentifier("@android:drawable/btn_star_big_off", null, null))
            1 -> binding.imageViewLive2.setImageResource(resources.getIdentifier("@android:drawable/btn_star_big_off", null, null))
            0 -> binding.imageViewLive1.setImageResource(resources.getIdentifier("@android:drawable/btn_star_big_off", null, null))
        }
        if (mViewModel.lives < 1) { onEndGame() }
    }

    /** Called when the game is finished */
    private fun gameFinished() {
        Toast.makeText(this, getString(R.string.Game_has_just_finished), Toast.LENGTH_SHORT).show()

        val intent = Intent(this, ScoreActivity::class.java).apply {
            putExtra(MESSAGE_score, mViewModel.score.toString())
        }
        runStarActivity(intent)
    }

    private fun ImageView.loadUrl(url: String) {
        Picasso.get().load(url).into(this)
    }

    private fun runStarActivity(intent: Intent) {
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

}