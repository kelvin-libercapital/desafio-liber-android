package br.com.libercapital.deathbattle.presentation.characterpicker.view.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.libercapital.deathbattle.R
import br.com.libercapital.deathbattle.domainmodels.CharacterModel

class CharacterListAdapter : RecyclerView.Adapter<CharacterViewHolder>() {

    var data: MutableList<CharacterModel> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onItemClickListener: ((CharacterModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder =
        CharacterViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.character_item,
                parent,
                false
            ), onItemClickListener
        )

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) =
        holder.bind(data[position])

}



