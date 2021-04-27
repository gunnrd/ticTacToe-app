package com.example.tictactoe

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.tictactoe.api.GameService
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

        //TODO set textview clicklisteners
        //clickListeners()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        finish()
        return super.onSupportNavigateUp()
    }

    private fun clickListeners() {
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
        binding.textView09.setOnClickListener {

        }
    }
}