package br.com.libercapital.deathbattle.presentation.characterpicker.view.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import br.com.libercapital.deathbattle.R
import br.com.libercapital.deathbattle.domainmodels.CharacterModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.character_item.view.*

class CharacterViewHolder(
    itemView: View,
    private val onItemClickListener: ((CharacterModel) -> Unit)? = null
) : RecyclerView.ViewHolder(itemView) {

    fun bind(character: CharacterModel) {
        itemView.run {
            info.text = resources.getString(
                R.string.character_infos,
                character.name,
                character.gender,
                character.race,
                character.alignment,
                character.publisher
            )

            setOnClickListener { onItemClickListener?.invoke(character) }

            setupImage(character.imageUrl)
        }
    }

    private fun setupImage(imageUrl: String) {
        Glide.with(itemView).load(imageUrl).into(itemView.picture)
    }
}