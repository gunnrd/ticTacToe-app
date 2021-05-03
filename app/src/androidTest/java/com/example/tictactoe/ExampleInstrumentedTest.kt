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

    var state: Game? = null
    var gameId: String = ""
    private val playerOne: String = "Gunn"
    private val playerTwo: String = "Renate"
    private val initState = listOf(listOf("0","0","0"), listOf("0","0","0"), listOf("0","0","0"))
    private val newGameState = listOf(listOf("X","0","0"), listOf("X","0","0"), listOf("X","0","0"))

    @Test
    fun createGame() {
        GameService.createGame(playerOne, initState) { game: Game?, _: Int? ->
            state = game
            gameId = game?.gameId.toString()
            assertNotNull(game)
            assertNotNull(game?.gameId)
            assertEquals(initState, game?.state)
            assertEquals(playerOne, game?.players?.get(0))
        }
    }

    @Test
    fun joinGame() {

        state?.let {
            GameService.joinGame(playerOne, gameId) { game: Game?, _: Int? ->
                assertNotNull(game?.gameId)
                assertNotNull(game?.state)
                assertEquals(playerOne, game?.players?.get(0))
                assertEquals(playerTwo, game?.players?.get(1))
            }
        }
    }

    @Test
    fun updateGame() {
        GameService.updateGame(gameId, newGameState) { game: Game?, _: Int? ->
            assertNotNull(game?.gameId)
            assertNotNull(game?.state)
            assertEquals(newGameState, game?.state)
        }
    }

    @Test
    fun pollGame() {
        GameService.pollGame(gameId) { game: Game?, _: Int? ->
            assertNotNull(game?.gameId)
            assertNotNull(game?.state)
            assertEquals(playerOne, game?.players?.get(0))
            assertEquals(playerTwo, game?.players?.get(1))
        }
    }
}