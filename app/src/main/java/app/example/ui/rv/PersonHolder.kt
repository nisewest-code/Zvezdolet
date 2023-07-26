package app.example.ui.rv

import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import app.example.R
import app.example.db.Dependencies
import app.example.db.ModelDao
import app.example.db.ModelDbRepository
import app.example.model.BasicModel
import app.example.model.Person
import app.example.utils.Status
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PersonHolder(itemView: View) : BasicHolder(itemView) {
    override fun updateView(item: BasicModel) {
        item as Person

        itemView.findViewById<TextView>(R.id.titleU).text = item.name
        itemView.findViewById<TextView>(R.id.genderU).text = item.gender
        itemView.findViewById<TextView>(R.id.starshipsU).text = item.starships.size.toString()

        itemView.findViewById<MaterialButton>(R.id.btnAddFavorite).setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                Dependencies.statisticRepository.insertNewPerson(item).collect{
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
            rv.visibility = View.VISIBLE
            val adapter = AdapterRv()
            rv.adapter = adapter
            adapter.updateList(item.filmsOb ?: listOf())
        }
    }
}