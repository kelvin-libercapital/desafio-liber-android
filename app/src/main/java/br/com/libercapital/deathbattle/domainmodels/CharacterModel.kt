package br.com.libercapital.deathbattle.domainmodels

import java.io.Serializable

data class CharacterModel(
    val imageUrl: String,
    val name: String,
    val publisher: String,
    val alignment: String,
    val gender: String,
    val race: String,
    val intelligence: Int,
    val strength: Int,
    val speed: Int,
    val durability: Int,
    val power: Int,
    val combat: Int,
    var isSelected: Boolean = false
) : Serializable