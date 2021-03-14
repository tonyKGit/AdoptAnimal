package com.example.adoptanimal.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.adoptanimal.R
import com.example.adoptanimal.networkResponse.AnimalInfo
import kotlinx.android.synthetic.main.animal_posts_adapter_row.view.*

class AnimalPostsAdapter: PagedListAdapter<AnimalInfo, AnimalPostsAdapter.MyViewHolder>(DiffUtilCallBack()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AnimalPostsAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.animal_posts_adapter_row,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimalPostsAdapter.MyViewHolder, position: Int) {
        getItem(position)?.let { holder.bindPost(it) }
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val idText = itemView.animalId
        fun bindPost(animalPosts: AnimalInfo){
            with(animalPosts){
                idText.text = animalId.toString()
            }
        }
    }

    class DiffUtilCallBack: DiffUtil.ItemCallback<AnimalInfo>() {
        override fun areItemsTheSame(oldItem: AnimalInfo, newItem: AnimalInfo): Boolean {
            return oldItem.animalId == newItem.animalId
        }

        override fun areContentsTheSame(oldItem: AnimalInfo, newItem: AnimalInfo): Boolean {
            return  oldItem.animalId == newItem.animalId
        }
    }
}