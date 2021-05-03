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

            binding.textView1.setOnClickListener {
                if (GameManager.activePlayer) {
                    binding.textView1.text = "X"
                } else {
                    binding.textView1.text = "O"
                }

                binding.textView1.isClickable = false
                GameManager.updateGame()
            }
            binding.textView1.setOnClickListener {
                if (GameManager.activePlayer) {
                    binding.textView1.text = "X"
                } else {
                    binding.textView1.text = "O"
                }
                binding.textView1.isClickable = false
                GameManager.updateGame()
            }

            binding.textView2.setOnClickListener {
                if (GameManager.activePlayer) {
                    binding.textView2.text = "X"
                } else {
                    binding.textView2.text = "O"
                }
                binding.textView2.isClickable = false
                GameManager.updateGame()
            }

            binding.textView3.setOnClickListener {
                if (GameManager.activePlayer) {
                    binding.textView3.text = "X"
                } else {
                    binding.textView3.text = "O"
                }
                binding.textView3.isClickable = false
                GameManager.updateGame()
            }

            binding.textView4.setOnClickListener {
                if (GameManager.activePlayer) {
                    binding.textView4.text = "X"
                } else {
                    binding.textView4.text = "O"
                }
                binding.textView4.isClickable = false
                GameManager.updateGame()
            }

            binding.textView5.setOnClickListener {
                if (GameManager.activePlayer) {
                    binding.textView5.text = "X"
                } else {
                    binding.textView5.text = "O"
                }
                binding.textView5.isClickable = false
                GameManager.updateGame()
            }

            binding.textView6.setOnClickListener {
                if (GameManager.activePlayer) {
                    binding.textView6.text = "X"
                } else {
                    binding.textView6.text = "O"
                }
                binding.textView6.isClickable = false
                GameManager.updateGame()
            }

            binding.textView7.setOnClickListener {
                if (GameManager.activePlayer) {
                    binding.textView7.text = "X"
                } else {
                    binding.textView7.text = "O"
                }
                binding.textView7.isClickable = false
                GameManager.updateGame()
            }

            binding.textView8.setOnClickListener {
                if (GameManager.activePlayer) {
                    binding.textView8.text = "X"
                } else {
                    binding.textView8.text = "O"
                }
                binding.textView8.isClickable = false
                GameManager.updateGame()
            }

            binding.textView9.setOnClickListener {
                if (GameManager.activePlayer) {
                    binding.textView9.text = "X"
                } else {
                    binding.textView9.text = "O"
                }
                binding.textView9.isClickable = false
                GameManager.updateGame()
            }
        }
    }

    override fun getItemCount(): Int = game.size

}