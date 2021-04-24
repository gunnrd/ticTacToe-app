package com.example.tictactoe.api

import android.util.Log
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.tictactoe.App
import com.example.tictactoe.R
import com.example.tictactoe.api.data.Game
import com.example.tictactoe.api.data.GameState
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
            context.getString(R.string.base_path)))
    }

    // POST
    fun createGame(playerId: String, state: GameState, callback: GameServiceCallback?) {

    }


    fun joinGame(playerId: String, gameId: String, callback: (GameState) -> Unit) {

    }

    fun updateGame(gameId: String, gameState: GameState, callback: (GameState) -> Unit) {

    }

    fun pollGame(gameId: String, callback: (GameState) -> Unit) {

    }

}