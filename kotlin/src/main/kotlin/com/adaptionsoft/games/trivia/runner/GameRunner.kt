package com.adaptionsoft.games.trivia.runner

import java.util.Random

import com.adaptionsoft.games.uglytrivia.Game


object GameRunner {

    private var notAWinner: Boolean = false

    @JvmStatic
    fun main(args: Array<String>) {
        val rand = Random()
        playGame(rand)

    }

    fun playGame(rand: Random) {
        val aGame = Game()

        aGame.add("Chet")
        aGame.add("Pat")
        aGame.add("Sue")


        do {

            aGame.roll(rand.nextInt(5) + 1)

            if (rand.nextInt(9) == 7) {
                notAWinner = aGame.wrongAnswer()
            } else {
                notAWinner = aGame.wasCorrectlyAnswered()
            }


        } while (notAWinner)
    }
}
