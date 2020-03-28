<?php

use Game\Game;
use Game\GameRunner;
use Game\Player;
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
        $game = $this->setGameForPlayers(
            new Player( 'Kristof' ),
            new Player( 'Julio' ),
            new Player( 'Soraya' )
        );

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

    public function testKeepCurrentPlayerWhenPlayerLeaves() {
        $game = $this->setGameForPlayers(
            new Player('Damien'),
            new Player('Aïcha'),
            new Player('Stéphanie')
        );

        GameRunner::runRound($game);

        $game->remove(0);

        $this->assertEquals( 'Aïcha', $game->getNameForCurrentPlayer());
    }

    public function testGetNextPlayerWhenCurrentPlayerLeaves() {
        $game = $this->setGameForPlayers(
            new Player('Camilla'),
            new Player('Ludwig'),
            new Player('Amine')
        );

        GameRunner::runRound($game);

        $game->remove(1);

        $this->assertEquals( 'Amine', $game->getNameForCurrentPlayer());
    }

    public function playerDataTurnTwelve() {
        return [
            'coins' => [
                'getCoins',
                [
                    'Katrin' => 4,
                    'Constantine' => 4
                ]
            ],
            'place' => [
                'getPlace',
                [
                    'Katrin' => 0,
                    'Constantine' => 8
                ]
            ],
            'inPenaltyBox' => [
                'isInPenaltyBox',
                [
                    'Katrin' => false,
                    'Constantine' => false
                ]
            ]
        ];
    }

    /**
     * @dataProvider playerDataTurnTwelve
     * @param string $getter
     * @param array  $expectedValueFor
     */
    public function testPlayersKeepDataWhenOtherPlayerLeaves( $getter, $expectedValueFor ) {
        $katrin = new Player('Katrin');
        $seraphin = new Player('Seraphin');
        $constantine = new Player('Constantine');
        $game = $this->setGameForPlayers($katrin, $seraphin, $constantine );
        $this->runRoundsDeterministically($game);

        $game->remove(1);

        $this->assertEquals( $expectedValueFor['Katrin'], $katrin->$getter() );
        $this->assertEquals( $expectedValueFor['Constantine'], $constantine->$getter() );
    }

    public function setGameForPlayers( ...$players )
    {
        $game = new Game();

        foreach( $players as $player ) {
            $game->add($player);
        };

        return $game;
    }

    /**
     * @param Game $game
     */
    public function runRoundsDeterministically(Game $game)
    {
        srand(123455);
        GameRunner::runRounds($game, 12);
    }
}
