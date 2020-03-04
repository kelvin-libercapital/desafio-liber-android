package br.com.libercapital.deathbattle.presentation.characterpicker.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.libercapital.deathbattle.R
import br.com.libercapital.deathbattle.presentation.characterpicker.view.recyclerview.CharacterListAdapter
import br.com.libercapital.deathbattle.presentation.characterpicker.viewmodel.CharactersListViewModel
import br.com.libercapital.deathbattle.presentation.characterpicker.viewmodel.CharactersListViewModelImpl
import kotlinx.android.synthetic.main.activity_character_picker.*

class CharacterPickerActivity : AppCompatActivity() {

    private val viewModel: CharactersListViewModel by viewModels<CharactersListViewModelImpl>()

    private val characterListAdapter: CharacterListAdapter by lazy { CharacterListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_picker)

        setupRecyclerView()
        setupListeners()
        observeChanges()
    }

    private fun setupRecyclerView() {
        charactersList.apply {
            adapter = characterListAdapter
            layoutManager = LinearLayoutManager(
                this@CharacterPickerActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
        }
    }

    private fun setupListeners() {
        searchBarView.setOnEditorActionListener {
            if (loader.visibility != VISIBLE) {
                viewModel.searchCharacter(it)
                loader.visibility = VISIBLE
            }
        }
        characterListAdapter.onItemClickListener = {
            setResult(Activity.RESULT_OK, Intent().apply { putExtra(EXTRA_CHARACTER, it) })
            finish()
        }
    }

    private fun observeChanges() {
        viewModel.getListLiveData().observe(this, Observer { list ->
            characterListAdapter.data = list
            loader.visibility = GONE
        })
    }

    companion object {
        const val EXTRA_CHARACTER = "extra_character"
        const val REQUEST_CHARACTER = 1001

        @JvmStatic
        fun getStartIntent(context: Context) = Intent(context, CharacterPickerActivity::class.java)
    }

}
