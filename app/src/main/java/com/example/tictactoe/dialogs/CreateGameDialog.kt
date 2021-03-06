package com.example.tictactoe.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.tictactoe.GameManager
import com.example.tictactoe.R
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
                setTitle(context.getString(R.string.create_game))
                setPositiveButton(context.getString(R.string.create)) { _, _ ->
                    if(binding.playerName.text.toString() != ""){
                        listener.onDialogCreateGame(binding.playerName.text.toString())

                        GameManager.playerOne = binding.playerName.text.toString()
                        GameManager.createGame()

                    } else {
                        Toast.makeText(it, context.getString(R.string.name_required), Toast.LENGTH_SHORT).show()
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