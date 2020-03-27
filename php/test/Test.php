<?php

use Game\Game;
use Game\GameRunner;
use PHPUnit\Framework\TestCase;

class GameTest extends TestCase
{


    public function testLockDownr()
    {
        srand(123455);
        ob_start();

        GameRunner::runGame();

        $actual = ob_get_contents();
        ob_end_clean();

        $expected = file_get_contents(__DIR__ . '/approved.txt');
        $this->assertEquals($expected, $actual);

    }

    public function testCorrectNumberOfPlayersWhenPlayersAdded() {
        $game = new Game();
        $game->add('Kristof');
        $game->add('Julio');
        $game->add('Soraya');

        $this->assertEquals( 3, $game->howManyPlayers());

        return $game;
    }

    /**
     * @depends testCorrectNumberOfPlayersWhenPlayersAdded
     *
     * @param Game $game
     */
    public function testCorrectNumberOfPlayersWhenPlayerRemoved($game)
    {
        $game->remove(1);

        $this->assertEquals(2, $game->howManyPlayers());
    }

    public function testPlayerKeepPursesWhenOtherPlayerLeaves() {
        $game = $this->setGameFor('Katrin', 'Seraphin', 'Constantine');
        $this->runRoundsDeterminastically($game);

        $katrinPurses = $game->purses[0];
        $constantinePurses = $game->purses[2];

        $game->remove(1);

        $this->assertEquals( $katrinPurses, $game->purses[0] );
        $this->assertEquals( $constantinePurses, $game->purses[1] );
    }

    public function testPlayerKeepPlaceWhenOtherPlayerLeaves() {
        $game = $this->setGameFor('Katrin', 'Seraphin', 'Constantine' );
        $this->runRoundsDeterminastically($game);

        $katrinPlace = $game->places[0];
        $constantinePlace = $game->places[2];

        $game->remove(1);

        $this->assertEquals( $katrinPlace, $game->places[0] );
        $this->assertEquals( $constantinePlace, $game->places[1] );
    }

    public function testPlayerStaysInPenaltyBoxWhenOtherPlayerLeaves() {
        $game = $this->setGameFor('Katrin', 'Seraphin', 'Constantine');
        $this->runRoundsDeterminastically($game);

        $katrinInPenaltyBox = $game->inPenaltyBox[0];
        $constantineInPenaltyBox = $game->inPenaltyBox[2];

        $game->remove(1);

        $this->assertEquals( $katrinInPenaltyBox, $game->inPenaltyBox[0] );
        $this->assertEquals( $constantineInPenaltyBox, $game->inPenaltyBox[1] );
    }

    /**
     * @return Game
     */
    public function setGameFor( ...$players )
    {
        $game = new Game();

        foreach( $players as $playerName ) {
            $game->add($playerName);
        };

        return $game;
    }

    /**
     * @param Game $game
     */
    public function runRoundsDeterminastically(Game $game)
    {
        srand(123455);
        GameRunner::runRounds($game, 12);
    }
}
