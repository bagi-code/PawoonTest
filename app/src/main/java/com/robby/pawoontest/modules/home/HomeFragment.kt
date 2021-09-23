package com.robby.pawoontest.modules.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.robby.pawoontest.R
import com.robby.pawoontest.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    lateinit var binding:FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnTodo.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_todoFragment)
        }
    }
}