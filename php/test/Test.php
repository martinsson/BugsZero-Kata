<?php

use Game\Game;
use Game\GameRunner;
use Game\Player;
use Game\PlayersList;
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

    public function testDisplayCorrectPlayersNumbersWhenPlayersLeave() {
        $game = new Game();
        $game->addPlayer( new Player('Etienne') );
        $game->addPlayer(new Player('Esmeralda'));
        $game->addPlayer(new Player('Bigsby'));

        ob_flush();
        ob_start();
        $game->removePlayer(1);
        $output = ob_end_clean();

        $this->assertEquals( '', $output );
    }
}
