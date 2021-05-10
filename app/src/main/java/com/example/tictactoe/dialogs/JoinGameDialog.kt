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
import com.example.tictactoe.R
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
                setTitle(context.getString(R.string.join_game))
                setPositiveButton(context.getString(R.string.join)) { _, _ ->

                    when {
                        binding.joinGamePlayerName.text.isEmpty() ->
                            Toast.makeText(it, context.getString(R.string.name_required), Toast.LENGTH_SHORT).show()
                        binding.joinGameId.text.isEmpty() ->
                            Toast.makeText(it, context.getString(R.string.gameId_required), Toast.LENGTH_SHORT).show()
                        else -> {
                            listener.onDialogJoinGame(
                                binding.joinGamePlayerName.text.toString(),
                                binding.joinGameId.text.toString()
                            )

                            GameManager.playerTwo = binding.joinGamePlayerName.text.toString()
                            GameManager.gameId = binding.joinGameId.text.toString()
                            GameManager.joinGame()
                        }
                    }
                }
                setNegativeButton(context.getString(R.string.cancel_alert)) { dialog, _ ->
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