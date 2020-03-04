package br.com.libercapital.deathbattle.mechanism.searchbarview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import br.com.libercapital.deathbattle.R
import kotlinx.android.synthetic.main.search_bar_view_layout.view.*

class SearchBarView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.search_bar_view_layout, this, true)
    }

    fun setOnEditorActionListener(onEditorActionListener: (query: String) -> Unit) {
        searchInput.setOnEditorActionListener { view, _, _ ->
            onEditorActionListener(view.text.toString())
            false
        }
    }

}