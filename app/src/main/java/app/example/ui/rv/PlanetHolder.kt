package app.example.ui.rv

import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import app.example.R
import app.example.db.Dependencies
import app.example.model.BasicModel
import app.example.model.Planet
import app.example.utils.Status
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PlanetHolder(itemView: View) : BasicHolder(itemView) {

    override fun updateView(item: BasicModel) {
        item as Planet

        itemView.findViewById<TextView>(R.id.titleU).text = item.name
        itemView.findViewById<TextView>(R.id.diameterU).text = item.diameter
        itemView.findViewById<TextView>(R.id.populationU).text = item.population

        itemView.findViewById<MaterialButton>(R.id.btnAddFavorite).setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                Dependencies.statisticRepository.insertNewPlanet(item).collect{
                    withContext(Dispatchers.Main){
                        when (it.status) {
                            Status.SUCCESS -> Toast.makeText(itemView.context, "Added", Toast.LENGTH_SHORT).show()
                            Status.ERROR -> Toast.makeText(itemView.context, "Error", Toast.LENGTH_SHORT).show()
                            Status.LOADING -> {}
                        }
                    }
                }
            }
        }

        if (item.isFavorite){
            itemView.findViewById<MaterialButton>(R.id.btnAddFavorite).visibility = View.GONE
            val rv = itemView.findViewById<RecyclerView>(R.id.rvCard)
            val adapter = AdapterRv()
            rv.adapter = adapter
            adapter.updateList(item.filmsOb ?: listOf())
        }
    }
}