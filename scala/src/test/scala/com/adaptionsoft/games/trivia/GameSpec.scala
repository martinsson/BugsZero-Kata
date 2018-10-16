package com.adaptionsoft.games.trivia

import java.io.{ByteArrayOutputStream, PrintStream}
import java.util.Random

import com.adaptionsoft.games.trivia.runner.GameRunner
import org.scalatest.{FunSpec, Matchers}

import scala.io.Source

class GameSpec extends FunSpec with Matchers {
  describe("Game") {
    it("should should output expected values") {
      val randomizer = new Random(123455)
      val resultStream = new ByteArrayOutputStream

      Console.withOut(new PrintStream(resultStream)) {
        (1 until 15).foreach(_ => GameRunner.playGame(randomizer))
      }

      val expected = Source.fromFile("../java/src/test/java/com/adaptionsoft/games/trivia/GameTest.itsLockedDown.approved.txt").mkString
      val result = resultStream.toString
      result shouldBe expected
    }
  }
}
