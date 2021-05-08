package com.example.tictactoe

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.tictactoe.App.Companion.context
import com.example.tictactoe.databinding.ActivityGameBotBinding
import java.util.*

class GameBotActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityGameBotBinding
    private var activePlayer = 1
    private var player = 0
    private var countCheckedCells = 0
    private var playerX = mutableListOf<Int>()
    private var playerO = mutableListOf<Int>()
    private var winner = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBotBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.title = "Main menu"

        binding.buttonStartNewGame.isVisible = false

        binding.buttonX.setOnClickListener {
            player = 1
        }

        binding.buttonO.setOnClickListener {
            player = 2
            bot()
        }

        binding.textView0.setOnClickListener(this)
        binding.textView1.setOnClickListener(this)
        binding.textView2.setOnClickListener(this)
        binding.textView3.setOnClickListener(this)
        binding.textView4.setOnClickListener(this)
        binding.textView5.setOnClickListener(this)
        binding.textView6.setOnClickListener(this)
        binding.textView7.setOnClickListener(this)
        binding.textView8.setOnClickListener(this)

        startNewGame()
    }

    override fun onClick(view: View) {
        var cellIndex = 10
        val textView = view as TextView

        when (textView.id) {
            R.id.textView0 -> cellIndex = 0
            R.id.textView1 -> cellIndex = 1
            R.id.textView2 -> cellIndex = 2
            R.id.textView3 -> cellIndex = 3
            R.id.textView4 -> cellIndex = 4
            R.id.textView5 -> cellIndex = 5
            R.id.textView6 -> cellIndex = 6
            R.id.textView7 -> cellIndex = 7
            R.id.textView8 -> cellIndex = 8
        }

        if (player == 1) {
            playGameX(cellIndex, textView)
        }else if (player == 2) {
            playGameO(cellIndex, textView)
        }
    }

    private fun playGameX(i: Int, textView: TextView) {
        if (activePlayer == 1) {
            textView.text = "X"
            playerX.add(i)
            activePlayer = 2
            countCheckedCells += 1

            if (countCheckedCells < 8) {
                bot()
            }
        } else {
            textView.text = "O"
            playerO.add(i)
            activePlayer = 1
        }
        textView.isClickable = false
        checkWinner()
    }

    private fun playGameO(i: Int, textView: TextView) {
        if (activePlayer == 1) {
            textView.text = "X"
            playerX.add(i)
            activePlayer = 2

        } else {
            textView.text = "O"
            playerO.add(i)
            activePlayer = 1

            bot()

        }
        textView.isClickable = false
        checkWinner()
    }

    private fun bot() {
        val availableCells = mutableListOf<Int>()
        countCheckedCells += 1

        for (cell  in 0..8) {
            if (!(playerX.contains(cell ) || playerO.contains(cell ))) {
                availableCells.add(cell)
            }
        }

        val randomCell = Random()
        val randomIndex = randomCell.nextInt(availableCells.size)
        val cellIndex = availableCells[randomIndex]

        val textView: TextView = when(cellIndex) {
            0 -> binding.textView0
            1 -> binding.textView1
            2 -> binding.textView2
            3 -> binding.textView3
            4 -> binding.textView4
            5 -> binding.textView5
            6 -> binding.textView6
            7 -> binding.textView7
            8 -> binding.textView8
            else -> {
                binding.textView0
            }
        }

        if (player == 1) {
            playGameX(cellIndex, textView)
        } else if (player == 2) {
            playGameO(cellIndex, textView)
        }
    }

    private fun startNewGame() {
        binding.buttonStartNewGame.setOnClickListener {
            activePlayer = 1
            player = 0
            countCheckedCells = 0
            playerX = mutableListOf()
            playerO = mutableListOf()

            binding.buttonStartNewGame.isVisible = false
            binding.textViewInfo.text = ""
            binding.textView0.text = ""
            binding.textView1.text = ""
            binding.textView2.text = ""
            binding.textView3.text = ""
            binding.textView4.text = ""
            binding.textView5.text = ""
            binding.textView6.text = ""
            binding.textView7.text = ""
            binding.textView8.text = ""

            activateClickable()
        }
    }

    private fun activateClickable() {
        binding.textView0.isClickable = true
        binding.textView1.isClickable = true
        binding.textView2.isClickable = true
        binding.textView3.isClickable = true
        binding.textView4.isClickable = true
        binding.textView5.isClickable = true
        binding.textView6.isClickable = true
        binding.textView7.isClickable = true
        binding.textView8.isClickable = true
    }

    private fun checkWinner() {
        when {
            //TODO sett opp draw
            winConditions() && winner == 1 && player == 1 -> {
                binding.textViewInfo.text = context.getString(R.string.you_won)
                binding.buttonStartNewGame.isVisible = true
                activateClickable()
            }
            winConditions() && winner == 2 && player == 1 -> {
                binding.textViewInfo.text = context.getString(R.string.you_lose)
                binding.buttonStartNewGame.isVisible = true
                activateClickable()
            }
            winConditions() && winner == 1 && player == 2 -> {
                binding.textViewInfo.text = context.getString(R.string.you_lose)
                binding.buttonStartNewGame.isVisible = true
                activateClickable()
            }
            winConditions() && winner == 2 && player == 2 -> {
                binding.textViewInfo.text = context.getString(R.string.you_won)
                binding.buttonStartNewGame.isVisible = true
                activateClickable()
            }
        }
    }

    private fun winConditions(): Boolean {
        when {
            playerX.contains(0) && playerX.contains(1) && playerX.contains(2) -> {
                winner = 1
                return true
            }
            playerO.contains(0) && playerO.contains(1) && playerO.contains(2) -> {
                winner = 2
                return true
            }
            playerX.contains(3) && playerX.contains(4) && playerX.contains(5) -> {
                winner = 1
                return true
            }
            playerO.contains(3) && playerO.contains(4) && playerO.contains(5) -> {
                winner = 2
                return true
            }
            playerX.contains(6) && playerX.contains(7) && playerX.contains(8) -> {
                winner = 1
                return true
            }
            playerO.contains(6) && playerO.contains(7) && playerO.contains(8) -> {
                winner = 2
                return true
            }
            playerX.contains(0) && playerX.contains(3) && playerX.contains(6) -> {
                winner = 1
                return true
            }
            playerO.contains(0) && playerO.contains(3) && playerO.contains(6) -> {
                winner = 2
                return true
            }
            playerX.contains(1) && playerX.contains(4) && playerX.contains(7) -> {
                winner = 1
                return true
            }
            playerO.contains(1) && playerO.contains(4) && playerO.contains(7) -> {
                winner = 2
                return true
            }
            playerX.contains(2) && playerX.contains(5) && playerX.contains(8) -> {
                winner = 1
                return true
            }
            playerO.contains(2) && playerO.contains(5) && playerO.contains(8) -> {
                winner = 2
                return true
            }
            playerX.contains(0) && playerX.contains(4) && playerX.contains(8) -> {
                winner = 1
                return true
            }
            playerO.contains(0) && playerO.contains(4) && playerO.contains(8) -> {
                winner = 2
                return true
            }
            playerX.contains(2) && playerX.contains(4) && playerX.contains(6) -> {
                winner = 1
                return true
            }
            playerO.contains(2) && playerO.contains(4) && playerO.contains(6) -> {
                winner = 2
                return true
            }

            else -> { return false }
        }
    }
}