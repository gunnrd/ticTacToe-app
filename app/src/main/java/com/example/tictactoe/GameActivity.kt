package com.example.tictactoe

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.tictactoe.GameManager.activePlayer
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
    private var cellNumber = 0

    private val poll = object : Runnable {
        override fun run() {
            poll()
            //TODO Change delay
            gameHandler.postDelayed(this, 5000)
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
        if (host && activePlayer) {
            textView.text = "X"
            newState[i] = "X"
        } else {
            textView.text = "O"
            newState[i] = "O"
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
                getChangedCell()
                checkWinner()
            }
        }
    }

    private fun startNewGame() {
        binding.buttonStartNewGame.setOnClickListener {
            /*
            if (host) {
                GameManager.startNewGame()
            } else {
                GameManager.joinGame()
            }*/
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
            //clickListeners()
        }
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

    private fun checkWinner() {
        when {
            GameManager.countCheckedCells == 9 && !GameManager.winConditions() -> {
                binding.textViewWinner.text = context.getString(R.string.draw)
                binding.buttonStartNewGame.isVisible = true
                newState = GameManager.gameStateStart.flatten() as MutableList<String>
                pollState = GameManager.gameStateStart.flatten() as MutableList<String>
                deactivateClickable()
            }
            GameManager.winConditions() && activePlayer -> {
                binding.textViewWinner.text = context.getString(R.string.player_one_wins)
                binding.buttonStartNewGame.isVisible = true
                newState = GameManager.gameStateStart.flatten() as MutableList<String>
                pollState = GameManager.gameStateStart.flatten() as MutableList<String>
                deactivateClickable()
            }
            GameManager.winConditions() && !activePlayer -> {
                binding.textViewWinner.text = context.getString(R.string.player_two_wins)
                binding.buttonStartNewGame.isVisible = true
                newState = GameManager.gameStateStart.flatten() as MutableList<String>
                pollState = GameManager.gameStateStart.flatten() as MutableList<String>
                deactivateClickable()
            }
        }
    }

    private fun getChangedCell() {
        when {
            pollState[0] != "0" -> {
                binding.textView0.text = pollState[0]
                binding.textView0.isClickable = false
            }
            pollState[1] != "0" -> {
                binding.textView1.text = pollState[1]
                binding.textView1.isClickable = false
            }
            pollState[2] != "0" -> {
                binding.textView2.text = pollState[2]
                binding.textView2.isClickable = false
            }
            pollState[3] != "0" -> {
                binding.textView3.text = pollState[3]
                binding.textView3.isClickable = false
            }
            pollState[4] != "0" -> {
                binding.textView4.text = pollState[4]
                binding.textView4.isClickable = false
            }
            pollState[5] != "0" -> {
                binding.textView5.text = pollState[5]
                binding.textView5.isClickable = false
            }
            pollState[6] != "0" -> {
                binding.textView6.text = pollState[6]
                binding.textView6.isClickable = false
            }
            pollState[7] != "0" -> {
                binding.textView7.text = pollState[7]
                binding.textView7.isClickable = false
            }
            pollState[8] != "0" -> {
                binding.textView8.text = pollState[8]
                binding.textView8.isClickable = false
            }
        }
    }

}