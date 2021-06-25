package com.aac.superheroe.ui.heroe

import androidx.lifecycle.*
import com.aac.superheroe.domain.repository.HeroeRepository
import com.aac.superheroe.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class HeroeViewModel @Inject constructor (private val repository: HeroeRepository): ViewModel(){

    fun getAll_Room_LivedataResource() = liveData(Dispatchers.IO) {
        emit(Resource.success( repository.getAll_Room() ))
    }

    fun getCompany_Room_LivedataResource(position: Int) = liveData(Dispatchers.IO) {
        var texto = "Marvel Comics"

        when (position) {
            0 -> texto = "Marvel Comics"
            1 -> texto = "DC Comics"
            2 -> texto = "Dark Horse Comics"
            3 -> texto = "NBC - Heroes"
            4 -> texto = "George Lucas"
        }

        emit(Resource.success( repository.getCompany_Room(texto) ))
    }

    fun getOneID_Room_LivedataResource(id: String) = liveData(Dispatchers.IO) {
        emit(Resource.success( repository.getOneID_Room(id) ))
    }

}