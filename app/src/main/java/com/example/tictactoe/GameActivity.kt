package com.example.tictactoe

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tictactoe.api.data.Game
import com.example.tictactoe.api.data.GameState
import com.example.tictactoe.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        binding.playerOneValue.text = GameManager.player

        //TODO set textview clicklisteners
        //clickListeners()
        binding.buttonUpdate.setOnClickListener {
            GameManager.updateGame()
        }

        binding.buttonPoll.setOnClickListener {
            GameManager.pollGame()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        finish()
        return super.onSupportNavigateUp()
    }

    private fun setValues() {

    }

    private fun clickListeners() {
        binding.textView00.setOnClickListener {
            binding.textView00.text = "X"

        }
        binding.textView01.setOnClickListener {

        }
        binding.textView02.setOnClickListener {

        }
        binding.textView03.setOnClickListener {

        }
        binding.textView04.setOnClickListener {

        }
        binding.textView05.setOnClickListener {

        }
        binding.textView06.setOnClickListener {

        }
        binding.textView07.setOnClickListener {

        }
        binding.textView08.setOnClickListener {

        }
    }
}