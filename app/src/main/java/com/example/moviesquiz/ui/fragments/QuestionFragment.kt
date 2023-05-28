package com.example.moviesquiz.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import com.example.moviesquiz.R
import com.example.moviesquiz.app
import com.example.moviesquiz.databinding.FragmentQuestionBinding
import com.example.moviesquiz.domain.entities.Answer
import com.example.moviesquiz.ui.QuestionState
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
    }

    private fun setQuestion(questionState: QuestionState?) = with(binding) {
        questionState?.let {
            when(questionState){
                is QuestionState.Error -> {
                    parentFragmentManager.popBackStack()
                }
                is QuestionState.Loading -> {
                    fourAnswersContainer.visibility = View.GONE
                    textFieldContainer.visibility = View.GONE
                }
                is QuestionState.SuccessEasyLevel -> {
                    fourAnswersContainer.visibility = View.VISIBLE
                    textFieldContainer.visibility = View.GONE
                    if (!answerBtn1.isEnabled) {
                        setButtonsDisabled()
                    }
                    answerBtn1.setBackgroundColor(
                        resources.getColor(
                            R.color.purple_500,
                            requireContext().theme
                        )
                    )
                    answerBtn2.setBackgroundColor(
                        resources.getColor(
                            R.color.purple_500,
                            requireContext().theme
                        )
                    )
                    answerBtn3.setBackgroundColor(
                        resources.getColor(
                            R.color.purple_500,
                            requireContext().theme
                        )
                    )
                    answerBtn4.setBackgroundColor(
                        resources.getColor(
                            R.color.purple_500,
                            requireContext().theme
                        )
                    )
                    tempQId.text = questionState.question.id
                    screenshotContainer.setImageDrawable(
                        AppCompatResources.getDrawable(
                            requireContext(),
                            questionState.question.screenshot
                        )
                    )
                    setAnswers(questionState.answers)
                }
                is QuestionState.SuccessNormalLevel -> {
                    fourAnswersContainer.visibility = View.GONE
                    textFieldContainer.visibility = View.VISIBLE
                    tempQId.text = questionState.question.id
                    screenshotContainer.setImageDrawable(
                        AppCompatResources.getDrawable(
                            requireContext(),
                            questionState.question.screenshot
                        )
                    )
                }
                is QuestionState.SuccessHardLevel -> {
                    tempQId.text = questionState.question.id
                    screenshotContainer.setImageDrawable(
                        AppCompatResources.getDrawable(
                            requireContext(),
                            questionState.question.screenshot
                        )
                    )
                }
            }
        }
    }

    private fun setAnswers(answers: ArrayList<Answer>?) = with(binding) {
        answers?.let {
            answerBtn1.apply {
                text = answers[0].text
                setOnClickListener {
                    onAnswerClicked(answers[0], this)
                    setButtonsDisabled()
                }
            }
            answerBtn2.apply {
                text = answers[1].text
                setOnClickListener {
                    onAnswerClicked(answers[1], this)
                    setButtonsDisabled()
                }
            }
            answerBtn3.apply {
                text = answers[2].text
                setOnClickListener {
                    onAnswerClicked(answers[2], this)
                    setButtonsDisabled()
                }
            }
            answerBtn4.apply {
                text = answers[3].text
                setOnClickListener {
                    onAnswerClicked(answers[3], this)
                    setButtonsDisabled()
                }
            }
        }
    }

    private fun setButtonsDisabled() = with(binding) {
        if (answerBtn1.isEnabled){
            answerBtn1.isEnabled = false
            answerBtn2.isEnabled = false
            answerBtn3.isEnabled = false
            answerBtn4.isEnabled = false
        } else {
            answerBtn1.isEnabled = true
            answerBtn2.isEnabled = true
            answerBtn3.isEnabled = true
            answerBtn4.isEnabled = true
        }
    }

    private fun onAnswerClicked(answer: Answer, btn: MaterialButton) = with(binding) {
        if (answer.isRight){
            btn.setBackgroundColor(resources.getColor(R.color.green, requireContext().theme))
            viewModel.setAnswerAsRight()
        } else {
            btn.setBackgroundColor(resources.getColor(R.color.red, requireContext().theme))
        }
        viewModel.setNextQuestion()
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