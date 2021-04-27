package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.tictactoe.databinding.ActivityMainBinding
import com.example.tictactoe.dialogs.CreateGameDialog
import com.example.tictactoe.dialogs.GameDialogListener

class MainActivity : AppCompatActivity(), GameDialogListener {

    private val TAG: String = "MainActivity"
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //TODO start game button listener
        binding.buttonNewGame.setOnClickListener {
            createNewGame()
        }

        //TODO join game button listener
    }

    private fun createNewGame(){
        val dialog = CreateGameDialog()
        dialog.show(supportFragmentManager,"CreateGameDialogFragment")
    }

    private fun joinGame(){
        //TODO
    }

    override fun onDialogCreateGame(player: String) {
        Log.d(TAG,player)
    }

    override fun onDialogJoinGame(player: String, gameId: String) {
        Log.d(TAG, "$player $gameId")
    }
}