package com.example.evaluateappfinal.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.evaluateroom.databinding.ItemRecyclerviewBinding
import com.example.evaluateroom.model.Evaluate

class EvaluateAdapter(val clickListener: (evaluate: Evaluate) -> Unit) :
    ListAdapter<Evaluate, EvaluateAdapter.ViewHolder>(MyDiffUtil) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRecyclerviewBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val evaluate = getItem(position)
        holder.itemView.setOnClickListener {
            clickListener(evaluate)
        }
        holder.bind(evaluate)
    }


    class ViewHolder(private val binding: ItemRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(evaluate: Evaluate) {
            binding.evaluate = evaluate
            binding.executePendingBindings()
        }
    }

    companion object MyDiffUtil : DiffUtil.ItemCallback<Evaluate>() {
        override fun areItemsTheSame(oldItem: Evaluate, newItem: Evaluate): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Evaluate, newItem: Evaluate): Boolean {
            return oldItem.id == newItem.id
        }
    }
}