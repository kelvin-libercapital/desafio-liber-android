package br.com.libercapital.deathbattle.data.repositories

import br.com.libercapital.deathbattle.data.WebServiceClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object CharacterListRepository {

    suspend fun searchCharacter(name: String) = withContext(Dispatchers.IO) {
        WebServiceClient.webService.searchCharacter(name)
            .results
            ?.map { it.toCharactersListModel() }
            ?.toMutableList()
    }
}