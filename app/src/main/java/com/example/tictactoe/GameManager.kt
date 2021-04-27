package com.example.tictactoe

import com.example.tictactoe.api.GameService
import com.example.tictactoe.api.data.Game
import com.example.tictactoe.api.data.GameState

// Skal holde rede på hvordan spillet foregår
object GameManager {

    lateinit var player: String
    lateinit var gameState: GameState
    lateinit var gameId: String

    lateinit var playerX: String
    lateinit var playerO: String

    private val gameStateStart: GameState = listOf(listOf(0,0,0), listOf(0,0,0), listOf(0,0,0))

    fun createGame() {
        GameService.createGame(player, gameStateStart) { game: Game?, error: Int? ->
            if (error != null) {
                //TODO give response to given error code
            } else {
                // TODO Game is created. What to do?
                gameId = game?.gameId.toString()
                println(game?.gameId)
                gameState = gameStateStart
                println("gameState: $gameState")
            }
        }
    }

    fun joinGame() {
        GameService.joinGame(player, gameId) { game: Game?, error: Int? ->
            if (error != null) {
                //TODO give response to given error code
            } else {
                //TODO
                val players = game?.players.toString()
                println("Players: $players")
            }
        }
    }

    fun updateGame() {

    }

    fun pollGame() {
        //TODO kjør hvis gamestate har endret seg
    }
}