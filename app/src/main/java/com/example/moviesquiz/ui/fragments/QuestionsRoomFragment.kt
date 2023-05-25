package com.example.moviesquiz.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviesquiz.R
import com.example.moviesquiz.app
import com.example.moviesquiz.databinding.FragmentQuestionsRoomBinding
import com.example.moviesquiz.domain.entities.Question
import com.example.moviesquiz.ui.MainActivity
import com.example.moviesquiz.ui.adapters.QuestionsRoomAdapter
import com.example.moviesquiz.ui.adapters.QuestionsRoomInterface

class QuestionsRoomFragment : Fragment() {
    private var _binding: FragmentQuestionsRoomBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy { app.viewModel }
    private val adapter: QuestionsRoomAdapter by lazy {
        QuestionsRoomAdapter(object : QuestionsRoomInterface {
            override fun onQuestionClicked(question: Question) {
                viewModel.setCurrentQuestion(question)
                parentFragmentManager.beginTransaction()
                    .replace((requireActivity() as MainActivity).binding.container.id, QuestionFragment.newInstance())
                    .addToBackStack(null)
                    .commit()
            }

            override fun setColor(question: Question, itemView: View) {
                if (!question.isAnswered){
                    itemView.setBackgroundColor(resources.getColor(R.color.green, requireContext().theme))
                } else {
                    itemView.setBackgroundColor(resources.getColor(R.color.gray, requireContext().theme))
                }
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
        viewModel.getQuestions()
    }

    private fun setQuestions(questions: ArrayList<Question>?) {
        questions?.let {
            adapter.setQuestionsList(questions)
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