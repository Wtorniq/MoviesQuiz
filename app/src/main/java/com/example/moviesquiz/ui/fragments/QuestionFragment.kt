package com.example.moviesquiz.ui.fragments

import android.content.res.Resources
import android.content.res.Resources.Theme
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.ThemeUtils
import com.example.moviesquiz.R
import com.example.moviesquiz.app
import com.example.moviesquiz.databinding.FragmentQuestionBinding
import com.example.moviesquiz.databinding.FragmentQuestionsRoomBinding
import com.example.moviesquiz.domain.Question
import com.example.moviesquiz.ui.MainViewModel

class QuestionFragment : Fragment() {

    private var _binding: FragmentQuestionBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy { app.viewModel }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuestionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCurrentQuestionLiveData().observe(viewLifecycleOwner) { setQuestion(it) }
        viewModel.getCurrentQuestion()
    }

    private fun setQuestion(question: Question?) = with(binding) {
        question?.let {
            screenshotContainer.setImageDrawable(AppCompatResources.getDrawable(requireContext(), question.screenshot))
            answerBtn1.text = question.answer
            answerBtn2.text = question.wrongAnswers!![0]
            answerBtn3.text = question.wrongAnswers[1]
            answerBtn4.text = question.wrongAnswers[2]
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        @JvmStatic
        fun newInstance() = QuestionFragment()
    }

}