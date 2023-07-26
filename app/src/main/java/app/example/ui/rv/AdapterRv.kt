package app.example.ui.rv

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.example.model.BasicModel

class AdapterRv: RecyclerView.Adapter<BasicHolder>() {
    private var list = listOf<BasicModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasicHolder {
        return HolderFactory.bind(parent, viewType)
    }

    override fun onBindViewHolder(holder: BasicHolder, position: Int) {
        holder.updateView(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        val item = list[position]
        return HolderFactory.itemType(item)
    }

    fun updateList(nList: List<BasicModel>){
        list = nList
        notifyDataSetChanged()
    }
}