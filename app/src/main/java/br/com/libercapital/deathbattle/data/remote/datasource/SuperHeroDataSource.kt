package br.com.libercapital.deathbattle.data.remote.datasource

import br.com.libercapital.deathbattle.data.remote.model.CharactersListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface SuperHeroDataSource {

    @GET("search/{name}")
    suspend fun searchCharacter(@Path("name") name: String): CharactersListResponse

}