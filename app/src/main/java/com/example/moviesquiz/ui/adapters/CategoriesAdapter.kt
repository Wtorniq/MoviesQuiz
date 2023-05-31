package com.example.moviesquiz.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.moviesquiz.databinding.FragmentCategoriesRvItemBinding
import com.example.moviesquiz.domain.entities.Category

class CategoriesAdapter(private val categoriesInterface: CategoriesInterface) : Adapter<CategoriesAdapter.CategoriesViewHolder>(){

    private val categoriesList = arrayListOf<Category>()
    private val namesList = arrayListOf<String>()

    @SuppressLint("NotifyDataSetChanged")
    fun setCategoriesList(categories: ArrayList<Category>, names: ArrayList<String>){
        categoriesList.apply {
            clear()
            addAll(categories)
        }
        namesList.apply {
            clear()
            addAll(names)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val binding = FragmentCategoriesRvItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return CategoriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.bind(categoriesList[position], namesList[position])
    }

    override fun getItemCount(): Int = categoriesList.size

    inner class CategoriesViewHolder(private val binding: FragmentCategoriesRvItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(category: Category, name: String) = with(binding){
            categoryName.text = name
            if (category.isEnabled){
                blocker.visibility = View.GONE
                counter.text = category.answersCounter.toString()
                itemView.setOnClickListener { categoriesInterface.onCategoryClicked(category) }
            } else {
                blocker.visibility = View.VISIBLE
            }
        }
    }
}

interface CategoriesInterface{
    fun onCategoryClicked(category: Category)
}