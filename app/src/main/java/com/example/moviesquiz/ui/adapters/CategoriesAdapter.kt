package com.example.moviesquiz.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.moviesquiz.databinding.ActivityMainBinding
import com.example.moviesquiz.databinding.FragmentCategoriesRvItemBinding

class CategoriesAdapter(private val categoriesInterface: CategoriesInterface) : Adapter<CategoriesAdapter.CategoriesViewHolder>(){

    private lateinit var binding: FragmentCategoriesRvItemBinding
    private val categoriesList = ArrayList<String>()

    @SuppressLint("NotifyDataSetChanged")
    fun setCategoriesList(categories: ArrayList<String>){
        categoriesList.clear()
        categoriesList.addAll(categories)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        binding = FragmentCategoriesRvItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return CategoriesViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.bind(categoriesList[position])
    }

    override fun getItemCount(): Int = categoriesList.size

    inner class CategoriesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(category: String){
            binding.categoryName.text = category
            itemView.setOnClickListener { categoriesInterface.onCategoryClicked() }
        }
    }
}
interface CategoriesInterface{
    fun onCategoryClicked()
}