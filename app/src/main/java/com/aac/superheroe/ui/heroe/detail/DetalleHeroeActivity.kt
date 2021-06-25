package com.aac.superheroe.ui.heroe.detail

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.aac.superheroe.R
import com.aac.superheroe.databinding.ActivityHeroeDetalleBinding
import com.aac.superheroe.domain.model.HeroeComun
import com.aac.superheroe.domain.util.Resource
import com.aac.superheroe.ui.heroe.HeroeViewModel
import com.aac.superheroe.utils.AppConstants.MESSAGE_id
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetalleHeroeActivity : AppCompatActivity() {

    private val mViewModel: HeroeViewModel by viewModels()

    private lateinit var binding: ActivityHeroeDetalleBinding

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHeroeDetalleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sid = intent.getStringExtra(MESSAGE_id)

        setupObservers(sid!!)
    }

    private fun setupObservers(id: String) {
        mViewModel.getOneID_Room_LivedataResource(id).observe(this, Observer {
            procesarResource(it)
        })
    }

    private fun procesarResource(heroeResource: Resource<HeroeComun>) {
        when (heroeResource.status) {
            Resource.Status.SUCCESS -> {
                //progressBar.visibility = View.GONE
                val heroe = heroeResource.data as HeroeComun
                displayHeroe(heroe)
            }
            Resource.Status.ERROR -> {
                //Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()
                //progressBar.visibility = View.GONE
                Log.e("RecuperarActivity", "onRetrofitRequest: ${heroeResource.message}")
            }
            Resource.Status.LOADING -> {
                //progressBar.visibility = View.VISIBLE
            }
        }
    }

    private fun displayHeroe(heroe: HeroeComun) {
        binding.name.text = heroe.name
        binding.intelligence.text = getString(R.string.intelligence) + " " + heroe.intelligence
        binding.strength.text = getString(R.string.strength) + " " + heroe.strength
        binding.speed.text = getString(R.string.speed) + " " + heroe.speed
        binding.durability.text = getString(R.string.durability) + " " + heroe.durability
        binding.power.text = getString(R.string.power) + " " + heroe.power
        binding.combat.text = getString(R.string.combat) + " " + heroe.combat
        binding.fullName.text = heroe.fullName
        binding.alterEgos.text = heroe.alterEgos
        binding.aliases.text = heroe.aliases
        binding.publisher.text = "- " + heroe.publisher + " -"
        binding.alignment.text =  "(" + heroe.alignment + ")"
        binding.imageViewImagen.loadUrl(heroe.lg)
    }

    private fun ImageView.loadUrl(url: String) {
        Picasso.get().load(url).into(this)
    }

}
