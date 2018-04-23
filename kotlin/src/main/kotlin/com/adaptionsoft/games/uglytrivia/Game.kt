package com.adaptionsoft.games.uglytrivia

import java.util.LinkedList

class Game {
     var players = mutableListOf<String>()
     var places = IntArray(6)
     var purses = IntArray(6)
     var inPenaltyBox = BooleanArray(6)

     var popQuestions = mutableListOf<String>()
     var scienceQuestions = mutableListOf<String>()
     var sportsQuestions = mutableListOf<String>()
     var rockQuestions = mutableListOf<String>()

     var currentPlayer = 0
     var isGettingOutOfPenaltyBox: Boolean = false

    val isPlayable: Boolean
        get() = howManyPlayers() >= 2

    init {
        for (i in 0..49) {
            popQuestions.addLast("Pop Question " + i)
            scienceQuestions.addLast("Science Question " + i)
            sportsQuestions.addLast("Sports Question " + i)
            rockQuestions.addLast(createRockQuestion(i))
        }
    }

    fun createRockQuestion(index: Int): String {
        return "Rock Question " + index
    }

    fun add(playerName: String): Boolean {


        players.add(playerName)
        places[howManyPlayers()] = 0
        purses[howManyPlayers()] = 0
        inPenaltyBox[howManyPlayers()] = false

        println(playerName + " was added")
        println("They are player number " + players.size)
        return true
    }

    fun howManyPlayers(): Int {
        return players.size
    }

    fun roll(roll: Int) {
        println(players.get(currentPlayer) + " is the current player")
        println("They have rolled a " + roll)

        if (inPenaltyBox[currentPlayer]) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true

                println(players.get(currentPlayer) + " is getting out of the penalty box")
                movePlayerAndAskQuestion(roll)
            } else {
                println(players.get(currentPlayer) + " is not getting out of the penalty box")
                isGettingOutOfPenaltyBox = false
            }

        } else {

            movePlayerAndAskQuestion(roll)
        }

    }

    private fun movePlayerAndAskQuestion(roll: Int) {
        places[currentPlayer] = places[currentPlayer] + roll
        if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12

        println(players.get(currentPlayer)
                + "'s new location is "
                + places[currentPlayer])
        println("The category is " + currentCategory())
        askQuestion()
    }

    private fun askQuestion() {
        if (currentCategory() === "Pop")
            println(popQuestions.removeFirst())
        if (currentCategory() === "Science")
            println(scienceQuestions.removeFirst())
        if (currentCategory() === "Sports")
            println(sportsQuestions.removeFirst())
        if (currentCategory() === "Rock")
            println(rockQuestions.removeFirst())
    }


    private fun currentCategory(): String {
        if (places[currentPlayer] == 0) return "Pop"
        if (places[currentPlayer] == 4) return "Pop"
        if (places[currentPlayer] == 8) return "Pop"
        if (places[currentPlayer] == 1) return "Science"
        if (places[currentPlayer] == 5) return "Science"
        if (places[currentPlayer] == 9) return "Science"
        if (places[currentPlayer] == 2) return "Sports"
        if (places[currentPlayer] == 6) return "Sports"
        return if (places[currentPlayer] == 10) "Sports" else "Rock"
    }

    fun wasCorrectlyAnswered(): Boolean {
        if (inPenaltyBox[currentPlayer]) {
            if (isGettingOutOfPenaltyBox) {
                println("Answer was correct!!!!")
                currentPlayer++
                if (currentPlayer == players.size) currentPlayer = 0
                purses[currentPlayer]++
                println(players.get(currentPlayer)
                        + " now has "
                        + purses[currentPlayer]
                        + " Gold Coins.")

                return didPlayerWin()
            } else {
                currentPlayer++
                if (currentPlayer == players.size) currentPlayer = 0
                return true
            }


        } else {

            println("Answer was corrent!!!!")
            purses[currentPlayer]++
            println(players.get(currentPlayer)
                    + " now has "
                    + purses[currentPlayer]
                    + " Gold Coins.")

            val winner = didPlayerWin()
            currentPlayer++
            if (currentPlayer == players.size) currentPlayer = 0

            return winner
        }
    }

    fun wrongAnswer(): Boolean {
        println("Question was incorrectly answered")
        println(players.get(currentPlayer) + " was sent to the penalty box")
        inPenaltyBox[currentPlayer] = true

        currentPlayer++
        if (currentPlayer == players.size) currentPlayer = 0
        return true
    }


    private fun didPlayerWin(): Boolean {
        return purses[currentPlayer] != 6
    }
}

fun MutableList<String>.removeFirst(): String {
    return this.removeAt(0)
}
fun MutableList<String>.addLast(element: String) {
    this.add(element)
}