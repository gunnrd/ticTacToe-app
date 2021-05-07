package com.example.tictactoe

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.tictactoe.GameManager.newState
import com.example.tictactoe.GameManager.pollState
import com.example.tictactoe.GameManager.state
import com.example.tictactoe.GameManager.host
import com.example.tictactoe.api.GameService.context
import com.example.tictactoe.api.data.Game
import com.example.tictactoe.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityGameBinding
    private lateinit var gameHandler: Handler

    private enum class MARK(var value: String) {
        ZERO("0"),
        PLAYERONE("X"),
        PLAYERTWO("O")
    }

    private val poll = object : Runnable {
        override fun run() {
            poll()
            gameHandler.postDelayed(this, 3000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.title = "Main menu"

        val response = intent.getParcelableExtra<Game>("RESPONSE")
        if (response != null) {
            binding.gameIdValue.text = response.gameId
            binding.playerOneValue.text = response.players[0]
        }

        gameHandler = Handler(Looper.getMainLooper())
        gameHandler.post { poll() }

        binding.buttonStartNewGame.isVisible = false

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

        playGame(cellIndex, textView)
    }

    private fun playGame(i: Int, textView: TextView) {
        if (host) {
            textView.text = MARK.PLAYERONE.value
            newState[i] = MARK.PLAYERONE.value
        } else {
            textView.text = MARK.PLAYERTWO.value
            newState[i] = MARK.PLAYERTWO.value
        }
        textView.isClickable = false
        state = newState.chunked(3)
        checkWinner()
        GameManager.updateGame()
    }

    private fun poll() {
        if (GameManager.gameId != "") {
            GameManager.pollGame()

            if (GameManager.playerTwo != "") {
                binding.playerTwoValue.text = GameManager.playerTwo
            }

            if (pollState.isNotEmpty()) {
                displayPollChanges()
                checkWinner()
            }
        }
    }

    private fun startNewGame() {
        binding.buttonStartNewGame.setOnClickListener {
            GameManager.updateGame()
            pollState = mutableListOf()
            GameManager.startNewGame()

            binding.buttonStartNewGame.isVisible = false
            binding.textViewWinner.text = ""
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

    private fun checkWinner() {
        when {
            GameManager.countCheckedCells == 9 && !GameManager.winConditions() -> {
                binding.textViewWinner.text = context.getString(R.string.draw)
                binding.buttonStartNewGame.isVisible = true
                newState = GameManager.gameStateStart.flatten() as MutableList<String>
                pollState = GameManager.gameStateStart.flatten() as MutableList<String>
                deactivateClickable()
            }
            GameManager.winConditions() && host -> {
                binding.textViewWinner.text = context.getString(R.string.player_one_wins)
                binding.buttonStartNewGame.isVisible = true
                newState = GameManager.gameStateStart.flatten() as MutableList<String>
                pollState = GameManager.gameStateStart.flatten() as MutableList<String>
                deactivateClickable()
            }
            GameManager.winConditions() && !host -> {
                binding.textViewWinner.text = context.getString(R.string.player_two_wins)
                binding.buttonStartNewGame.isVisible = true
                newState = GameManager.gameStateStart.flatten() as MutableList<String>
                pollState = GameManager.gameStateStart.flatten() as MutableList<String>
                deactivateClickable()
            }
        }
    }

    private fun displayPollChanges() {
        when {
            pollState[0] != MARK.ZERO.value && binding.textView0.text.isEmpty()-> {
                binding.textView0.text = pollState[0]
                binding.textView0.isClickable = false
            }
            pollState[1] != MARK.ZERO.value && binding.textView1.text.isEmpty() -> {
                binding.textView1.text = pollState[1]
                binding.textView1.isClickable = false
            }
            pollState[2] != MARK.ZERO.value && binding.textView2.text.isEmpty() -> {
                binding.textView2.text = pollState[2]
                binding.textView2.isClickable = false
            }
            pollState[3] != MARK.ZERO.value && binding.textView3.text.isEmpty() -> {
                binding.textView3.text = pollState[3]
                binding.textView3.isClickable = false
            }
            pollState[4] != MARK.ZERO.value && binding.textView4.text.isEmpty() -> {
                binding.textView4.text = pollState[4]
                binding.textView4.isClickable = false
            }
            pollState[5] != MARK.ZERO.value && binding.textView5.text.isEmpty() -> {
                binding.textView5.text = pollState[5]
                binding.textView5.isClickable = false
            }
            pollState[6] != MARK.ZERO.value && binding.textView6.text.isEmpty() -> {
                binding.textView6.text = pollState[6]
                binding.textView6.isClickable = false
            }
            pollState[7] != MARK.ZERO.value && binding.textView7.text.isEmpty() -> {
                binding.textView7.text = pollState[7]
                binding.textView7.isClickable = false
            }
            pollState[8] != MARK.ZERO.value && binding.textView8.text.isEmpty() -> {
                binding.textView8.text = pollState[8]
                binding.textView8.isClickable = false
            }
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

    private fun deactivateClickable() {
        binding.textView0.isClickable = false
        binding.textView1.isClickable = false
        binding.textView2.isClickable = false
        binding.textView3.isClickable = false
        binding.textView4.isClickable = false
        binding.textView5.isClickable = false
        binding.textView6.isClickable = false
        binding.textView7.isClickable = false
        binding.textView8.isClickable = false
    }
}