package br.com.libercapital.deathbattle.presentation.characterpicker.viewmodel

import androidx.lifecycle.MutableLiveData
import br.com.libercapital.deathbattle.domainmodels.CharacterModel

interface CharactersListViewModel {

    fun getListLiveData(): MutableLiveData<MutableList<CharacterModel>>

    fun searchCharacter(name: String)
}