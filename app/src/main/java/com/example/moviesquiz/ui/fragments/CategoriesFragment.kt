package com.example.moviesquiz.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesquiz.app
import com.example.moviesquiz.databinding.FragmentCategoriesBinding
import com.example.moviesquiz.domain.entities.Category
import com.example.moviesquiz.ui.MainActivity
import com.example.moviesquiz.ui.adapters.CategoriesAdapter
import com.example.moviesquiz.ui.adapters.CategoriesInterface

class CategoriesFragment : Fragment() {

    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy { app.viewModel }
    private val adapter: CategoriesAdapter by lazy { CategoriesAdapter(object : CategoriesInterface{
        override fun onCategoryClicked(category: Category) {
            viewModel.setChosenCategory(category)
            parentFragmentManager.beginTransaction()
                .replace((requireActivity() as MainActivity).binding.container.id, QuestionsRoomFragment.newInstance())
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
        viewModel.getCategoriesLiveData().observe(viewLifecycleOwner) { setCategories(it) }
        viewModel.getCategories()
    }

    private fun setCategories(categories: ArrayList<Category>?) {
        categories?.let {
            val namesList = arrayListOf<String>()
            categories.forEach { category ->
                val name = requireContext().resources.getString(category.name)
                namesList.add(name)
            }
            adapter.setCategoriesList(categories, namesList)
        }
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