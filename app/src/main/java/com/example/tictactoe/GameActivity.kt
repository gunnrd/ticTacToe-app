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

    private val win1: GameState = listOf(listOf(1,1,1), listOf(0,0,0), listOf(0,0,0))
    private val win2: GameState = listOf(listOf(1,1,1), listOf(1,1,1), listOf(0,0,0))
    private val win3: GameState = listOf(listOf(1,1,1), listOf(0,0,0), listOf(1,1,1))
    private val win4: GameState = listOf(listOf(1,0,0), listOf(1,0,0), listOf(1,0,0))
    private val win5: GameState = listOf(listOf(0,1,0), listOf(0,1,0), listOf(0,1,0))
    private val win6: GameState = listOf(listOf(0,0,1), listOf(0,0,1), listOf(0,0,1))
    private val win7: GameState = listOf(listOf(1,0,0), listOf(0,1,0), listOf(0,0,1))
    private val win8: GameState = listOf(listOf(0,0,1), listOf(0,1,0), listOf(1,0,0))

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
        finish()
        return super.onSupportNavigateUp()
    }

    private fun pollApi() {
        if (GameManager.gameId != "") {
            GameManager.pollGame()
        }
    }

    private fun checkWinState() {
        when {
            GameManager.state == win1 ->
                Toast.makeText(this, "You won!", Toast.LENGTH_SHORT).show()
            GameManager.state == win2 ->
                Toast.makeText(this, "You won!", Toast.LENGTH_SHORT).show()
            GameManager.state == win3 ->
                Toast.makeText(this, "You won!", Toast.LENGTH_SHORT).show()
            GameManager.state == win4 ->
                Toast.makeText(this, "You won!", Toast.LENGTH_SHORT).show()
            GameManager.state == win5 ->
                Toast.makeText(this, "You won!", Toast.LENGTH_SHORT).show()
            GameManager.state == win6 ->
                Toast.makeText(this, "You won!", Toast.LENGTH_SHORT).show()
            GameManager.state == win7 ->
                Toast.makeText(this, "You won!", Toast.LENGTH_SHORT).show()
            GameManager.state == win8 ->
                Toast.makeText(this, "You won!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun clickListeners() {
        binding.textView00.setOnClickListener {

            if (GameManager.activePlayer) {
                binding.textView00.text = "X"
            } else {
                binding.textView00.text = "O"
            }
            GameManager.state = listOf(listOf(1,0,0), listOf(0,0,0), listOf(0,0,0))
            checkWinState()
            GameManager.updateGame()
        }

        binding.textView01.setOnClickListener {

            if (GameManager.activePlayer) {
                binding.textView01.text = "X"
            } else {
                binding.textView01.text = "O"
            }
            GameManager.state = listOf(listOf(0,1,0), listOf(0,0,0), listOf(0,0,0))
            GameManager.updateGame()
        }

        binding.textView02.setOnClickListener {

            if (GameManager.activePlayer) {
                binding.textView02.text = "X"
            } else {
                binding.textView02.text = "O"
            }
            GameManager.state = listOf(listOf(0,0,1), listOf(0,0,0), listOf(0,0,0))
            GameManager.updateGame()
        }

        binding.textView03.setOnClickListener {

            if (GameManager.activePlayer) {
                binding.textView03.text = "X"
            } else {
                binding.textView03.text = "O"
            }
            GameManager.state = listOf(listOf(0,0,0), listOf(1,0,0), listOf(0,0,0))
            GameManager.updateGame()
        }

        binding.textView04.setOnClickListener {

            if (GameManager.activePlayer) {
                binding.textView04.text = "X"
            } else {
                binding.textView04.text = "O"
            }
            GameManager.state = listOf(listOf(0,0,0), listOf(0,1,0), listOf(0,0,0))
            GameManager.updateGame()
        }

        binding.textView05.setOnClickListener {

            if (GameManager.activePlayer) {
                binding.textView05.text = "X"
            } else {
                binding.textView05.text = "O"
            }
            GameManager.state = listOf(listOf(0,0,0), listOf(0,0,1), listOf(0,0,0))
            GameManager.updateGame()
        }

        binding.textView06.setOnClickListener {

            if (GameManager.activePlayer) {
                binding.textView06.text = "X"
            } else {
                binding.textView06.text = "O"
            }
            GameManager.state = listOf(listOf(0,0,0), listOf(0,0,0), listOf(1,0,0))
            GameManager.updateGame()
        }

        binding.textView07.setOnClickListener {

            if (GameManager.activePlayer) {
                binding.textView07.text = "X"
            } else {
                binding.textView07.text = "O"
            }
            GameManager.state = listOf(listOf(0,0,0), listOf(0,0,0), listOf(0,1,0))
            GameManager.updateGame()
        }

        binding.textView08.setOnClickListener {

            if (GameManager.activePlayer) {
                binding.textView08.text = "X"
            } else {
                binding.textView08.text = "O"
            }
            GameManager.state = listOf(listOf(0,0,0), listOf(0,0,0), listOf(0,0,1))
            GameManager.updateGame()
        }
    }
}