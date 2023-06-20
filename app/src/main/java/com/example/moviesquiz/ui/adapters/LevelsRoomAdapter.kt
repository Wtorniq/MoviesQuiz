package com.example.moviesquiz.ui.adapters

import android.annotation.SuppressLint
import android.view.*
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.moviesquiz.databinding.FragmentLevelsRoomRvItemBinding
import com.example.moviesquiz.domain.entities.Level
import com.google.android.material.snackbar.Snackbar

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
        holder.bindCardView(levelsList[position])
    }

    override fun getItemCount(): Int = levelsList.size

    @SuppressLint("ClickableViewAccessibility")
    inner class LevelsRoomViewHolder(private val inListBinding: FragmentLevelsRoomRvItemBinding): ViewHolder(inListBinding.root) {

        private var isScaledDown = false

        init {
            itemView.setOnClickListener {
                if (isScaledDown) {
                    it.animate().scaleX(1f).scaleY(1f).setDuration(200).start()
                }
                isScaledDown = false
            }

            itemView.setOnTouchListener { view, motionEvent ->
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        view.animate().scaleX(0.95f).scaleY(0.95f).setDuration(200).start()
                        isScaledDown = true
                    }
                }
                false
            }
        }

        fun bindCardView(level: Level) = with(inListBinding){
            lvlName.text = level.name
            lvlImgContainer.setImageDrawable(
                ResourcesCompat.getDrawable(
                    itemView.resources, level.image, itemView.resources.newTheme()))
            if (level.isEnabled){
                blocker.visibility = View.GONE
//                counter.text = level.answersCounter.toString()
                description.text = level.description
                enterBtn.setOnClickListener { levelsRoomInterface.onLevelClicked(level) }
            } else {
                blocker.visibility = View.VISIBLE
                enterBtn.visibility = View.INVISIBLE

                lockImg.setOnClickListener {
                    Snackbar.make(itemView, "Not opened now", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }
}

interface LevelsRoomInterface{
    fun onLevelClicked(lvl: Level)
}