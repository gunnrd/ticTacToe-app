package com.example.tictactoe

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tictactoe.databinding.GameLayoutBinding

class GameAdapter(private val clickListener: Unit) : RecyclerView.Adapter<GameAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(GameLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: GameAdapter.ViewHolder, position: Int) {

    }

    inner class ViewHolder(private val binding: GameLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

}