package org.cazait.cazait_android.ui.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import org.cazait.cazait_android.data.model.Cafe

object BindingAdapter{

    @BindingAdapter("items")
    @JvmStatic
    fun setItems(recyclerView: RecyclerView, items : ArrayList<Cafe>){

        if(recyclerView.adapter == null) {
            val adapter = MyAdapter()
            adapter.setHasStableIds(true)
            recyclerView.adapter = adapter
        }

        val myAdapter = recyclerView.adapter as MyAdapter

        myAdapter.userList = items
        myAdapter.notifyDataSetChanged()
    }

}