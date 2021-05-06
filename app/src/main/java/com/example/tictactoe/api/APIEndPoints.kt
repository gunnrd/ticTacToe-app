package com.example.tictactoe.api

import com.example.tictactoe.R

object APIEndPoints {

    var currentGameId: String? = null

    fun createGameUrl(): String {
        return "%1s%2s%3s".format(
            GameService.context.getString(R.string.protocol),
            GameService.context.getString(R.string.domain),
            GameService.context.getString(R.string.base_path))
    }

    fun joinGameUrl(): String{

        if(currentGameId == null){
            throw Exception("Current game id is not set")
        }

        return "%1s%2s%3s%4s".format(
            GameService.context.getString(R.string.protocol),
            GameService.context.getString(R.string.domain),
            GameService.context.getString(R.string.base_path),
            GameService.context.getString(R.string.join_game_path)
                .format(currentGameId))
    }

    fun updateGame(): String {
        return "%1s%2s%3s%4s".format(
            GameService.context.getString(R.string.protocol),
            GameService.context.getString(R.string.domain),
            GameService.context.getString(R.string.base_path),
            GameService.context.getString(R.string.update_game_path)
                .format(currentGameId))
    }

    fun pollGame(): String {
        return "%1s%2s%3s%4s".format(
            GameService.context.getString(R.string.protocol),
            GameService.context.getString(R.string.domain),
            GameService.context.getString(R.string.base_path),
            GameService.context.getString(R.string.poll_game_path)
                .format(currentGameId))
    }
}