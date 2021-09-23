package com.robby.pawoontest.modules.todo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.robby.pawoontest.databinding.FragmentTodoBinding
import com.robby.pawoontest.domain.TodoDatabase
import com.robby.pawoontest.domain.model.TodoModel
import com.robby.pawoontest.modules.todo.adapter.TodoAdapter

class TodoFragment : Fragment(), TodoContract.View, TodoAdapter.Callback {
    lateinit var binding : FragmentTodoBinding
    lateinit var presenter: TodoPresenter
    lateinit var todoDatabase: TodoDatabase

    lateinit var todoAdapter : TodoAdapter
    var todoList: MutableList<TodoModel> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTodoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        todoDatabase = TodoDatabase.getInstance(requireContext())
        presenter = TodoPresenter(this, todoDatabase.todoDao())
        presenter.getTodoListOffline()

        todoAdapter = TodoAdapter(this)
        val layoutManagerVertical: RecyclerView.LayoutManager = LinearLayoutManager(context)
        binding.rvTodo.layoutManager = layoutManagerVertical
        binding.rvTodo.adapter = todoAdapter
    }

    override fun onTodoSuccess(todoModel: List<TodoModel>) {
        presenter.insertTodoAll(todoModel)

        todoList = todoModel.toMutableList()
        todoAdapter.setData(todoList)
        todoAdapter.notifyDataSetChanged()
    }

    override fun onTodoOffline(todoModel: List<TodoModel>) {
        if (todoModel.isEmpty()) {
            presenter.getTodoList()
        } else {
            todoList = todoModel.toMutableList()
            requireActivity().runOnUiThread({
                todoAdapter.setData(todoModel)
                todoAdapter.notifyDataSetChanged()
            })
        }
    }

    override fun onTodoFailed(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        binding.pbLoading.visibility = View.VISIBLE
    }

    override fun dismissLoading() {
        requireActivity().runOnUiThread({
            binding.pbLoading.visibility = View.INVISIBLE
        })
    }

    override fun onItemClick(data: TodoModel, position: Int, status: Boolean) {
        var dataTemp = data.apply {
            completed = status
        }
        todoList.set(position, dataTemp)
        presenter.updateTodo(data)
    }
}