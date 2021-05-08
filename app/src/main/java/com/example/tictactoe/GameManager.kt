package com.example.tictactoe

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Bundle
import com.example.tictactoe.App.Companion.context
import com.example.tictactoe.api.GameService
import com.example.tictactoe.api.data.Game
import com.example.tictactoe.api.data.GameState
import java.util.*

// Creator of game starts the game

object GameManager {

    var gameId: String = ""
    var playerOne: String = ""
    var playerTwo: String = ""
    var state: GameState? = null
    var activePlayer: Boolean = false
    var host = false
    var countCheckedCells = 0
    var newState = mutableListOf<String>()
    var pollState = mutableListOf<String>()
    val gameStateStart: GameState = List(3) { List(3) {"0"} }

    fun winConditions(): Boolean {
        val state = state!!
        when {
            state[0][0] == state[0][1] && state[0][0] == state[0][2] && state[0][0] != "0" ->
                return true
            state[1][0] == state[1][1] && state[1][0] == state[1][2] && state[1][0] != "0" ->
                return true
            state[2][0] == state[2][1] && state[2][0] == state[2][2] && state[2][0] != "0" ->
                return true
            state[0][0] == state[1][0] && state[0][0] == state[2][0] && state[0][0] != "0" ->
                return true
            state[0][1] == state[1][1] && state[0][1] == state[2][1] && state[0][1] != "0" ->
                return true
            state[0][2] == state[1][2] && state[0][2] == state[2][2] && state[0][2] != "0" ->
                return true
            state[0][0] == state[1][1] && state[0][0] == state[2][2] && state[0][0] != "0" ->
                return true
            state[0][2] == state[1][1] && state[0][2] == state[2][0] && state[0][2] != "0" ->
                return true
            else -> {
                return false
            }
        }
    }

    private fun startActivity(game: Game) {
        val bundle = Bundle()
        val intent = Intent(context, GameActivity::class.java).apply {
            bundle.putParcelable("RESPONSE", game)
            putExtras(bundle)
        }
        intent.flags = FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

    fun createGame() {
        GameService.createGame(playerOne, gameStateStart) { game: Game?, error: Int? ->
            if (error != null) {
                //TODO give response to given error code
            } else {
                gameId = game?.gameId.toString()
                state = gameStateStart
                host = true
                activePlayer = true
                newState = state?.flatten() as MutableList<String>

                if (game != null) {
                    startActivity(game)
                }
            }
        }
    }

    fun joinGame() {
        GameService.joinGame(playerTwo, gameId) { game: Game?, error: Int? ->
            if (error != null) {
                //TODO give response to given error code
            } else {
                if (game != null) {
                    host = false
                    activePlayer = false
                    startActivity(game)
                }
            }
        }
    }

    fun updateGame() {
        state?.let {
            GameService.updateGame(gameId, it) { _: Game?, error: Int? ->
                if (error != null) {
                    //TODO give response to given error code
                } else {
                    activePlayer = false
                    countCheckedCells += 1
                    newState = state?.flatten() as MutableList<String>
                }
            }
        }
    }

    fun pollGame() {
        GameService.pollGame(gameId) { game: Game?, error: Int? ->
            if (error != null) {
                //TODO give response to given error code
            } else {

                if (newState != pollState) {

                    if (winConditions()) {
                        activePlayer = false
                    } else {
                        activePlayer = true
                        countCheckedCells += 1
                    }
                }
                newState = pollState
            }
        }
    }

    fun startNewGame() {
        state = gameStateStart

        state?.let {
            GameService.updateGame(gameId, it) { _: Game?, error: Int? ->
                if (error != null) {
                    //TODO give response to given error code
                } else {
                    activePlayer = if (host) {
                        true
                    } else {
                        !activePlayer
                    }
                    countCheckedCells = 0
                }
            }
        }
    }
}