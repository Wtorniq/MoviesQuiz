package com.example.moviesquiz.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import com.example.moviesquiz.R
import com.example.moviesquiz.app
import com.example.moviesquiz.databinding.FragmentQuestionBinding
import com.example.moviesquiz.domain.entities.Answer
import com.example.moviesquiz.domain.entities.Question
import com.google.android.material.button.MaterialButton

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
        viewModel.getAnswersLiveData().observe(viewLifecycleOwner) {setAnswers(it)}
        viewModel.getAnswers()
    }

    private fun setAnswers(answers: ArrayList<Answer>?) = with(binding) {
        answers?.let {
            answerBtn1.apply {
                text = answers[0].text
                setOnClickListener {
                    onAnswerClicked(answers[0], this)
                }
            }
            answerBtn2.apply {
                text = answers[1].text
                setOnClickListener {
                    onAnswerClicked(answers[1], this)
                }
            }
            answerBtn3.apply {
                setOnClickListener {
                    onAnswerClicked(answers[2], this)
                }
            }
            answerBtn4.apply {
                text = answers[3].text
                setOnClickListener {
                    onAnswerClicked(answers[3], this)
                }
            }
        }
    }

    private fun onAnswerClicked(answer: Answer, btn: MaterialButton) = with(binding) {
        if (answer.isRight){
            btn.setBackgroundColor(resources.getColor(R.color.green, requireContext().theme))
            Toast.makeText(requireContext(), "Right!", Toast.LENGTH_SHORT).show()
        } else {
            btn.setBackgroundColor(resources.getColor(R.color.red, requireContext().theme))
            Toast.makeText(requireContext(), "Wrong!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setQuestion(question: Question?) = with(binding) {
        question?.let {
            screenshotContainer.setImageDrawable(AppCompatResources.getDrawable(requireContext(), question.screenshot))
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