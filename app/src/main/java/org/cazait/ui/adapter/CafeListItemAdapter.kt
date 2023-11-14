package org.cazait.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.cazait.databinding.ItemCafeMainBinding
import org.cazait.data.model.Cafe
import org.cazait.ui.adapter.holder.CafesViewHolder
import org.cazait.ui.base.RecyclerItemListener
import org.cazait.ui.viewmodel.CafeListViewModel

class CafeListItemAdapter(
    private val cafeListViewModel: CafeListViewModel,
    private val cafes: List<Cafe>,
) : RecyclerView.Adapter<CafesViewHolder>() {

    private val onItemClickListener: RecyclerItemListener = object : RecyclerItemListener {
        override fun onItemSelected(cafe: Cafe) {
            cafeListViewModel.openCafeDetails(cafe)
        }
    }

    override fun getItemCount() = cafes.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CafesViewHolder {
        val listItemBinding = ItemCafeMainBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return CafesViewHolder(listItemBinding, cafeListViewModel)
    }

    override fun onBindViewHolder(holder: CafesViewHolder, position: Int) {
        holder.bindForCafeListPage(cafes[position], onItemClickListener)
    }
}
