package com.example.moviesquiz.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.moviesquiz.databinding.FragmentLevelsRoomRvItemBinding
import com.example.moviesquiz.domain.entities.Level

class LevelsRoomAdapter(private val levelsRoomInterface: LevelsRoomInterface): Adapter<LevelsRoomAdapter.LevelsRoomViewHolder>() {
    private val levelsList = arrayListOf<Level>()

    @SuppressLint("NotifyDataSetChanged")
    fun setLevelsList(levels: ArrayList<Level>){
        levelsList.apply {
            clear()
            addAll(levels)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LevelsRoomViewHolder {
        val inListBinding = FragmentLevelsRoomRvItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return LevelsRoomViewHolder(inListBinding)
    }

    override fun onBindViewHolder(holder: LevelsRoomViewHolder, position: Int) {
        holder.bind(levelsList[position])
    }

    override fun getItemCount(): Int = levelsList.size

    inner class LevelsRoomViewHolder(private val inListBinding: FragmentLevelsRoomRvItemBinding): ViewHolder(inListBinding.root) {
        fun bind(level: Level) = with(inListBinding){
            levelName.text = level.id
            counter.text = level.answersCounter.toString()
            itemView.setOnClickListener { levelsRoomInterface.onLevelClicked(level) }
        }
    }
}

interface LevelsRoomInterface{
    fun onLevelClicked(lvl: Level)
}