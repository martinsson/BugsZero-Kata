package com.adaptionsoft.games.trivia


import com.adaptionsoft.games.trivia.runner.GameRunner
import org.approvaltests.Approvals
import org.junit.Test

import java.io.*
import java.util.Random
import java.util.stream.IntStream

class GameTest {

    @Test
    @Throws(Exception::class)
    fun itsLockedDown() {

        val randomizer = Random(123455)
        val resultStream = ByteArrayOutputStream()
        System.setOut(PrintStream(resultStream))

        IntStream.range(1, 15).forEach { i -> GameRunner.playGame(randomizer) }

        Approvals.verify(resultStream.toString())

    }
}
