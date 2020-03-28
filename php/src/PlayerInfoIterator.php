<?php


namespace Game;


use Iterator;

class PlayerInfoIterator implements Iterator
{

    private $playersInfo = array();
    private $currentPlayerNumber = 0;
    public function __construct($players) {
        for($i = 0; $i < count( $players ); $i++) {
            $this->playersInfo[] = [
                'name'   => $players[$i]->getName(),
                'number' => $i
            ];
        }
    }

    public function current()
    {
        return $this->playersInfo[$this->currentPlayerNumber];
    }

    public function next()
    {
        return $this->currentPlayerNumber++;
    }

    public function key()
    {
        return $this->currentPlayerNumber;
    }

    public function valid()
    {
        return $this->currentPlayerNumber < count($this->playersInfo);
    }

    public function rewind()
    {
        $this->currentPlayerNumber = 0;
    }


}