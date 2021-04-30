package com.example.tictactoe

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tictactoe.api.data.GameState
import com.example.tictactoe.databinding.GameLayoutBinding

class GameActivity : AppCompatActivity() {

    private lateinit var binding: GameLayoutBinding
    private lateinit var gameHandler: Handler

    private val poll = object : Runnable {
        override fun run() {
            pollApi()
            gameHandler.postDelayed(this, 5000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = GameLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        gameHandler = Handler(Looper.getMainLooper())
        gameHandler.post { pollApi() }

        binding.gameIdValue.text = GameManager.gameId

        binding.playerOneValue.text = GameManager.player

        if (GameManager.playerTwo != "") {
            binding.playerTwoValue.text = GameManager.playerTwo
        }

        clickListeners()
    }

    override fun onPause() {
        super.onPause()
        gameHandler.removeCallbacks(poll)
    }

    override fun onResume() {
        super.onResume()
        gameHandler.post(poll)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun pollApi() {
        if (GameManager.gameId != "") {
            GameManager.pollGame()
        }
    }

    private fun clickListeners() {
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