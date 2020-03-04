package br.com.libercapital.deathbattle.presentation.characterpicker.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.libercapital.deathbattle.data.repositories.CharacterListRepository
import br.com.libercapital.deathbattle.domainmodels.CharacterModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CharactersListViewModelImpl : ViewModel(), CharactersListViewModel {

    private val listLiveData = MutableLiveData<MutableList<CharacterModel>>()

    override fun getListLiveData() = listLiveData

    override fun searchCharacter(name: String) {
        GlobalScope.launch(Dispatchers.IO) {
            CharacterListRepository.searchCharacter(name)?.let {
                listLiveData.postValue(it)
            }
        }
    }
}