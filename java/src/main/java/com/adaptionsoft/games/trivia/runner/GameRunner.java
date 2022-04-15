
package com.adaptionsoft.games.trivia.runner;
import java.util.Random;

import com.adaptionsoft.games.trivia.enums.PlayerEnum;
import com.adaptionsoft.games.uglytrivia.Game;


public class GameRunner {

	public static void main(String[] args) {
		Random rand = new Random();
		playGame(rand);
		
	}

	public static void playGame(Random rand) {
		Game aGame = new Game();

		aGame.add(PlayerEnum.CHET.getCode());
		aGame.add(PlayerEnum.PAT.getCode());
		aGame.add(PlayerEnum.SUE.getCode());
		
		// The boolean notAWinner has changed value. so we can't declare it as a constant
		boolean notAWinner;
		
		do {
			
			aGame.roll(rand.nextInt(5) + 1);

			if (rand.nextInt(9) == 7) {
				notAWinner = aGame.wrongAnswer();
			} else {
				notAWinner = aGame.wasCorrectlyAnswered();
			}



		} while (notAWinner);
	}
}
