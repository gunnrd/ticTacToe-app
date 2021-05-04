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
import com.example.tictactoe.GameManager.gameStateStart
import com.example.tictactoe.api.GameService.context
import com.example.tictactoe.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding
    //private lateinit var binding: GameLayoutBinding
    private lateinit var gameHandler: Handler
    //private lateinit var recyclerView: RecyclerView
    //private var state: GameState? = null
    var cellNumber = 0


    private val poll = object : Runnable {
        override fun run() {
            poll()
            gameHandler.postDelayed(this, 5000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = ActivityGameBinding.inflate(layoutInflater)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.title = "Main menu"

        //recyclerView = findViewById(R.id.gameRecyclerView)
        //recyclerView.adapter = GameAdapter(state!!)
        //recyclerView.layoutManager = LinearLayoutManager(this)

        gameHandler = Handler(Looper.getMainLooper())
        gameHandler.post { poll() }

        binding.buttonStartNewGame.isVisible = false
        binding.gameIdValue.text = GameManager.gameId
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

            if (pollState.isNotEmpty() && !activePlayer) {
                getChangedCell()
                checkWinner()
            }
        }
    }

    private fun startNewGame() {
        binding.buttonStartNewGame.setOnClickListener {
            GameManager.startNewGame()
            binding.buttonStartNewGame.isVisible = false
            binding.textViewWinner.text = ""
            binding.textView1.text = ""
            binding.textView2.text = ""
            binding.textView3.text = ""
            binding.textView4.text = ""
            binding.textView5.text = ""
            binding.textView6.text = ""
            binding.textView7.text = ""
            binding.textView8.text = ""
            binding.textView9.text = ""
            clickListeners()
        }
    }

    private fun deactivateClickable() {
        binding.textView1.isClickable = false
        binding.textView2.isClickable = false
        binding.textView3.isClickable = false
        binding.textView4.isClickable = false
        binding.textView5.isClickable = false
        binding.textView6.isClickable = false
        binding.textView7.isClickable = false
        binding.textView8.isClickable = false
        binding.textView9.isClickable = false
    }

    private fun checkWinner() {
        when {
            GameManager.countCheckedCells == 9 && !GameManager.winConditions() -> {
                binding.textViewWinner.text = context.getString(R.string.draw)
                binding.buttonStartNewGame.isVisible = true
                state = gameStateStart
                deactivateClickable()
            }
            GameManager.winConditions() && activePlayer -> {
                binding.textViewWinner.text = context.getString(R.string.player_one_wins)
                binding.buttonStartNewGame.isVisible = true
                state = gameStateStart
                deactivateClickable()
            }
            GameManager.winConditions() && !activePlayer -> {
                binding.textViewWinner.text = context.getString(R.string.player_two_wins)
                binding.buttonStartNewGame.isVisible = true
                state = gameStateStart
                deactivateClickable()
            }
        }
    }

    private fun getChangedCell() {
        when {
            newState[0] != pollState[0] && binding.textView1.text.isEmpty() && GameManager.host -> {
                binding.textView1.text = "X"
                binding.textView1.isClickable = false
                setNewState("X")
            }
            newState[1] != pollState[1] && binding.textView2.text == "" && GameManager.host -> {
                binding.textView2.text = "X"
                binding.textView2.isClickable = false
                setNewState("X")
            }
            newState[2] != pollState[2] && binding.textView3.text == "" && GameManager.host -> {
                binding.textView3.text = "X"
                binding.textView3.isClickable = false
                setNewState("X")
            }
            newState[3] != pollState[3] && binding.textView4.text == "" && GameManager.host -> {
                binding.textView4.text = "X"
                binding.textView4.isClickable = false
                setNewState("X")
            }
            newState[4] != pollState[4] && binding.textView5.text == "" && GameManager.host -> {
                binding.textView5.text = "X"
                binding.textView5.isClickable = false
                setNewState("X")
            }
            newState[5] != pollState[5] && binding.textView6.text == "" && GameManager.host -> {
                binding.textView6.text = "X"
                binding.textView6.isClickable = false
                setNewState("X")
            }
            newState[6] != pollState[6] && binding.textView7.text == "" && GameManager.host -> {
                binding.textView7.text = "X"
                binding.textView7.isClickable = false
                setNewState("X")
            }
            newState[7] != pollState[7] && binding.textView8.text == "" && GameManager.host -> {
                binding.textView8.text = "X"
                binding.textView8.isClickable = false
                setNewState("X")
            }
            newState[8] != pollState[8] && binding.textView9.text == "" && GameManager.host -> {
                binding.textView9.text = "X"
                binding.textView9.isClickable = false
                setNewState("X")
            }
            newState[0] != pollState[0] && binding.textView1.text == "" && !GameManager.host -> {
                binding.textView1.text = "O"
                binding.textView1.isClickable = false
                setNewState("O")
            }
            newState[1] != pollState[1] && binding.textView2.text == "" && !GameManager.host -> {
                binding.textView2.text = "O"
                binding.textView2.isClickable = false
                setNewState("O")
            }
            newState[2] != pollState[2] && binding.textView3.text == "" && !GameManager.host -> {
                binding.textView3.text = "O"
                binding.textView3.isClickable = false
                setNewState("O")
            }
            newState[3] != pollState[3] && binding.textView4.text == "" && !GameManager.host -> {
                binding.textView4.text = "O"
                binding.textView4.isClickable = false
                setNewState("O")
            }
            newState[4] != pollState[4] && binding.textView5.text == "" && !GameManager.host -> {
                binding.textView5.text = "O"
                binding.textView5.isClickable = false
                setNewState("O")
            }
            newState[5] != pollState[5] && binding.textView6.text == "" && !GameManager.host -> {
                binding.textView6.text = "O"
                binding.textView6.isClickable = false
                setNewState("O")
            }
            newState[6] != pollState[6] && binding.textView7.text == "" && !GameManager.host -> {
                binding.textView7.text = "O"
                binding.textView7.isClickable = false
                setNewState("O")
            }
            newState[7] != pollState[7] && binding.textView8.text == "" && !GameManager.host -> {
                binding.textView8.text = "O"
                binding.textView8.isClickable = false
                setNewState("O")
            }
            newState[8] != pollState[8] && binding.textView9.text == "" && !GameManager.host -> {
                binding.textView9.text = "O"
                binding.textView9.isClickable = false
                setNewState("O")
            }
        }
    }

    private fun clickListeners() {
        binding.textView1.setOnClickListener {
            cellNumber = 1
            if (activePlayer && GameManager.host) {
                binding.textView1.text = "X"
                setNewState("X")
            } else {
                binding.textView1.text = "O"
                setNewState("O")
            }
            binding.textView1.isClickable = false
            checkWinner()
            GameManager.updateGame()
        }

        binding.textView2.setOnClickListener {
            cellNumber = 2
            if (activePlayer && GameManager.host) {
                binding.textView2.text = "X"
                setNewState("X")
            } else {
                binding.textView2.text = "O"
                setNewState("O")
            }
            binding.textView2.isClickable = false
            checkWinner()
            GameManager.updateGame()
        }

        binding.textView3.setOnClickListener {
            cellNumber = 3
            if (activePlayer && GameManager.host) {
                binding.textView3.text = "X"
                setNewState("X")
            } else {
                binding.textView3.text = "O"
                setNewState("O")
            }
            binding.textView3.isClickable = false
            checkWinner()
            GameManager.updateGame()
        }

        binding.textView4.setOnClickListener {
            cellNumber = 4
            if (activePlayer && GameManager.host) {
                binding.textView4.text = "X"
                setNewState("X")
            } else {
                binding.textView4.text = "O"
                setNewState("O")
            }
            checkWinner()
            binding.textView4.isClickable = false
            GameManager.updateGame()
        }

        binding.textView5.setOnClickListener {
            cellNumber = 5
            if (activePlayer && GameManager.host) {
                binding.textView5.text = "X"
                setNewState("X")
            } else {
                binding.textView5.text = "O"
                setNewState("O")
            }
            checkWinner()
            binding.textView5.isClickable = false
            GameManager.updateGame()
        }

        binding.textView6.setOnClickListener {
            cellNumber = 6
            if (activePlayer && GameManager.host) {
                binding.textView6.text = "X"
                setNewState("X")
            } else {
                binding.textView6.text = "O"
                setNewState("O")
            }
            binding.textView6.isClickable = false
            checkWinner()
            GameManager.updateGame()
        }

        binding.textView7.setOnClickListener {
            cellNumber = 7
            if (activePlayer && GameManager.host) {
                binding.textView7.text = "X"
                setNewState("X")
            } else {
                binding.textView7.text = "O"
                setNewState("O")
            }
            binding.textView7.isClickable = false
            checkWinner()
            GameManager.updateGame()
        }

        binding.textView8.setOnClickListener {
            cellNumber = 8
            if (activePlayer && GameManager.host) {
                binding.textView8.text = "X"
                setNewState("X")
            } else {
                binding.textView8.text = "O"
                setNewState("O")
            }
            binding.textView8.isClickable = false
            checkWinner()
            GameManager.updateGame()
        }

        binding.textView9.setOnClickListener {
            cellNumber = 9
            if (activePlayer && GameManager.host) {
                binding.textView9.text = "X"
                setNewState("X")
            } else {
                binding.textView9.text = "O"
                setNewState("O")
            }
            binding.textView9.isClickable = false
            checkWinner()
            GameManager.updateGame()
        }
    }

    private fun setNewState(newValue: String) {
        when {
            cellNumber == 1 && newState[0] == "0" -> {
                newState[0] = newValue
                state = newState.chunked(3)
                println(state)
            }
            cellNumber == 2 && newState[1] == "0" -> {
                newState[1] = newValue
                state = newState.chunked(3)
                println(state)
            }
            cellNumber == 3 && newState[2] == "0" -> {
                newState[2] = newValue
                state = newState.chunked(3)
                println(state)
            }
            cellNumber == 4 && newState[3] == "0" -> {
                newState[3] = newValue
                state = newState.chunked(3)
                println(state)
            }
            cellNumber == 5 && newState[4] == "0" -> {
                newState[4] = newValue
                state = newState.chunked(3)
                println(state)
            }
            cellNumber == 6 && newState[5] == "0" -> {
                newState[5] = newValue
                state = newState.chunked(3)
                println(state)
            }
            cellNumber == 7 && newState[6] == "0" -> {
                newState[6] = newValue
                state = newState.chunked(3)
                println(state)
            }
            cellNumber == 8 && newState[7] == "0" -> {
                newState[7] = newValue
                state = newState.chunked(3)
                println(state)
            }
            cellNumber == 9 && newState[8] == "0" -> {
                newState[8] = newValue
                state = newState.chunked(3)
                println(state)
            }
        }
    }
}