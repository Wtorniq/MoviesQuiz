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

    @SuppressLint("NotifyDataSetChanged")
    fun setCategoriesList(categories: ArrayList<Category>){
        categoriesList.apply {
            clear()
            addAll(categories)
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
        holder.bind(categoriesList[position])
    }

    override fun getItemCount(): Int = categoriesList.size

    inner class CategoriesViewHolder(private val binding: FragmentCategoriesRvItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(category: Category) = with(binding){
            categoryName.text = category.name
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