package com.example.tictactoe

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.tictactoe.databinding.ActivityGameBotBinding
import java.util.*

class GameBotActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityGameBotBinding
    var activePlayer = 1
    var player = 0
    var countCheckedCells = 0
    private val playerX = mutableListOf<Int>()
    private val playerO = mutableListOf<Int>()

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
        //checkWinner()
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
        //checkWinner()
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
}