package com.example.tictactoe

import android.content.Intent
import com.example.tictactoe.App.Companion.context
import com.example.tictactoe.api.GameService
import com.example.tictactoe.api.data.Game
import com.example.tictactoe.api.data.GameState

// Creator of game starts the game

object GameManager {

    var gameId: String = ""
    var player: String = ""
    var playerTwo: String = ""
    var state: GameState? = null
    var activePlayer: Boolean = false
    var host = false
    var countCheckedCells = 0
    var newState = mutableListOf<String>()
    var pollState = mutableListOf<String>()
    private val gameStateStart: GameState = List(3) { List(3) {"0"} }

    fun winConditions(): Boolean {
        val state = GameManager.state!!
        when {
            state[0][0] == state[0][1] && state[0][0] == state[0][2] && state[0][0] != "0" -> {
                println("win row 1")
                return true
            }
            state[1][0] == state[1][1] && state[1][0] == state[1][2] && state[1][0] != "0" -> {
                println("win row 2")
                return true
            }
            state[2][0] == state[2][1] && state[2][0] == state[2][2] && state[2][0] != "0" -> {
                println("win row 3")
                return true
            }
            state[0][0] == state[1][0] && state[0][0] == state[2][0] && state[0][0] != "0" -> {
                println("win column 1")
                return true
            }
            state[0][1] == state[1][1] && state[0][1] == state[2][1] && state[0][1] != "0" -> {
                println("win column 2")
                return true
            }
            state[0][2] == state[1][2] && state[0][2] == state[2][2] && state[0][2] != "0" -> {
                println("win column 3")
                return true
            }
            state[0][0] == state[1][1] && state[0][0] == state[2][2] && state[0][0] != "0" -> {
                println("win diagonal")
                return true
            }
            state[0][2] == state[1][1] && state[0][2] == state[2][0] && state[0][2] != "0" -> {
                println("win diagonal")
                return true
            }
            countCheckedCells == 9 -> {
                return false
            }
            else -> {
                return false
            }
        }
    }

    fun createGame() {
        GameService.createGame(player, gameStateStart) { game: Game?, error: Int? ->
            if (error != null) {
                //TODO give response to given error code
            } else {
                gameId = game?.gameId.toString()
                state = gameStateStart
                activePlayer = true
                host = true
                newState = state?.flatten() as MutableList<String>
                println("------------------------------ $gameId ------------------------------")

                Intent(context, GameActivity::class.java).apply {
                    putExtra("GAMEID", game?.gameId)
                }
            }
        }
    }

    fun joinGame() {
        GameService.joinGame(playerTwo, gameId) { game: Game?, error: Int? ->
            if (error != null) {
                //TODO give response to given error code
            } else {
                if (game?.state != gameStateStart) {
                    activePlayer = true
                    host = true
                }

                Intent(context, GameActivity::class.java).apply {
                    putExtra("GAMEID", game?.gameId)
                }
            }
        }
    }

    fun updateGame() {
        state?.let {
            GameService.updateGame(gameId, it) { game: Game?, error: Int? ->
                if (error != null) {
                    //TODO give response to given error code
                } else {
                    activePlayer = !activePlayer
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
                winConditions()

                if ((newState != pollState) && !winConditions()) {
                    activePlayer = true
                    countCheckedCells += 1
                }
            }
        }
    }

    fun startNewGame() {
        state = gameStateStart

        state?.let {
            GameService.updateGame(gameId, it) { game: Game?, error: Int? ->
                if (error != null) {
                    //TODO give response to given error code
                } else {
                    activePlayer = if (host) {
                        true
                    } else {
                        !activePlayer
                    }
                    countCheckedCells = 0
                    newState = state?.flatten() as MutableList<String>
                }
            }
        }
    }
}