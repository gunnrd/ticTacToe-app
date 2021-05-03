package com.example.tictactoe

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tictactoe.api.data.Game
import com.example.tictactoe.api.data.GameState
import com.example.tictactoe.databinding.GameLayoutBinding

class GameAdapter(private var game: GameState) : RecyclerView.Adapter<GameAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(GameLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    class ViewHolder(private val binding: GameLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.gameIdValue.text = GameManager.gameId
            binding.playerOneValue.text = GameManager.player

            if (GameManager.playerTwo != "") {
                binding.playerTwoValue.text = GameManager.playerTwo
            }
        }
    }

    override fun getItemCount(): Int = game.size
}