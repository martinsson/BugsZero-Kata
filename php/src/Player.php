<?php


namespace Game;


class Player
{
    private $name;
    private $place = 0;
    private $coins = 0;
    private $inPenaltyBox = false;

    /**
     * Player constructor.
     * @param $playerName
     */
    public function __construct($playerName)
    {
        $this->name = $playerName;
    }

    public function getName()
    {
        return $this->name;
    }

    public function getPlace()
    {
        return $this->place;
    }

    public function moveBy($number) {
        $this->place = $this->place + $number;
        if ($this->place > 11) $this->place = $this->place - 12;
    }

    public function getCoins()
    {
        return $this->coins;
    }

    public function addCoin() {
        $this->coins++;
    }

    public function isInPenaltyBox() {
        return $this->inPenaltyBox;
    }

    public function setInPenaltyBox() {
        $this->inPenaltyBox = true;
    }



}