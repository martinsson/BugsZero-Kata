<?php
namespace Game;

class GameRunner {

    public static function runGame()
    {

        $aGame = new Game();

        $aGame->addPlayer(new Player("Chet"));
        $aGame->addPlayer(new Player("Pat"));
        $aGame->addPlayer(new Player("Sue"));


        do {

            $notAWinner = self::runRound($aGame);


        } while ($notAWinner);
    }

    /**
     * @param Game $aGame
     * @return bool
     */
    public static function runRound(Game $aGame)
    {
        $aGame->roll(rand(0, 5) + 1);

        if (rand(0, 9) == 7) {
            $notAWinner = $aGame->wrongAnswer();
        } else {
            $notAWinner = $aGame->wasCorrectlyAnswered();
        }
        return $notAWinner;
    }

    public static function runRounds( $aGame, $number ) {
        $notAWinner = true;
        for ($i=0; $i < $number; $i++) {
            $notAWinner = ( $notAWinner && self::runRound($aGame) );
        }
        return $notAWinner;
    }
}



