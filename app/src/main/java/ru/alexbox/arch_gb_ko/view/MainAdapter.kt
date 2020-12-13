package ru.alexbox.arch_gb_ko.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.alexbox.arch_gb_ko.R
import ru.alexbox.arch_gb_ko.data.SearchResult
import kotlinx.android.synthetic.main.recycler_view_item.view.*

class MainAdapter(private var onListItemClickListener: OnListItemClickListener, private var data: List<SearchResult>) :
    RecyclerView.Adapter<MainAdapter.RecyclerItemViewHolder>() {

    interface OnListItemClickListener {
        fun onItemClick(data: SearchResult)
    }

    fun setData(data: List<SearchResult>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerItemViewHolder {
        return RecyclerItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_view_item, parent, false) as View
        )
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data.get(position))

    }
    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(data: SearchResult) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                itemView.header_item.text = data.text
                itemView.description_item.text = data.meanings?.get(0)?.translation?.translation

                itemView.setOnClickListener { getResult(data) }
            }
        }
    }

    private fun getResult(listItemData: SearchResult) {
        onListItemClickListener.onItemClick(listItemData)
    }
}