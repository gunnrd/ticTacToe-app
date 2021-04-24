package com.example.tictactoe

import com.example.tictactoe.api.GameService
import com.example.tictactoe.api.data.Game
import com.example.tictactoe.api.data.GameState

// Skal holde rede på hvordan spillet foregår
object GameManager {

    var player: String = ""
    var gameState: GameState? = null


    private val gameStateStart: GameState = listOf(listOf(0,0,0), listOf(0,0,0), listOf(0,0,0))

    fun createGame() {
        GameService.createGame(player, gameStateStart) { _: Game?, error: Int? ->
            if (error != null) {
                //TODO give response to given error code
            } else {
                // TODO Game is created. What to do?
            }
        }
    }

    fun joinGame(player: String) {

    }

    fun updateGame() {

    }

    fun pollGame() {

    }

}