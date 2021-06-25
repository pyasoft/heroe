package com.aac.superheroe.ui.game.game

import android.util.Log
import androidx.lifecycle.*
import com.aac.superheroe.domain.model.HeroeComun
import com.aac.superheroe.domain.repository.HeroeRepository
import com.aac.superheroe.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/** ViewModel containing all the logic needed to run the game */
@HiltViewModel
class GameViewModel @Inject constructor (private val repository: HeroeRepository): ViewModel(){

    // The current score
    var score = 0
    var lives = 3

    // The current Heroes
    lateinit var heroe1: HeroeComun
    lateinit var heroe2: HeroeComun

    // The list of Heroes
    private lateinit var heroesList: MutableList<HeroeComun>

    init {
        resetList()
        nextHeroes()
        Log.i("GameViewModel", "GameViewModel created!")
    }

    fun getAll_Room_LivedataResource() = liveData(Dispatchers.IO) {
        val lstHeroes = repository.getAll_Room()
        cargarlistHeroes(lstHeroes)
        emit(Resource.success( heroesList ))
    }

    /** Resets the list of words and randomizes the order */
    private fun resetList() {
        heroesList = mutableListOf(
             HeroeComun("1",
                 "A-Bomb", "38", "100", "17", "80", "24", "64",
                 "Richard Milhouse Jones", "No alter egos found.", "Rick Jones", "Marvel Comics", "good",
                 "https://cdn.jsdelivr.net/gh/akabab/superhero-api@0.3.0/api/images/xs/1-a-bomb.jpg",
                 "https://cdn.jsdelivr.net/gh/akabab/superhero-api@0.3.0/api/images/lg/1-a-bomb.jpg"
            ), HeroeComun("2",
                "Abe Sapien", "88", "28", "35", "65", "100", "85",
                "Abraham Sapien", "No alter egos found.", "Langdon Caul", "Dark Horse Comics", "good",
                "https://cdn.jsdelivr.net/gh/akabab/superhero-api@0.3.0/api/images/xs/2-abe-sapien.jpg",
                "https://cdn.jsdelivr.net/gh/akabab/superhero-api@0.3.0/api/images/lg/2-abe-sapien.jpg"
            )
        )
        heroesList.shuffle()
    }

    /** Callback called when the ViewModel is destroyed */
    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "GameViewModel destroyed!")
    }

    fun cargarlistHeroes(listHeroes: List<HeroeComun>) {
        heroesList.clear()
        listHeroes.forEach {
            heroesList.add(it)
        }
        heroesList.shuffle()

        nextHeroes()
    }

    /** Methods for updating the UI **/
    fun onCorrect(bPrimero: Boolean) {
        comprobar(bPrimero)
        nextHeroes()
    }

    private fun comprobar(bPrimero: Boolean) {

        val iintelligence1 = heroe1.intelligence.toInt()
        val istrength1 = heroe1.strength.toInt()
        val ispeed1 = heroe1.speed.toInt()
        val idurability1 = heroe1.durability.toInt()
        val ipower1 = heroe1.power.toInt()
        val icombat1 = heroe1.combat.toInt()

        val iintelligence2 = heroe2.intelligence.toInt()
        val istrength2 = heroe2.strength.toInt()
        val ispeed2 = heroe2.speed.toInt()
        val idurability2 = heroe2.durability.toInt()
        val ipower2 = heroe2.power.toInt()
        val icombat2 = heroe2.combat.toInt()

        val total1 =
            (icombat1 + ipower1) * 1.4 + (istrength1 + idurability1) * 1.2 + iintelligence1 + ispeed1
        val total2 =
            (icombat2 + ipower2) * 1.4 + (istrength2 + idurability2) * 1.2 + iintelligence2 + ispeed2

        if ((bPrimero && total1 >= total2) || (!bPrimero && total1 <= total2)) {
            score++
        } else {
            lives--
        }

    }

    /** Moves to the next word in the list. */
    private fun nextHeroes() {
        //Select and remove a word from the list
        if (heroesList.isNotEmpty()) {
            heroe1 = heroesList.removeAt(0)
            heroe2 = heroesList.removeAt(0)
        }
    }

}
