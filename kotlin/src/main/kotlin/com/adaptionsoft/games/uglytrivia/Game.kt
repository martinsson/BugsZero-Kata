package com.adaptionsoft.games.uglytrivia

class Game {
     private val players = mutableListOf<String>()
     private val places = IntArray(6)
     private val purses = IntArray(6)
     private val inPenaltyBox = BooleanArray(6)

     private val popQuestions = mutableListOf<String>()
     private val scienceQuestions = mutableListOf<String>()
     private val sportsQuestions = mutableListOf<String>()
     private val rockQuestions = mutableListOf<String>()

     private var currentPlayer = 0
     private var isGettingOutOfPenaltyBox: Boolean = false

    val isPlayable: Boolean
        get() = howManyPlayers() >= 2

    init {
        for (i in 0..49) {
            popQuestions.addLast("Pop Question $i")
            scienceQuestions.addLast("Science Question $i")
            sportsQuestions.addLast("Sports Question $i")
            rockQuestions.addLast(createRockQuestion(i))
        }
    }

    private fun createRockQuestion(index: Int): String {
        return "Rock Question $index"
    }

    fun add(playerName: String): Boolean {


        players.add(playerName)
        places[howManyPlayers()] = 0
        purses[howManyPlayers()] = 0
        inPenaltyBox[howManyPlayers()] = false

        println("$playerName was added")
        println("They are player number " + players.size)
        return true
    }

    private fun howManyPlayers(): Int {
        return players.size
    }

    fun roll(roll: Int) {
        println(players[currentPlayer] + " is the current player")
        println("They have rolled a $roll")

        if (inPenaltyBox[currentPlayer]) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true

                println(players[currentPlayer] + " is getting out of the penalty box")
                movePlayerAndAskQuestion(roll)
            } else {
                println(players[currentPlayer] + " is not getting out of the penalty box")
                isGettingOutOfPenaltyBox = false
            }

        } else {

            movePlayerAndAskQuestion(roll)
        }

    }

    private fun movePlayerAndAskQuestion(roll: Int) {
        places[currentPlayer] = places[currentPlayer] + roll
        if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12

        println(players[currentPlayer]
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
                println(players[currentPlayer]
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
            println("Answer was correct!!!!")
            purses[currentPlayer]++
            println(players[currentPlayer]
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
        println(players[currentPlayer] + " was sent to the penalty box")
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
