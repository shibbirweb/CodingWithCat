package com.swein.recyclerviewexample.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.swein.recyclerviewexample.R
import com.swein.recyclerviewexample.adapter.item.ItemBean
import com.swein.recyclerviewexample.adapter.item.ItemViewHolder

class RecyclerViewAdapter: RecyclerView.Adapter<ItemViewHolder>() {

    interface RecyclerViewAdapterDelegate {
        fun onLoadMore()
    }

    private var mutableList: MutableList<ItemBean> = mutableListOf()

    var delegate: RecyclerViewAdapterDelegate? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_recycler_view_item, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        holder.itemBean = mutableList[position]

        holder.delegate = object : ItemViewHolder.ItemViewHolderDelegate {
            override fun onItemViewClick(itemBean: ItemBean) {
                Log.d("??", "I click ${itemBean.title}")
            }
        }

        holder.updateView()

        // if scroll to last item
        // we need load more data from server
        if (position == mutableList.size - 1) {
            delegate?.onLoadMore()
        }
    }

    override fun getItemCount(): Int {
        return mutableList.size
    }

    fun reload(mutableList: MutableList<ItemBean>) {
        this.mutableList.clear()
        this.mutableList.addAll(mutableList)
        notifyDataSetChanged()
    }

    fun loadMore(mutableList: MutableList<ItemBean>) {
        this.mutableList.addAll(mutableList)
        notifyItemRangeChanged(this.mutableList.size - mutableList.size + 1, mutableList.size)
    }
}