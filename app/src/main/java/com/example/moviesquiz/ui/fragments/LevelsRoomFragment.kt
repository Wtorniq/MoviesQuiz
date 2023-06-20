package com.example.moviesquiz.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.*
import com.example.moviesquiz.app
import com.example.moviesquiz.databinding.FragmentLevelsRoomBinding
import com.example.moviesquiz.domain.entities.Level
import com.example.moviesquiz.ui.MainActivity
import com.example.moviesquiz.ui.adapters.LevelsRoomAdapter
import com.example.moviesquiz.ui.adapters.LevelsRoomInterface

class LevelsRoomFragment : Fragment() {

    private var _binding: FragmentLevelsRoomBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy { app.viewModel }
    private val adapter: LevelsRoomAdapter by lazy {
        LevelsRoomAdapter(object : LevelsRoomInterface {

            override fun onLevelClicked(lvl: Level) {
                viewModel.setChosenLevel(lvl)
                parentFragmentManager.beginTransaction()
                    .replace((requireActivity() as MainActivity).binding.container.id, CategoriesFragment.newInstance())
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

        binding.deleteBtn.visibility = View.GONE
/*        binding.deleteBtn.setOnClickListener {
            viewModel.deleteData()
            activity?.getPreferences(AppCompatActivity.MODE_PRIVATE)
                ?.edit()
                ?.putBoolean("ARG_IS_DB_CREATED", false)
                ?.apply()
        }*/
    }

    private fun setLevels(levels: ArrayList<Level>?) {
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