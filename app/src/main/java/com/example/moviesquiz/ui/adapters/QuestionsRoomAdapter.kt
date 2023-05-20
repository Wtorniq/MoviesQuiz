package com.example.moviesquiz.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesquiz.databinding.FragmentQuestionsRoomRvItemBinding
import com.example.moviesquiz.domain.entities.Question

class QuestionsRoomAdapter(private val questionsRoomInterface: QuestionsRoomInterface) : RecyclerView.Adapter<QuestionsRoomAdapter.QuestionsRoomViewHolder>(){

    private lateinit var binding: FragmentQuestionsRoomRvItemBinding
    private val questionsList = ArrayList<Question>()

    @SuppressLint("NotifyDataSetChanged")
    fun setQuestionsList(questions: ArrayList<Question>){
        questionsList.clear()
        questionsList.addAll(questions)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionsRoomViewHolder {
        binding = FragmentQuestionsRoomRvItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return QuestionsRoomViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: QuestionsRoomViewHolder, position: Int) {
        holder.bind(questionsList[position])
    }

    override fun getItemCount(): Int = questionsList.size

    inner class QuestionsRoomViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(question: Question){
            binding.questionName.text = question.id.toString()
            itemView.setOnClickListener { questionsRoomInterface.onQuestionClicked(question) }
        }
    }
}
interface QuestionsRoomInterface{
    fun onQuestionClicked(question: Question)
}