package com.example.tictactoe

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.tictactoe.api.GameService
import com.example.tictactoe.api.data.Game

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.tictactoe", appContext.packageName)
    }

    var gameState: Game? = null
    var gameId: String = ""
    private val firstPlayer: String = "Gunn"
    private val secondPlayer: String = "Renate"
    private val initState = listOf(listOf(0,0,0), listOf(0,0,0), listOf(0,0,0))

    @Test
    fun createGame() {
        GameService.createGame(firstPlayer, initState) { game: Game?, errorCode: Int? ->
            gameState = game
            gameId = game?.gameId.toString()
            assertNotNull(game)
            assertNotNull(game?.gameId)
            assertEquals(firstPlayer, game?.players?.get(0))
        }
    }

    @Test
    fun joinGame() {

        gameState?.let {
            GameService.joinGame(secondPlayer, gameId) { game: Game?, errorCode: Int? ->
                assertEquals(firstPlayer, game?.players?.get(0))
                assertEquals(secondPlayer, game?.players?.get(1))
            }
        }

    }
}