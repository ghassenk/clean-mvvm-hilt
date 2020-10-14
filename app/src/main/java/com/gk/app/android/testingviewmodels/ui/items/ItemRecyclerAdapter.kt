package com.gk.app.android.testingviewmodels.ui.items

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gk.app.android.testingviewmodels.R
import com.gk.app.testingviewmodels.domain.items.Item

class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var title: String?
        set(value) {
            itemView.findViewById<TextView>(R.id.itemTitle)?.text = value
        }
        get() = itemView.findViewById<TextView>(R.id.itemTitle)?.text.toString()
}

class ItemRecyclerAdapter(
    items: List<Item> = emptyList()
) : RecyclerView.Adapter<ItemViewHolder>() {

    private val _items = ArrayList(items)
    private var _selectedPosition: Int? = null
    private var _onItemClick: (position: Int) -> Unit = {}

    fun setOnItemClick(onItemClick: (position: Int) -> Unit) {
        _onItemClick = onItemClick
    }

    fun updateItems(items: List<Item>, selectedPosition: Int?) {
        _items.clear()
        _items.addAll(items)
        _selectedPosition = selectedPosition
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.title = _items[position].id
        holder.itemView.isSelected = (position == _selectedPosition)
        holder.itemView.setOnClickListener { _onItemClick(position) }
    }

    override fun getItemCount(): Int {
        return _items.count()
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
}