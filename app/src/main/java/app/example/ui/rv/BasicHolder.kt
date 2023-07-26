package app.example.ui.rv

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import app.example.model.BasicModel

abstract class BasicHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun updateView(item: BasicModel)
}