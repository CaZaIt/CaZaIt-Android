package org.cazait.cazait_android.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.cazait.cazait_android.data.model.Cafe
import org.cazait.cazait_android.databinding.ItemCafeMainBinding
import org.cazait.cazait_android.ui.adapter.holder.CafesViewHolder
import org.cazait.cazait_android.ui.base.RecyclerItemListener
import org.cazait.cazait_android.ui.viewmodel.CafeListViewModel

class CafeListItemAdapter(
    private val cafeListViewModel: CafeListViewModel,
    private val cafes: List<Cafe>
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