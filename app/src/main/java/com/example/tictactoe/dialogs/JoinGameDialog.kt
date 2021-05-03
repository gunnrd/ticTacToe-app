package com.example.tictactoe.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.tictactoe.GameActivity
import com.example.tictactoe.GameManager
import com.example.tictactoe.databinding.DialogJoinGameBinding
import java.lang.ClassCastException

class JoinGameDialog(): DialogFragment() {

    private lateinit var listener: GameDialogListener

    override fun onCreateDialog(savedInstance: Bundle?): Dialog {
        return activity?.let {
            val builder: AlertDialog.Builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val binding = DialogJoinGameBinding.inflate(inflater)

            builder.apply {
                setTitle("Join game")
                setPositiveButton("Join") { _, _ ->

                    when {
                        binding.joinGamePlayerName.text.isEmpty() ->
                            Toast.makeText(it, "Player name is required", Toast.LENGTH_SHORT).show()
                        binding.joinGameId.text.isEmpty() ->
                            Toast.makeText(it, "Game id is required", Toast.LENGTH_SHORT).show()
                        else -> {
                            listener.onDialogJoinGame(
                                    binding.joinGamePlayerName.text.toString(),
                                    binding.joinGameId.text.toString()
                            )

                            GameManager.playerTwo = binding.joinGamePlayerName.text.toString()
                            GameManager.gameId = binding.joinGameId.text.toString()
                            GameManager.joinGame()

                            startActivity(Intent(context, GameActivity::class.java))
                        }
                    }
                }
                setNegativeButton("Cancel") { dialog, _ ->
                    dialog.cancel()
                }
                setView(binding.root)
            }
            builder.create()

        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as GameDialogListener
        } catch (e: ClassCastException){
            throw ClassCastException(("$context must implement GameDialogListener"))

        }
    }
}