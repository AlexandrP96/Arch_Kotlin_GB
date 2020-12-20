package ru.alexbox.historyscreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.history_recycler_item.view.*
import ru.alexbox.model.data.SearchResult

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.RecyclerViewItemHolder>() {

    private var data: List<SearchResult> = arrayListOf()

    fun setData(data: List<SearchResult>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HistoryAdapter.RecyclerViewItemHolder {
        return RecyclerViewItemHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.history_recycler_item, parent, false) as View
        )
    }

    override fun onBindViewHolder(holder: RecyclerViewItemHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerViewItemHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(data: SearchResult) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                itemView.history_recycler_view.text = data.text
                itemView.setOnClickListener {
                    Toast.makeText(itemView.context, "on click: ${data.text}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}