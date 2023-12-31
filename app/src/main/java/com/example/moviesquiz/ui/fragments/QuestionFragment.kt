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
import com.example.moviesquiz.domain.entities.Question
import com.example.moviesquiz.ui.states.QuestionState
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
                    setQuestionForEasyLevel(questionState.question, questionState.answers)
                }
                is QuestionState.SuccessNormalLevel -> {
                    fourAnswersContainer.visibility = View.GONE
                    textFieldContainer.visibility = View.VISIBLE
                    screenshotContainer.setImageDrawable(
                        AppCompatResources.getDrawable(
                            requireContext(),
                            questionState.question.screenshot
                        )
                    )
                    inputTextField.text = null
                    inputTextField.setBackgroundColor(resources.getColor(R.color.white, requireContext().theme))
                    btnTextField.setOnClickListener {
                        val text = inputTextField.text.toString()
                        if (text == questionState.answer.text){
                            viewModel.setAnswerAsRight()
                            inputTextField.setBackgroundColor(resources.getColor(R.color.green, requireContext().theme))
                        } else {
                            inputTextField.setBackgroundColor(resources.getColor(R.color.red, requireContext().theme))
                        }
                        viewModel.setNextQuestion()
                    }
                }
                is QuestionState.SuccessHardLevel -> {
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

    private fun setQuestionForEasyLevel(question: Question, answers: ArrayList<Answer>) = with(binding) {
        if (!answerBtn1.isEnabled) {
            switchButtons()
        }
        setButtonsDefaultColor(answerBtn1)
        setButtonsDefaultColor(answerBtn2)
        setButtonsDefaultColor(answerBtn3)
        setButtonsDefaultColor(answerBtn4)
        screenshotContainer.setImageDrawable(
            AppCompatResources.getDrawable(
                requireContext(),
                question.screenshot
            )
        )
        setAnswers(answers)
    }

    private fun setButtonsDefaultColor(button: MaterialButton) {
        button.setBackgroundColor(
            resources.getColor(
                R.color.purple_500,
                requireContext().theme
            )
        )
    }

    private fun setAnswers(answers: ArrayList<Answer>?) = with(binding) {
        answers?.let {
            answerBtn1.apply {
                text = answers[0].text
                setOnClickListener {
                    onAnswerClicked(answers[0], this)
                    switchButtons()
                }
            }
            answerBtn2.apply {
                text = answers[1].text
                setOnClickListener {
                    onAnswerClicked(answers[1], this)
                    switchButtons()
                }
            }
            answerBtn3.apply {
                text = answers[2].text
                setOnClickListener {
                    onAnswerClicked(answers[2], this)
                    switchButtons()
                }
            }
            answerBtn4.apply {
                text = answers[3].text
                setOnClickListener {
                    onAnswerClicked(answers[3], this)
                    switchButtons()
                }
            }
        }
    }

    private fun switchButtons() = with(binding) {
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