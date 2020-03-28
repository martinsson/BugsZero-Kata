<?php

namespace Game;


use PHPUnit\Framework\TestCase;

class PlayersListTest extends TestCase
{
    public function testCorrectNumberOfPlayersWhenPlayersAdded() {
        $playersList = new PlayersList(
            new Player( 'Kristof' ),
            new Player( 'Julio' ),
            new Player( 'Soraya' )
        );

        $this->assertEquals( 3, $playersList->howManyPlayers());

        return $playersList;
    }

    /**
     * @depends testCorrectNumberOfPlayersWhenPlayersAdded
     *
     * @param PlayersList
     */
    public function testCorrectNumberOfPlayersWhenPlayerRemoved($playersList)
    {
        $playersList->remove(1);

        $this->assertEquals(2, $playersList->howManyPlayers());
    }

    public function setThreePlayersList()
    {
        $damien = new Player('Damien');
        $aicha = new Player('Aïcha');
        $stephanie = new Player('Stéphanie');
        $playersList = new PlayersList($damien, $aicha, $stephanie);
        return array($damien, $aicha, $stephanie, $playersList);
    }

    public function testKeepCurrentPlayerWhenPlayerLeaves() {
        list($damien, $aicha, $stephanie, $playersList) = $this->setThreePlayersList();

        $playersList->nextPlayerTurn();

        $playersList->remove(0);

        $this->assertEquals( $aicha, $playersList->getCurrentPlayer());
    }

    public function testGetNextPlayerWhenCurrentPlayerLeaves() {
        list($damien, $aicha, $stephanie, $playersList) = $this->setThreePlayersList();

        $playersList->nextPlayerTurn();

        $playersList->remove(1);

        $this->assertEquals( $stephanie, $playersList->getCurrentPlayer());
    }
}
