package com.example.moviesquiz.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.moviesquiz.databinding.FragmentLevelsRoomRvItemBinding

class LevelsRoomAdapter(private val levelsRoomInterface: LevelsRoomInterface): Adapter<LevelsRoomAdapter.LevelsRoomViewHolder>() {
    private var levelsList = ArrayList<String>()
    private lateinit var inListBinding: FragmentLevelsRoomRvItemBinding

    @SuppressLint("NotifyDataSetChanged")
    fun setLevelsList(levels: ArrayList<String>){
        levelsList.clear()
        levelsList.addAll(levels)
        notifyDataSetChanged()
    }

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
        fun bind(level: String) = with(inListBinding){
            levelName.text = level
            itemView.setOnClickListener { levelsRoomInterface.onLevelClicked(level.toInt()) }
        }
    }
}

interface LevelsRoomInterface{
    fun onLevelClicked(lvl: Int)
}