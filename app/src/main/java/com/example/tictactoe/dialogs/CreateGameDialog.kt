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
import com.example.tictactoe.databinding.DialogCreateGameBinding
import java.lang.ClassCastException

class CreateGameDialog(): DialogFragment() {

    private lateinit var listener: GameDialogListener

    override fun onCreateDialog(savedInstance: Bundle?): Dialog {
        return activity?.let {
            val builder: AlertDialog.Builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val binding = DialogCreateGameBinding.inflate(inflater)

            builder.apply {
                setTitle("Create game")
                setPositiveButton("Create") { _, _ ->
                    if(binding.playerName.text.toString() != ""){
                        listener.onDialogCreateGame(binding.playerName.text.toString())

                        GameManager.player = binding.playerName.text.toString()
                        GameManager.createGame()

                        startActivity(Intent(context, GameActivity::class.java))
                    } else {
                        Toast.makeText(it, "Player name is required", Toast.LENGTH_SHORT).show()
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