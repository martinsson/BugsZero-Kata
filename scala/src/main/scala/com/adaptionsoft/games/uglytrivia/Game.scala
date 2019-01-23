package com.adaptionsoft.games.uglytrivia

import scala.collection.mutable

class Game() {
  val players = mutable.ListBuffer[String]()
  val places = new Array[Int](6)
  val purses = new Array[Int](6)
  val inPenaltyBox = new Array[Boolean](6)
  val popQuestions = mutable.ListBuffer[String]()
  val scienceQuestions = mutable.ListBuffer[String]()
  val sportsQuestions = mutable.ListBuffer[String]()
  val rockQuestions = mutable.ListBuffer[String]()
  var currentPlayer = 0
  var isGettingOutOfPenaltyBox: Boolean = false

  (0 until 50).foreach { i =>
    popQuestions.append("Pop Question " + i)
    scienceQuestions.append("Science Question " + i)
    sportsQuestions.append("Sports Question " + i)
    rockQuestions.append(createRockQuestion(i))
  }

  def createRockQuestion(index: Int): String = "Rock Question " + index

  def isPlayable: Boolean = howManyPlayers >= 2

  def add(playerName: String): Boolean = {
    players.append(playerName)
    places(howManyPlayers) = 0
    purses(howManyPlayers) = 0
    inPenaltyBox(howManyPlayers) = false
    println(playerName + " was added")
    println("They are player number " + players.size)
    true
  }

  def howManyPlayers: Int = players.size

  def roll(roll: Int): Unit = {
    println(players(currentPlayer) + " is the current player")
    println("They have rolled a " + roll)
    if (inPenaltyBox(currentPlayer)) if (roll % 2 != 0) {
      isGettingOutOfPenaltyBox = true
      println(players(currentPlayer) + " is getting out of the penalty box")
      movePlayerAndAskQuestion(roll)
    } else {
      println(players(currentPlayer) + " is not getting out of the penalty box")
      isGettingOutOfPenaltyBox = false
    } else
      movePlayerAndAskQuestion(roll)
  }

  private def movePlayerAndAskQuestion(roll: Int): Unit = {
    places(currentPlayer) = places(currentPlayer) + roll
    if (places(currentPlayer) > 11) places(currentPlayer) = places(currentPlayer) - 12
    println(players(currentPlayer) + "'s new location is " + places(currentPlayer))
    println("The category is " + currentCategory)
    askQuestion()
  }

  private def askQuestion(): Unit = {
    if (currentCategory eq "Pop") println(popQuestions.remove(0))
    if (currentCategory eq "Science") println(scienceQuestions.remove(0))
    if (currentCategory eq "Sports") println(sportsQuestions.remove(0))
    if (currentCategory eq "Rock") println(rockQuestions.remove(0))
  }

  private def currentCategory: String = {
    if (places(currentPlayer) == 0) return "Pop"
    if (places(currentPlayer) == 4) return "Pop"
    if (places(currentPlayer) == 8) return "Pop"
    if (places(currentPlayer) == 1) return "Science"
    if (places(currentPlayer) == 5) return "Science"
    if (places(currentPlayer) == 9) return "Science"
    if (places(currentPlayer) == 2) return "Sports"
    if (places(currentPlayer) == 6) return "Sports"
    if (places(currentPlayer) == 10) return "Sports"
    "Rock"
  }

  def wasCorrectlyAnswered: Boolean =
    if (inPenaltyBox(currentPlayer)) {
      if (isGettingOutOfPenaltyBox) {
        println("Answer was correct!!!!")
        currentPlayer += 1
        if (currentPlayer == players.size) currentPlayer = 0
        purses(currentPlayer) += 1
        println(players(currentPlayer) + " now has " + purses(currentPlayer) + " Gold Coins.")
        val winner = didPlayerWin
        winner
      } else {
        currentPlayer += 1
        if (currentPlayer == players.size) currentPlayer = 0
        true
      }
    } else {
      println("Answer was corrent!!!!")
      purses(currentPlayer) += 1
      println(players(currentPlayer) + " now has " + purses(currentPlayer) + " Gold Coins.")
      val winner = didPlayerWin
      currentPlayer += 1
      if (currentPlayer == players.size) currentPlayer = 0
      winner
    }

  def wrongAnswer: Boolean = {
    println("Question was incorrectly answered")
    println(players(currentPlayer) + " was sent to the penalty box")
    inPenaltyBox(currentPlayer) = true
    currentPlayer += 1
    if (currentPlayer == players.size) currentPlayer = 0
    true
  }


  private def didPlayerWin: Boolean = !(purses(currentPlayer) == 6)
}
