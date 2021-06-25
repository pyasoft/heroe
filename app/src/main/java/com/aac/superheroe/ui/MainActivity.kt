package com.aac.superheroe.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aac.superheroe.R
import com.aac.superheroe.databinding.ActivityMainBinding
import com.aac.superheroe.ui.game.title.TitleActivity
import com.aac.superheroe.ui.heroe.list.HeroeActivity
import com.aac.superheroe.ui.heroe.listtab.HeroeTabActivity
import com.aac.superheroe.ui.updatedata.UpdateDataActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnStartUpdateDataActivity.setOnClickListener {
            val intent = Intent(this, UpdateDataActivity::class.java)
            runStarActivity(intent)
        }

        binding.btnStartHeroeAllActivity.setOnClickListener {
            val intent = Intent(this, HeroeActivity::class.java)
            runStarActivity(intent)
        }

        binding.btnStartHeroeCatActivity.setOnClickListener {
            val intent = Intent(this, HeroeTabActivity::class.java)
            runStarActivity(intent)
        }

        binding.btnStartJuegoActivity.setOnClickListener {
            val intent = Intent(this, TitleActivity::class.java)
            runStarActivity(intent)
        }

    }

    private fun runStarActivity(intent: Intent) {
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

}