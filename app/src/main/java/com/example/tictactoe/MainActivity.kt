package com.example.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.tictactoe.App.Companion.context
import com.example.tictactoe.databinding.ActivityMainBinding
import com.example.tictactoe.dialogs.CreateGameDialog
import com.example.tictactoe.dialogs.GameDialogListener
import com.example.tictactoe.dialogs.JoinGameDialog

class MainActivity : AppCompatActivity(), GameDialogListener {

    private val TAG: String = "MainActivity"
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonCreateGame.setOnClickListener {
            createNewGame()
        }

        binding.buttonJoinGame.setOnClickListener {
            joinGame()
        }

        binding.buttonPlayVsBot.setOnClickListener {
            startActivity(Intent(context, GameBotActivity::class.java))
        }
    }

    private fun createNewGame() {
        val dialog = CreateGameDialog()
        dialog.show(supportFragmentManager,"CreateGameDialogFragment")
    }

    private fun joinGame() {
        val dialog = JoinGameDialog()
        dialog.show(supportFragmentManager,"JoinGameDialogFragment")
    }

    override fun onDialogCreateGame(player: String) {
        Log.d(TAG,player)
    }

    override fun onDialogJoinGame(player: String, gameId: String) {
        Log.d(TAG, "$player $gameId")
    }
}