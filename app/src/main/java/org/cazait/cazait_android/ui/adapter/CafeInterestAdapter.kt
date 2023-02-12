package org.cazait.cazait_android.ui.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.cazait.cazait_android.data.model.Cafe
import org.cazait.cazait_android.databinding.ItemCafeInterestBinding
import org.cazait.cazait_android.ui.adapter.holder.CafesViewHolder
import org.cazait.cazait_android.ui.base.RecyclerItemListener
import org.cazait.cazait_android.ui.viewmodel.CafeInterestViewModel


class CafeInterestAdapter(
    private val cafeInterestViewModel: CafeInterestViewModel,
    private val cafes: List<Cafe>
) : RecyclerView.Adapter<CafesViewHolder>() {

    private val onItemClickListener: RecyclerItemListener = object : RecyclerItemListener {
        override fun onItemSelected(cafe: Cafe) {
            cafeInterestViewModel.openCafeDetails(cafe)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CafesViewHolder {
        val listItemBinding =
            ItemCafeInterestBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CafesViewHolder(listItemBinding)
    }

    override fun getItemCount() = cafes.size

    override fun onBindViewHolder(holder: CafesViewHolder, position: Int) {
        holder.bindForInterestPage(cafes[position], onItemClickListener)
    }
}