package com.example.tictactoe

import com.example.tictactoe.api.GameService
import com.example.tictactoe.api.data.Game
import com.example.tictactoe.api.data.GameState

object GameManager {

    var gameId: String = ""
    var player: String = ""
    var state: GameState? = null
    var activePlayer: Boolean = false
    var countChecked = 0

    private val gameStateStart: GameState = listOf(listOf(0,0,0), listOf(0,0,0), listOf(0,0,0))

    // Temp variables for testing
    // var player starts the game
    var testPlayer: String = ""

    fun createGame() {
        GameService.createGame(player, gameStateStart) { game: Game?, error: Int? ->
            if (error != null) {
                //TODO give response to given error code
            } else {
                // TODO Game is created. What to do?
                gameId = game?.gameId.toString()
                state = gameStateStart
                activePlayer = true
            }
        }
    }

    // TODO change back to player after testing
    fun joinGame() {
        GameService.joinGame(testPlayer, gameId) { game: Game?, error: Int? ->
            if (error != null) {
                //TODO give response to given error code
            } else {
                //TODO Game is joined
            }
        }
    }

    fun updateGame() {
        state?.let {
            GameService.updateGame(gameId, it) { game: Game?, error: Int? ->
                if (error != null) {
                    //TODO give response to given error code
                } else {
                    //TODO Game is updated

                    activePlayer = !activePlayer
                    countChecked ++
                }
            }
        }
    }

    fun pollGame() {
        GameService.pollGame(gameId) { game: Game?, error: Int? ->
            if (error != null) {
                //TODO give response to given error code
            } else {
                //TODO Game is polled
                // If gamestate has changed.
                // countChecked ++
            }
        }
    }
}