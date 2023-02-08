package org.cazait.cazait_android.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.cazait.cazait_android.data.model.Cafe
import org.cazait.cazait_android.databinding.ItemCafeMainBinding

class MyAdapter()
    : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    var userList = arrayListOf<Cafe>()

    // 생성된 뷰 홀더에 값 지정
    class MyViewHolder(private val binding: ItemCafeMainBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(currentCafe : Cafe) {
            binding.cafeState = currentCafe
        }
    }

    // 어떤 xml 으로 뷰 홀더를 생성할지 지정
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemCafeMainBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    // 뷰 홀더에 데이터 바인딩
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    // 뷰 홀더의 개수 리턴
    override fun getItemCount(): Int {
        return userList.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
}