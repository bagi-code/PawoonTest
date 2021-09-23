package com.robby.pawoontest.modules.todo.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.robby.pawoontest.databinding.ItemTodoBinding
import com.robby.pawoontest.domain.model.TodoModel

class TodoAdapter(val callback: Callback)
    : RecyclerView.Adapter<TodoAdapter.TodoHolder>() {

    private lateinit var binding: ItemTodoBinding
    private var dataList: List<TodoModel> = listOf()

    fun setData(dataList: List<TodoModel>) {
        this.dataList = dataList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoHolder {
        binding = ItemTodoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TodoHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.bind(dataList.get(position), position)
    }

    override fun getItemCount(): Int = dataList.size

    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class TodoHolder(itemView: ItemTodoBinding)
        : RecyclerView.ViewHolder(itemView.root) {
        fun bind(data : TodoModel, position: Int) {
            binding.tvTitle.setText(data.title)

            if (data.completed) {
                binding.ivChecklist.visibility = View.VISIBLE
            }

            itemView.setOnClickListener {
                if (data.completed) {
                    binding.ivChecklist.visibility = View.INVISIBLE
                    callback.onItemClick(data, position, false)
                    notifyItemChanged(position)
                } else {
                    binding.ivChecklist.visibility = View.VISIBLE
                    callback.onItemClick(data, position, true)
                    notifyItemChanged(position)
                }
            }
        }
    }

    interface Callback {
        fun onItemClick(data : TodoModel, position: Int, status: Boolean)
    }
}