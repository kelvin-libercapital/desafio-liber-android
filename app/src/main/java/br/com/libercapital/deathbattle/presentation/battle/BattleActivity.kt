package br.com.libercapital.deathbattle.presentation.battle

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import br.com.libercapital.deathbattle.R
import br.com.libercapital.deathbattle.domainmodels.CharacterModel
import br.com.libercapital.deathbattle.presentation.characterpicker.view.CharacterPickerActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_battle.*

class BattleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_battle)

        setupListeners()
    }

    private fun setupListeners() {
        firstCharacterContainer.setOnClickListener {
            startActivityForResult(
                CharacterPickerActivity.getStartIntent(this),
                CharacterPickerActivity.REQUEST_CHARACTER
            )
        }
        secondCharacterContainer.setOnClickListener {
            startActivityForResult(
                CharacterPickerActivity.getStartIntent(this),
                CharacterPickerActivity.REQUEST_CHARACTER + 1
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == CharacterPickerActivity.REQUEST_CHARACTER) {
                setupFirstCharacter(data?.toCharacterModel())
            } else if (requestCode == CharacterPickerActivity.REQUEST_CHARACTER + 1) {
                setupSecondCharacter(data?.toCharacterModel())
            }
            showWinner(data?.toCharacterModel())
        }
    }

    private fun Intent.toCharacterModel(): CharacterModel {
        return getSerializableExtra(CharacterPickerActivity.EXTRA_CHARACTER) as CharacterModel
    }

    private fun CharacterModel.convertToString() =
        resources.getString(
            R.string.character_attributes,
            name, intelligence, strength, speed, durability, power, combat
        )

    private fun setupFirstCharacter(character: CharacterModel?) {
        character?.run {
            firstCharacterInfos.visibility = VISIBLE
            firstPickHint.visibility = GONE

            Glide.with(this@BattleActivity).load(imageUrl).into(firstImage)
            firstAttributes.text = convertToString()
        }
    }

    private fun setupSecondCharacter(character: CharacterModel?) {
        character?.run {
            secondCharacterInfos.visibility = VISIBLE
            secondPickHint.visibility = GONE

            Glide.with(this@BattleActivity).load(imageUrl).into(secondImage)
            secondAttributes.text = convertToString()
        }
    }

    private fun showWinner(character: CharacterModel?) {

    }
}
