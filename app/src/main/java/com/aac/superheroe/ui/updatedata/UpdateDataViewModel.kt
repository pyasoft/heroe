package com.aac.superheroe.ui.updatedata

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.aac.superheroe.domain.model.HeroeComun
import com.aac.superheroe.domain.repository.HeroeRepository
import com.aac.superheroe.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateDataViewModel @Inject constructor(private val repository: HeroeRepository): ViewModel() {
    lateinit var listaheroeApi: List<HeroeComun>
    lateinit var listaheroeRoom: List<HeroeComun>

    val totalApi_Livedata = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        try{
            listaheroeApi = repository.getAll_API()
            emit(Resource.success(listaheroeApi))
        }catch (e: Exception){
            emit(Resource.error(e.message.toString(),null))
        }
    }

    val totalRoom_Livedata: LiveData<Resource<List<HeroeComun>>> = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        try{
            listaheroeRoom = repository.getAll_Room()
            emit(Resource.success(listaheroeRoom))
        }catch (e: Exception){
            emit(Resource.error(e.message.toString(),null))
        }
    }

    fun grabarRoom() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteALL_Room()
        repository.insertALL_Room(listaheroeApi)
    }

}