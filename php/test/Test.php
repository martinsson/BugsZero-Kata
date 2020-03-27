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
    public function testCorrectNumberOfPlayersWhenPlayerRemoved($game) {
        $game->remove(1);

        $this->assertEquals( 2, $game->howManyPlayers());
    }
}
