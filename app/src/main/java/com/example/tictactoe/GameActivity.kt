package com.example.tictactoe

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.tictactoe.GameManager.activePlayer
import com.example.tictactoe.GameManager.newState
import com.example.tictactoe.GameManager.pollState
import com.example.tictactoe.GameManager.state
import com.example.tictactoe.GameManager.host
import com.example.tictactoe.api.GameService.context
import com.example.tictactoe.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding
    //private lateinit var binding: GameLayoutBinding
    private lateinit var gameHandler: Handler
    //private lateinit var recyclerView: RecyclerView
    //private var state: GameState? = null
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

        //recyclerView = findViewById(R.id.gameRecyclerView)
        //recyclerView.adapter = GameAdapter(state!!)
        //recyclerView.layoutManager = LinearLayoutManager(this)

        val currentGameId = intent.getStringExtra("GAMEID")
        binding.gameIdValue.text = currentGameId.toString()

        gameHandler = Handler(Looper.getMainLooper())
        gameHandler.post { poll() }

        binding.buttonStartNewGame.isVisible = false
        binding.playerOneValue.text = GameManager.player

        if (GameManager.playerTwo != "") {
            binding.playerTwoValue.text = GameManager.playerTwo
        }

        clickListeners()
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

    private fun poll() {
        if (GameManager.gameId != "") {
            GameManager.pollGame()

            if (pollState.isNotEmpty()) {
                getChangedCell()
                checkWinner()
            }
        }
    }

    private fun startNewGame() {
        binding.buttonStartNewGame.setOnClickListener {
            if (host) {
                GameManager.startNewGame()
            } else {
                GameManager.joinGame()
            }

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
            clickListeners()
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
                //state = gameStateStart
                deactivateClickable()
            }
            GameManager.winConditions() && activePlayer -> {
                binding.textViewWinner.text = context.getString(R.string.player_one_wins)
                binding.buttonStartNewGame.isVisible = true
                //state = gameStateStart
                deactivateClickable()
            }
            GameManager.winConditions() && !activePlayer -> {
                binding.textViewWinner.text = context.getString(R.string.player_two_wins)
                binding.buttonStartNewGame.isVisible = true
                //state = gameStateStart
                deactivateClickable()
            }
        }
    }

    private fun getChangedCell() {
        when {
            pollState[0] != "0" -> binding.textView0.text = pollState[0]
            pollState[1] != "0" -> binding.textView1.text = pollState[1]
            pollState[2] != "0" -> binding.textView2.text = pollState[2]
            pollState[3] != "0" -> binding.textView3.text = pollState[3]
            pollState[4] != "0" -> binding.textView4.text = pollState[4]
            pollState[5] != "0" -> binding.textView5.text = pollState[5]
            pollState[6] != "0" -> binding.textView6.text = pollState[6]
            pollState[7] != "0" -> binding.textView7.text = pollState[7]
            pollState[8] != "0" -> binding.textView8.text = pollState[8]
        }
    }

    private fun clickListeners() {
        binding.textView0.setOnClickListener {
            cellNumber = 1
            if (host && newState[0] == "0") {
                binding.textView0.text = "X"
                newState[0] = "X"

            } else {
                binding.textView0.text = "O"
                newState[0] = "O"

            }
            state = newState.chunked(3)
            binding.textView0.isClickable = false
            checkWinner()
            GameManager.updateGame()
        }

        binding.textView1.setOnClickListener {
            cellNumber = 2
            if (host && newState[1] == "0") {
                binding.textView1.text = "X"
                newState[1] = "X"

            } else {
                binding.textView1.text = "O"
                newState[1] = "O"

            }
            state = newState.chunked(3)
            binding.textView1.isClickable = false
            checkWinner()
            GameManager.updateGame()
        }

        binding.textView2.setOnClickListener {
            cellNumber = 3
            if (host && newState[2] == "0") {
                binding.textView2.text = "X"
                newState[2] = "X"

            } else {
                binding.textView2.text = "O"
                newState[2] = "O"
            }
            state = newState.chunked(3)
            binding.textView2.isClickable = false
            checkWinner()
            GameManager.updateGame()
        }

        binding.textView3.setOnClickListener {
            cellNumber = 4
            if (host && newState[3] == "0") {
                binding.textView3.text = "X"
                newState[3] = "X"

            } else {
                binding.textView3.text = "O"
                newState[3] = "O"

            }
            state = newState.chunked(3)
            checkWinner()
            binding.textView3.isClickable = false
            GameManager.updateGame()
        }

        binding.textView4.setOnClickListener {
            cellNumber = 5
            if (host && newState[4] == "0") {
                binding.textView4.text = "X"
                newState[4] = "X"

            } else {
                binding.textView4.text = "O"
                newState[4] = "O"

            }
            state = newState.chunked(3)
            checkWinner()
            binding.textView4.isClickable = false
            GameManager.updateGame()
        }

        binding.textView5.setOnClickListener {
            cellNumber = 6
            if (host && newState[5] == "0") {
                binding.textView5.text = "X"
                newState[5] = "X"

            } else {
                binding.textView5.text = "O"
                newState[5] = "O"

            }
            state = newState.chunked(3)
            binding.textView5.isClickable = false
            checkWinner()
            GameManager.updateGame()
        }

        binding.textView6.setOnClickListener {
            cellNumber = 7
            if (host && newState[6] == "0") {
                binding.textView6.text = "X"
                newState[6] = "X"

            } else {
                binding.textView6.text = "O"
                newState[6] = "O"

            }
            state = newState.chunked(3)
            binding.textView6.isClickable = false
            checkWinner()
            GameManager.updateGame()
        }

        binding.textView7.setOnClickListener {
            cellNumber = 8
            if (host && newState[7] == "0") {
                binding.textView7.text = "X"
                newState[7] = "X"

            } else {
                binding.textView7.text = "O"
                newState[7] = "O"

            }
            state = newState.chunked(3)
            binding.textView7.isClickable = false
            checkWinner()
            GameManager.updateGame()
        }

        binding.textView8.setOnClickListener {
            cellNumber = 9
            if (host && newState[8] == "0") {
                binding.textView8.text = "X"
                newState[8] = "X"

            } else {
                binding.textView8.text = "O"
                newState[8] = "O"

            }
            state = newState.chunked(3)
            binding.textView8.isClickable = false
            checkWinner()
            GameManager.updateGame()
        }
    }
}