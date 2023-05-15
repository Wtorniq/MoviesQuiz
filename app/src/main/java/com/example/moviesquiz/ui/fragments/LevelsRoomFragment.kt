package com.example.moviesquiz.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.moviesquiz.R
import com.example.moviesquiz.app
import com.example.moviesquiz.databinding.FragmentLevelsRoomBinding
import com.example.moviesquiz.ui.adapters.LevelsRoomAdapter
import com.example.moviesquiz.ui.adapters.LevelsRoomInterface

class LevelsRoomFragment : Fragment() {

    private var _binding: FragmentLevelsRoomBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy { app.viewModel }
    private val adapter: LevelsRoomAdapter by lazy {
        LevelsRoomAdapter(object : LevelsRoomInterface {
            override fun onLevelClicked(lvl: Int) {
                viewModel.setChosenLevel(lvl)
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, CategoriesFragment.newInstance())
                    .addToBackStack(null)
                    .commit()
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLevelsRoomBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val lm = LinearLayoutManager(requireContext()).apply {
            orientation = LinearLayoutManager.HORIZONTAL
        }
        binding.recyclerView.layoutManager = lm
        PagerSnapHelper().attachToRecyclerView(binding.recyclerView)
        binding.recyclerView.adapter = adapter

        viewModel.getLevelsLiveData().observe(viewLifecycleOwner) { setLevels(it) }
        viewModel.getLevels()
    }

    private fun setLevels(levels: ArrayList<String>?) {
        levels?.let {
            adapter.setLevelsList(levels)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        @JvmStatic
        fun newInstance() = LevelsRoomFragment()
    }
}