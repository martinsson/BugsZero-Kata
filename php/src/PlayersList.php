<?php


namespace Game;

class PlayersList {

    private $players = array();
    private $currentPlayer;
    private $currentPlayerNumber = 0;

    public function __construct( ...$players ) {
        $this->players = $players;
    }

    function howManyPlayers()
    {
        return count($this->players);
    }

    public function add($player)
    {
        array_push($this->players, $player);
    }

    public function remove($playerNumber)
    {
        $player = $this->players[$playerNumber];
        array_splice($this->players, $playerNumber, 1);
        if ($player === $this->currentPlayer) {
            $this->currentPlayer = $this->players[$this->currentPlayerNumber];
        }
        return $player;
    }

    public function getCurrentPlayer()
    {
        if (!isset($this->currentPlayer)) {
            $this->currentPlayer = $this->players[$this->currentPlayerNumber];
        }
        return $this->currentPlayer;
    }

    public function nextPlayerTurn()
    {
        $this->currentPlayerNumber++;
        if ($this->currentPlayerNumber == count($this->players)) $this->currentPlayerNumber = 0;
        $this->currentPlayer = $this->players[$this->currentPlayerNumber];
    }

    public function getInfo() {
        return new PlayerInfoIterator($this->players);
    }
}