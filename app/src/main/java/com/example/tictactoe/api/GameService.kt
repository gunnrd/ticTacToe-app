package com.example.tictactoe.api

import android.util.Log
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.tictactoe.App
import com.example.tictactoe.GameManager
import com.example.tictactoe.R
import com.example.tictactoe.api.data.Game
import com.example.tictactoe.api.data.GameState
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject

typealias GameServiceCallback = (state: Game?, errorCode: Int?) -> Unit

object GameService {

    val context = App.context
    private val requestQueue: RequestQueue = Volley.newRequestQueue(context)
    private val TAG: String = "GameService"

    fun createGame(playerId: String, state: GameState, callback: GameServiceCallback) {
        val url = APIEndPoints.createGameUrl()
        val requestData = JSONObject()

        requestData.put("player", playerId)
        requestData.put("state", JSONArray(state))

        val request = object : JsonObjectRequest(
            Method.POST, url, requestData,
            {
                val game = Gson().fromJson(it.toString(0), Game::class.java)
                GameManager.gameId = game.gameId

                //TODO Remove print on line below
                println(game)

                callback(game, null)
                Log.d(TAG, "Game successfully created")
            }, {

                callback(null, it.networkResponse.statusCode)
                Log.d(TAG, "Error creating new game")
            }) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Content-Type"] = "application/json"
                headers["Game-Service-Key"] = context.getString(R.string.game_service_key)
                return headers
            }
        }

        requestQueue.add(request)
    }

    fun joinGame(playerId: String, gameId: String, callback: GameServiceCallback) {
        APIEndPoints.currentGameId = gameId
        val url = APIEndPoints.joinGameUrl()
        val requestData = JSONObject()

        requestData.put("player", playerId)

        val request = object : JsonObjectRequest(
            Method.POST, url, requestData,
            {
                val game = Gson().fromJson(it.toString(0), Game::class.java)

                //TODO Remove print on line below
                println(game)

                GameManager.playerTwo = game.players[1]
                GameManager.newState = game.state.flatten() as MutableList<String>

                callback(game, null)
                Log.d(TAG, "Game successfully joined")
            }, {

                callback(null, it.networkResponse.statusCode)
                Log.d(TAG, "Error joining new game")
            }) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Content-Type"] = "application/json"
                headers["Game-Service-Key"] = context.getString(R.string.game_service_key)
                return headers
            }
        }

        requestQueue.add(request)
    }

    fun updateGame(gameId: String, state: GameState, callback: GameServiceCallback) {
        APIEndPoints.currentGameId = gameId
        val url = APIEndPoints.updateGame()
        val requestData = JSONObject()

        requestData.put("gameId", gameId)
        requestData.put("state", JSONArray(state))

        val request = object : JsonObjectRequest(
            Method.POST, url, requestData,
            {
                val game = Gson().fromJson(it.toString(0), Game::class.java)

                callback(game, null)
                Log.d(TAG, "Game successfully updated")
            }, {

                callback(null, it.networkResponse.statusCode)
                Log.d(TAG, "Error updating game")
            }) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Content-Type"] = "application/json"
                headers["Game-Service-Key"] = context.getString(R.string.game_service_key)
                return headers
            }
        }

        requestQueue.add(request)
    }

    fun pollGame(gameId: String, callback: GameServiceCallback) {
        APIEndPoints.currentGameId = gameId
        val url = APIEndPoints.pollGame()
        val requestData = JSONObject()

        val request = object : JsonObjectRequest(
                Method.GET, url, requestData,
                {
                    val game = Gson().fromJson(it.toString(0), Game::class.java)

                    GameManager.state = game.state
                    GameManager.pollState = game.state.flatten() as MutableList<String>

                    if (game.players.size == 2) {
                        GameManager.playerTwo = game.players[1]
                    }

                    //TODO remove print on line below
                    println("Polled game state from server: ${game.state}")

                    callback(game, null)
                    //Log.d(TAG, "Poll game success")
                }, {

            callback(null, it.networkResponse.statusCode)
            println("Error polling game")
            Log.d(TAG, "Error polling game")
        }) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Content-Type"] = "application/json"
                headers["Game-Service-Key"] = context.getString(R.string.game_service_key)
                return headers
            }
        }

        requestQueue.add(request)
    }
}