package com.dmitrysergeev.weatherapp.presentation.mainscreen.quickmenu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dmitrysergeev.weatherapp.databinding.QuickMenuItemBinding
import com.dmitrysergeev.weatherapp.presentation.mainscreen.recyclerview.MainScreenMenuItem

class QuickMenuItemViewHolder(private val binding: QuickMenuItemBinding): RecyclerView.ViewHolder(binding.root){

    fun bind(quickMenuItem: MainScreenMenuItem){
        binding.title.text = quickMenuItem.title
        binding.icon.setImageResource(quickMenuItem.iconId)
        binding.cardView.setOnClickListener {
            quickMenuItem.doOnClick()
        }
    }

}

class QuickMenuListAdapter(
    private val quickMenuList: List<MainScreenMenuItem>
): RecyclerView.Adapter<QuickMenuItemViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuickMenuItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = QuickMenuItemBinding.inflate(layoutInflater, parent, false)
        return QuickMenuItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuickMenuItemViewHolder, position: Int) {
        val quickMenuItem = quickMenuList[position]
        holder.bind(quickMenuItem)
    }

    override fun getItemCount(): Int = quickMenuList.size

}