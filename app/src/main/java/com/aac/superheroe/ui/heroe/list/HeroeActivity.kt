package com.aac.superheroe.ui.heroe.list

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.aac.superheroe.R
import com.aac.superheroe.databinding.ActivityHeroeBinding
import com.aac.superheroe.domain.model.HeroeComun
import com.aac.superheroe.domain.util.Resource
import com.aac.superheroe.ui.heroe.AdapterOnClick
import com.aac.superheroe.ui.heroe.detail.DetalleHeroeActivity
import com.aac.superheroe.ui.heroe.HeroeListAdapter
import com.aac.superheroe.ui.heroe.HeroeViewModel
import com.aac.superheroe.utils.AppConstants.MESSAGE_id
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HeroeActivity : AppCompatActivity(), AdapterOnClick {

    private val mViewModel: HeroeViewModel by viewModels()

    private lateinit var mAdapter: HeroeListAdapter

    private lateinit var binding: ActivityHeroeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeroeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecyclerView()

        setupObservers()
    }

    private fun setUpRecyclerView() {
        mAdapter = HeroeListAdapter(this)
        //recyclerview.setHasFixedSize(true)
        binding.recyclerview.adapter = mAdapter
        //recyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
    }

    private fun setupObservers() {
        mViewModel.getAll_Room_LivedataResource().observe(this, Observer { lstHeroes ->
            procesarResource(lstHeroes)
        })
    }

    private fun procesarResource(lstHeroesResource: Resource<List<HeroeComun>>) {
        when (lstHeroesResource.status) {
            Resource.Status.SUCCESS -> {
                //binding.progressBar.visibility = View.GONE
                val lstHeroes = lstHeroesResource.data as List<HeroeComun>
                mAdapter.setHeroes(lstHeroes)
            }
            Resource.Status.ERROR -> {
                //Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()
                //binding.progressBar.visibility = View.GONE
                Log.e("RecuperarActivity", "onRetrofitRequest: ${lstHeroesResource.message}")
            }
            Resource.Status.LOADING -> {
                //binding.progressBar.visibility = View.VISIBLE
            }
        }
    }

    //Function to be implemented for adapter click interface
    override fun onClick(heroe: HeroeComun) {
        val intent = Intent(this, DetalleHeroeActivity::class.java).apply {
            putExtra(MESSAGE_id, heroe.id)
        }
        runStarActivity(intent)
    }

    private fun runStarActivity(intent: Intent) {
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

}