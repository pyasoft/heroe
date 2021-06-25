package com.aac.superheroe.ui.updatedata

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.aac.superheroe.databinding.ActivityUpdateDataBinding
import com.aac.superheroe.domain.model.HeroeComun
import com.aac.superheroe.domain.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateDataActivity : AppCompatActivity() {

    private val mViewModel: UpdateDataViewModel by viewModels()

    private lateinit var binding: ActivityUpdateDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUpdateDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservers()

        initialize()

        binding.updateButton.setOnClickListener {
            binding.updateTextView.text = "Actualizado datos Api en Room"
            mViewModel.grabarRoom()
        }

    }

    private fun setupObservers(){
        mViewModel.totalApi_Livedata.observe(this, Observer { traspasarResource(it) })
        mViewModel.totalRoom_Livedata.observe(this, Observer { traspasarResource(it) })
    }

    private fun traspasarResource(result: Resource<List<HeroeComun>>) {
        when (result.status) {
            Resource.Status.SUCCESS -> {
                binding.progressBar.visibility = View.GONE
                //mViewModel.listaheroe = result.data as List<HeroeComun>
                updateTextSuccess()
            }
            Resource.Status.ERROR -> {
                binding.progressBar.visibility = View.GONE
                Toast.makeText(this, result.message, Toast.LENGTH_SHORT).show()
                Log.e("RecuperarActivity", "onRetrofitRequest: ${result.message}")
                updateTextFailure()
            }
            Resource.Status.LOADING -> {
                binding.progressBar.visibility = View.VISIBLE
                updateTextLoading()
            }
        }
    }

    /** Methods for updating the UI **/
    private fun initialize() {
        binding.regapiTextView.text = "0 Registros en API"
        binding.reglocalTextView.text = "0 Registros en Local"
        binding.avisoTextView.text = "Los datos estan sin actualizar"
        binding.updateTextView.text = "Datos sin importar"
    }

    private fun updateTextLoading() {
        binding.regapiTextView.text = "0 Registros en API"
        binding.reglocalTextView.text = "0 Registros en Local"
        binding.avisoTextView.text = "Los datos se estan actualizando"
        binding.updateTextView.text = "Datos sin importar"
    }

    private fun updateTextSuccess() {
        binding.regapiTextView.text = "Hay " + mViewModel.listaheroeApi.size + " Registros en API"
        binding.reglocalTextView.text = "Hay " + mViewModel.listaheroeRoom.size + " Registros en Local"
        binding.avisoTextView.text = "Los datos estan recuperados"
        binding.updateTextView.text = "Datos sin importar"
    }

    private fun updateTextFailure() {
        binding.regapiTextView.text = "0 Registros en API"
        binding.reglocalTextView.text = "0 Registros en Local"
        binding.avisoTextView.text = "Ha fallado la actualizacion"
        binding.updateTextView.text = "Datos sin importar"
    }

}