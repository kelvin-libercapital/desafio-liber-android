package br.com.libercapital.deathbattle.data.remote.model

import br.com.libercapital.deathbattle.domainmodels.CharacterModel
import com.google.gson.annotations.SerializedName

data class CharactersListResponse(
    val results: List<CharacterResponse>?
) {

    data class CharacterResponse(
        val name: String?,
        @SerializedName("powerstats")
        val powerStats: PowerStatsResponse?,
        val biography: BiographyResponse?,
        val appearance: AppearanceResponse?,
        val image: Image?
    ) {

        data class PowerStatsResponse(
            val intelligence: String?,
            val strength: String?,
            val speed: String?,
            val durability: String?,
            val power: String?,
            val combat: String?
        )

        data class BiographyResponse(
            @SerializedName("full-name")
            val fullName: String?,
            val publisher: String?,
            val alignment: String?
        )

        data class AppearanceResponse(
            val gender: String?,
            val race: String?,
            val height: List<String>?,
            val weight: List<String>?
        )

        data class Image(
            val url: String?
        )

        private fun String?.convertNullToEmptyIfNecessary() = if (this == "null" || this == null) "" else this

        private fun String?.convertToInt() = if (this == "null" || this == null) 0 else this.toInt()

        fun toCharactersListModel() =
            CharacterModel(
                image?.url.convertNullToEmptyIfNecessary(),
                name.convertNullToEmptyIfNecessary(),
                biography?.publisher.convertNullToEmptyIfNecessary(),
                biography?.alignment.convertNullToEmptyIfNecessary(),
                appearance?.gender.convertNullToEmptyIfNecessary(),
                appearance?.race.convertNullToEmptyIfNecessary(),
                powerStats?.intelligence.convertToInt(),
                powerStats?.strength.convertToInt(),
                powerStats?.speed.convertToInt(),
                powerStats?.durability.convertToInt(),
                powerStats?.power.convertToInt(),
                powerStats?.combat.convertToInt()
            )
    }
}