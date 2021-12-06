package com.example.evaluateroom.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.evaluateroom.databinding.ItemRecyclerviewDatabaseBinding
import com.example.evaluateroom.model.EvaluateEntity

class EvaluateEntityAdapter :
    ListAdapter<EvaluateEntity, EvaluateEntityAdapter.ViewHolder>(MyDiffUtil) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRecyclerviewDatabaseBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val evaluateEntity = getItem(position)
        holder.bind(evaluateEntity)
    }

    class ViewHolder(private val binding: ItemRecyclerviewDatabaseBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(evaluateEntity: EvaluateEntity) {
            binding.evaluateEntity = evaluateEntity
            binding.executePendingBindings()
        }
    }

    companion object MyDiffUtil : DiffUtil.ItemCallback<EvaluateEntity>() {
        override fun areItemsTheSame(oldItem: EvaluateEntity, newItem: EvaluateEntity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: EvaluateEntity, newItem: EvaluateEntity): Boolean {
            return oldItem.id == newItem.id
        }

    }
}