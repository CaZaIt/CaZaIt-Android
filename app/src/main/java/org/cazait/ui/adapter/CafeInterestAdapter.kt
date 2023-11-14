package org.cazait.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.cazait.databinding.ItemCafeInterestBinding
import org.cazait.data.model.Cafe
import org.cazait.ui.adapter.holder.CafesViewHolder
import org.cazait.ui.base.RecyclerItemListener
import org.cazait.ui.viewmodel.CafeInterestViewModel

class CafeInterestAdapter(
    private val cafeInterestViewModel: CafeInterestViewModel,
    private var cafes: List<Cafe>,
) : RecyclerView.Adapter<CafesViewHolder>() {

    private val onItemClickListener: RecyclerItemListener = object : RecyclerItemListener {
        override fun onItemSelected(cafe: Cafe) {
            cafeInterestViewModel.openCafeDetails(cafe)
        }
    }

    fun updateData(newCafes: List<Cafe>) {
        cafes = newCafes
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CafesViewHolder {
        val listItemBinding =
            ItemCafeInterestBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CafesViewHolder(listItemBinding, cafeInterestViewModel)
    }

    override fun getItemCount() = cafes.size

    override fun onBindViewHolder(holder: CafesViewHolder, position: Int) {
        holder.bindForInterestPage(cafes[position], onItemClickListener)
    }
}
