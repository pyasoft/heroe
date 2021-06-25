package com.aac.superheroe.ui.heroe.listtab

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.aac.superheroe.R
import com.aac.superheroe.databinding.FragmentTabBinding
import com.aac.superheroe.domain.model.HeroeComun
import com.aac.superheroe.domain.util.Resource
import com.aac.superheroe.ui.heroe.AdapterOnClick
import com.aac.superheroe.ui.heroe.detail.DetalleHeroeActivity
import com.aac.superheroe.ui.heroe.HeroeListAdapter
import com.aac.superheroe.ui.heroe.HeroeViewModel
import com.aac.superheroe.utils.AppConstants.MESSAGE_id
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TabFragment (private val position: Int) : Fragment(), AdapterOnClick {

    private val mViewModel: HeroeViewModel by viewModels()

    private lateinit var mAdapter: HeroeListAdapter
    private lateinit var binding: FragmentTabBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tab, container, false)
        //var binding = FragmentTabBinding.inflate(layoutInflater)

        setUpRecyclerView()

        setupObservers()

        return binding.root
    }

    private fun setUpRecyclerView() {
        mAdapter = HeroeListAdapter(this)
        binding.recyclerview.adapter = mAdapter
        binding.recyclerview.layoutManager =  LinearLayoutManager(context)
    }

    private fun setupObservers() {
        //Registrarse como observador en todos los LiveData que necesites.
        mViewModel.getCompany_Room_LivedataResource(position).observe(viewLifecycleOwner, Observer { lstHeroes ->
            //mAdapter.setHeroes(it)
            procesarResource(lstHeroes)
        })
    }

    private fun procesarResource(lstHeroesResource: Resource<List<HeroeComun>>) {
        when (lstHeroesResource.status) {
            Resource.Status.SUCCESS -> {
                //progressBar.visibility = View.GONE
                val lstHeroes = lstHeroesResource.data as List<HeroeComun>
                mAdapter.setHeroes(lstHeroes)
                //updateTextSuccess()
            }
            Resource.Status.ERROR -> {
                //Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()
                //progressBar.visibility = View.GONE
                Log.e("RecuperarActivity", "onRetrofitRequest: ${lstHeroesResource.message}")
                //updateTextFailure()
            }
            Resource.Status.LOADING -> {
                //progressBar.visibility = View.VISIBLE
                //updateTextLoading()
            }
        }

    }

    //Function to be implemented for adapter click interface
    override fun onClick(heroe: HeroeComun) {
        val intent = Intent(activity, DetalleHeroeActivity::class.java).apply {
            putExtra(MESSAGE_id, heroe.id)
        }
        startActivity(intent)
    }

}