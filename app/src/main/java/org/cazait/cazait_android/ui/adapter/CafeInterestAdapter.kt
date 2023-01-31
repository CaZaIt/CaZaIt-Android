package org.cazait.cazait_android.ui.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.cazait.cazait_android.data.model.CafeState
import org.cazait.cazait_android.databinding.ItemCafeInterestBinding


class CafeInterestAdapter :
    RecyclerView.Adapter<CafeInterestAdapter.CafeInterestViewHolder>() {

    private val _interest = mutableListOf<CafeState>()
    var interest: List<CafeState> = _interest
        set(value) {
            _interest.clear()
            _interest.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CafeInterestViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val interestItemBinding = ItemCafeInterestBinding.inflate(inflater, parent, false)

        return CafeInterestViewHolder(interestItemBinding)
    }


    override fun onBindViewHolder(
        holder: CafeInterestViewHolder,
        position: Int
    ) {
        holder.bind(_interest[position])

    }

    override fun getItemCount() = _interest.size

    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class CafeInterestViewHolder(val binding: ItemCafeInterestBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cafeState: CafeState) {
            binding.cafeState = cafeState

        }
    }
}