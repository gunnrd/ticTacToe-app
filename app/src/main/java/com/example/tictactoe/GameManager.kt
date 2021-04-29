package com.example.tictactoe

import com.example.tictactoe.api.GameService
import com.example.tictactoe.api.data.Game
import com.example.tictactoe.api.data.GameState

// var player starts the game

object GameManager {

    var gameId: String = ""
    var player: String = ""
    var playerTwo: String = ""
    var state: GameState? = null
    var activePlayer: Boolean = false
    var countChecked = 0

    private val gameStateStart: GameState = listOf(listOf(0,0,0), listOf(0,0,0), listOf(0,0,0))

    fun createGame() {
        GameService.createGame(player, gameStateStart) { game: Game?, error: Int? ->
            if (error != null) {
                //TODO give response to given error code
            } else {
                gameId = game?.gameId.toString()
                state = gameStateStart
                activePlayer = true
            }
        }
    }

    fun joinGame() {
        GameService.joinGame(player, gameId) { game: Game?, error: Int? ->
            if (error != null) {
                //TODO give response to given error code
            } else {
                if (game != null) {
                    if (game.players[1] != "")
                        playerTwo = game.players[1]

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