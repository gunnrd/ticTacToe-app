package com.example.tictactoe.api

import com.example.tictactoe.R
import com.example.tictactoe.api.GameService.context

object APIEndPoints {

    var currentGameId: String? = null

    fun createGameUrl(): String {
        return "%1s%2s%3s".format(
            context.getString(R.string.protocol),
            context.getString(R.string.domain),
            context.getString(R.string.base_path))
    }

    fun joinGameUrl(): String{

        if(currentGameId == null){
            throw Exception("Current game id is not set")
        }

        return "%1s%2s%3s%4s".format(
            context.getString(R.string.protocol),
            context.getString(R.string.domain),
            context.getString(R.string.base_path),
            context.getString(R.string.join_game_path)
                .format(currentGameId))
    }

    fun updateGame(): String {
        return "%1s%2s%3s%4s".format(
            context.getString(R.string.protocol),
            context.getString(R.string.domain),
            context.getString(R.string.base_path),
            context.getString(R.string.update_game_path)
                .format(currentGameId))
    }

    fun pollGame(): String {
        return "%1s%2s%3s%4s".format(
            context.getString(R.string.protocol),
            context.getString(R.string.domain),
            context.getString(R.string.base_path),
            context.getString(R.string.poll_game_path)
                .format(currentGameId))
    }
}