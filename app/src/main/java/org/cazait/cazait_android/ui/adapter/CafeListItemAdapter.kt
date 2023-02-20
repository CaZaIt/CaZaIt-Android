package org.cazait.cazait_android.ui.adapter

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import org.cazait.cazait_android.data.model.Cafe
import org.cazait.cazait_android.databinding.ItemCafeMainBinding
import org.cazait.cazait_android.ui.adapter.holder.CafesViewHolder
import org.cazait.cazait_android.ui.base.RecyclerItemListener
import org.cazait.cazait_android.ui.viewmodel.CafeListViewModel
import java.util.logging.Filter

class CafeListItemAdapter(
    private val cafeListViewModel: CafeListViewModel,
    private val cafes: List<Cafe>
) : RecyclerView.Adapter<CafesViewHolder>(), Filterable {

    var filteredCafes = ArrayList<Cafe>()
    var itemFilter = ItemFilter()

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


    override fun getFilter(): Filter {
        return itemFilter
    }

    fun initFilteredCafes() {
        filteredCafes.clear()
        filteredCafes.addAll(cafes)
    }

    inner class ItemFilter : Filter() {
        override fun performFiltering(charSequence: CharSequence): FilterResults {
            val filterString = charSequence.toString()
            val results = FilterResults()
            Log.d(TAG, "charSequence : $charSequence")

            //검색이 필요 없을 경우를 위해 원본 배열을 복제
            val filteredList: ArrayList<Cafe> = ArrayList<Cafe>()
            //공백 제외 아무런 값이 없을 경우 -> 원본 배열
            if (filterString.trim { it <= ' ' }.isEmpty()) {
                results.values = cafes
                results.count = cafes.size
                return results

                return results
                //공백 제외 2글자 이인 경우 -> 이름으로만 검색
            } else if (filterString.trim { it <= ' ' }.length <= 2) {
                for (cafe in cafes) {
                    if (cafe.name.contains(filterString)) {
                        filteredList.add(cafe)
                    }
                }
                //그 외의 경우(공백 제외 2글자 초과) -> 이름/주소로 검색
            } else {
                for (cafe in cafes) {
                    if (cafe.name.contains(filterString) || cafe.address.contains(filterString)) {
                        filteredList.add(cafe)
                    }
                }
            }
            results.values = filteredList
            results.count = filteredList.size

            return results
        }

        @SuppressLint("NotifyDataSetChanged")
        override fun publishResults(charSequence: CharSequence?, filterResults: FilterResults) {
            filteredCafes.clear()
            filteredCafes.addAll(filterResults.values as ArrayList<Cafe>)
            notifyDataSetChanged()
        }
    }
}