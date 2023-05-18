package com.example.moviesquiz.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.moviesquiz.databinding.FragmentLevelsRoomRvItemBinding

class LevelsRoomAdapter(private val levelsRoomInterface: LevelsRoomInterface): Adapter<LevelsRoomAdapter.LevelsRoomViewHolder>() {
    private var levelsList = arrayListOf(1, 2, 3)
    private lateinit var inListBinding: FragmentLevelsRoomRvItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LevelsRoomViewHolder {
        inListBinding = FragmentLevelsRoomRvItemBinding.inflate(LayoutInflater.from(parent.context),
        parent, false)
        return LevelsRoomViewHolder(inListBinding.root)
    }

    override fun onBindViewHolder(holder: LevelsRoomViewHolder, position: Int) {
        holder.bind(levelsList[position])
    }

    override fun getItemCount(): Int = levelsList.size

    inner class LevelsRoomViewHolder(itemView: View): ViewHolder(itemView) {
        fun bind(level: Int) = with(inListBinding){
            levelName.text = level.toString()
            itemView.setOnClickListener { levelsRoomInterface.onLevelClicked(level) }
        }
    }
}

interface LevelsRoomInterface{
    fun onLevelClicked(lvl: Int)
}