package com.example.moviesquiz.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesquiz.databinding.FragmentQuestionsRoomRvItemBinding
import com.example.moviesquiz.domain.entities.Question

class QuestionsRoomAdapter(private val questionsRoomInterface: QuestionsRoomInterface) : RecyclerView.Adapter<QuestionsRoomAdapter.QuestionsRoomViewHolder>(){

    private val questionsList = ArrayList<Question>()

    @SuppressLint("NotifyDataSetChanged")
    fun setQuestionsList(questions: ArrayList<Question>){
        questionsList.clear()
        questionsList.addAll(questions)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionsRoomViewHolder {
        val binding = FragmentQuestionsRoomRvItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return QuestionsRoomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuestionsRoomViewHolder, position: Int) {
        holder.bind(questionsList[position], position)
    }

    override fun getItemCount(): Int = questionsList.size

    inner class QuestionsRoomViewHolder(private val binding: FragmentQuestionsRoomRvItemBinding): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(question: Question, position: Int){
            binding.questionName.text = "${position + 1}"
            itemView.setOnClickListener { questionsRoomInterface.onQuestionClicked(question) }
        }
    }
}
interface QuestionsRoomInterface{
    fun onQuestionClicked(question: Question)
}