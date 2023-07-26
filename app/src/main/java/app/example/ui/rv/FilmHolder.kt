package app.example.ui.rv

import android.view.View
import android.widget.TextView
import app.example.R
import app.example.model.BasicModel
import app.example.model.Film

class FilmHolder(itemView: View) : BasicHolder(itemView) {
    override fun updateView(item: BasicModel) {
        item as Film
        itemView.findViewById<TextView>(R.id.titleU).text = item.name
        itemView.findViewById<TextView>(R.id.directorU).text = item.director
        itemView.findViewById<TextView>(R.id.producerU).text = item.producer
    }
}