package com.example.moviesquiz.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesquiz.R
import com.example.moviesquiz.app
import com.example.moviesquiz.databinding.FragmentCategoriesBinding
import com.example.moviesquiz.ui.adapters.CategoriesAdapter
import com.example.moviesquiz.ui.adapters.CategoriesInterface

class CategoriesFragment : Fragment() {

    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy { app.viewModel }
    private val adapter: CategoriesAdapter by lazy { CategoriesAdapter(object : CategoriesInterface{
        override fun onCategoryClicked(category: String) {
            viewModel.setChosenCategory(category)
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, QuestionsRoomFragment.newInstance())
                .addToBackStack(null)
                .commit()
        }
    }) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val lm = LinearLayoutManager(requireContext()).apply {
            orientation = LinearLayoutManager.VERTICAL
        }
        binding.recyclerView.layoutManager = lm
        binding.recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        @JvmStatic
        fun newInstance() = CategoriesFragment()
    }
}