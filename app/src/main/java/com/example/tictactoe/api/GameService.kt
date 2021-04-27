package com.example.tictactoe.api

import android.util.Log
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.tictactoe.App
import com.example.tictactoe.R
import com.example.tictactoe.api.data.Game
import com.example.tictactoe.api.data.GameState
import com.google.gson.Gson
import org.json.JSONObject

typealias GameServiceCallback = (state: Game?, errorCode: Int?) -> Unit

/*  NOTE:
    Using object expression to make GameService a Singleton.
    Why? Because there should only be one active GameService ever.
*/

object GameService {

    // NOTE: Do not want to have App.context all over the code. Also it is nice if we later want to support different contexts
    val context = App.context

    // NOTE: God practice to use a que for performing requests.
    // Instantiate the RequestQueue.
    private val requestQueue: RequestQueue = Volley.newRequestQueue(context)

    // NOTE: One possible way of constructing a list of API url.
    // You want to construct the urls so that you can support different environments (i.e. Debug, Test, Prod etc)
    private enum class APIEndPoints(val url: String) {
        CREATE_GAME("%1s%2s%3s".format(
            context.getString(R.string.protocol),
            context.getString(R.string.domain),
            context.getString(R.string.base_path))),

        JOIN_GAME("%1s%2s%3s%4s".format(
            context.getString(R.string.protocol),
            context.getString(R.string.domain),
            context.getString(R.string.base_path),
            context.getString(R.string.join_game_path))),

        UPDATE_GAME("%1s%2s%3s%4s".format(
            context.getString(R.string.protocol),
            context.getString(R.string.domain),
            context.getString(R.string.base_path),
            context.getString(R.string.update_game_path))),

        POLL_GAME("%1s%2s%3s%4s".format(
            context.getString(R.string.protocol),
            context.getString(R.string.domain),
            context.getString(R.string.base_path),
            context.getString(R.string.poll_game_path)))
    }

    fun createGame(playerId: String, gameState: GameState, callback: GameServiceCallback) {
        val url = APIEndPoints.CREATE_GAME.url

        val requestData = JSONObject()
        requestData.put("player", playerId)
        requestData.put("state", gameState)

        val request = object : JsonObjectRequest(Method.POST, url, requestData,
            {
                val game = Gson().fromJson(it.toString(0), Game::class.java)

                println(it.toString(0))
                val gid = game.gameId
                println("GameId: $gid")
                println(game)

                callback(game, null)
                Log.d("GameService: createGame()", "Game successfully created")
            }, {

                callback(null, it.networkResponse.statusCode)
                Log.d("GameService: createGame()", "Error creating new game")
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

    }

    fun updateGame(gameId: String, gameState: GameState, callback: GameServiceCallback) {

    }

    fun pollGame(gameId: String, callback: GameServiceCallback) {

    }
}