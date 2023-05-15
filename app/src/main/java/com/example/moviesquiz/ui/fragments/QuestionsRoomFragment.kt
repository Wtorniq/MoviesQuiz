package com.example.moviesquiz.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.moviesquiz.R
import com.example.moviesquiz.app
import com.example.moviesquiz.databinding.FragmentQuestionsRoomBinding
import com.example.moviesquiz.database.entities.Question
import com.example.moviesquiz.ui.adapters.QuestionsRoomAdapter
import com.example.moviesquiz.ui.adapters.QuestionsRoomInterface

class QuestionsRoomFragment : Fragment() {
    private var _binding: FragmentQuestionsRoomBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy { app.viewModel }
    private val adapter: QuestionsRoomAdapter by lazy {
        QuestionsRoomAdapter(object : QuestionsRoomInterface {
            override fun onQuestionClicked() {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, QuestionFragment.newInstance())
                    .addToBackStack(null)
                    .commit()
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuestionsRoomBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val lm = GridLayoutManager(requireContext(), 4, GridLayoutManager.VERTICAL, false)
        binding.recyclerView.layoutManager = lm
        binding.recyclerView.adapter = adapter

        viewModel.getQuestionsLiveData().observe(viewLifecycleOwner) { setQuestions(it) }
        viewModel.getQuestions("")
    }

    private fun setQuestions(levels: ArrayList<Question>?) {
        levels?.let {
            adapter.setQuestionsList(levels)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        @JvmStatic
        fun newInstance() = QuestionsRoomFragment()
    }
}