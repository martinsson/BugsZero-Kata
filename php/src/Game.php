<?php
namespace Game;
function echoln($string) {
  echo $string."\n";
}

class Game {
    var $players;
    var $places;
    var $purses ;
    var $inPenaltyBox ;

    var $popQuestions;
    var $scienceQuestions;
    var $sportsQuestions;
    var $rockQuestions;

    var $currentPlayer = 0;
    var $isGettingOutOfPenaltyBox;

    function  __construct(){

   	$this->players = array();
        $this->places = array(0);
        $this->purses  = array(0);
        $this->inPenaltyBox  = array(0);

        $this->popQuestions = array();
        $this->scienceQuestions = array();
        $this->sportsQuestions = array();
        $this->rockQuestions = array();

        for ($i = 0; $i < 50; $i++) {
			array_push($this->popQuestions, "Pop Question " . $i);
			array_push($this->scienceQuestions, ("Science Question " . $i));
			array_push($this->sportsQuestions, ("Sports Question " . $i));
			array_push($this->rockQuestions, $this->createRockQuestion($i));
    	}
    }

    function createRockQuestion($index){
		return "Rock Question " . $index;
	}

    function isPlayable() {
		return ($this->howManyPlayers() >= 2);
	}

    function add($player) {
	    array_push($this->players,$player);

	    echoln($player->getName() . " was added");
	    echoln("They are player number " . count($this->players));
		return true;
	}

    function remove($playerNumber) {
        $player = $this->players[$playerNumber];
        array_splice($this->players, $playerNumber, 1);

        echoln( $player->getName() . " leaved");
        for( $i = 0; $i < $this->howManyPlayers(); $i++ ) {
            echoln($this->players[$i]->getName() . " is now number " . $i );
        }
    }

    function howManyPlayers() {
		return count($this->players);
	}

    public function getNameForCurrentPlayer() {
        return $this->getCurrentPlayer()->getName();
    }

    public function getPursesForCurrentPlayer() {
        return $this->getCurrentPlayer()->getCoins();
    }

    public function addPurseForCurrentPlayer()
    {
        $this->getCurrentPlayer()->addCoin();
    }

    public function isCurrentPlayerInPenaltyBox() {
        return $this->getCurrentPlayer()->isInPenaltyBox();
    }

    public function setCurrentPlayerInPenaltyBox() {
        $this->getCurrentPlayer()->setInPenaltyBox();
    }

    public function getPlaceForCurrentPlayer() {
        return $this->getCurrentPlayer()->getPlace();
    }

    public function movePlayerBy($roll)
    {
        $this->getCurrentPlayer()->moveBy($roll);
    }


    function  roll($roll) {
		echoln($this->getNameForCurrentPlayer() . " is the current player");
		echoln("They have rolled a " . $roll);

		if ($this->isCurrentPlayerInPenaltyBox()) {
			if ($roll % 2 != 0) {
				$this->isGettingOutOfPenaltyBox = true;

				echoln($this->getNameForCurrentPlayer() . " is getting out of the penalty box");
			    $this->movePlayerBy($roll);

				echoln($this->getNameForCurrentPlayer()
						. "'s new location is "
						. $this->getPlaceForCurrentPlayer());
				echoln("The category is " . $this->currentCategory());
				$this->askQuestion();
			} else {
				echoln($this->getNameForCurrentPlayer() . " is not getting out of the penalty box");
				$this->isGettingOutOfPenaltyBox = false;
				}

		} else {

            $this->movePlayerBy($roll);

            echoln($this->getNameForCurrentPlayer()
					. "'s new location is "
					. $this->getPlaceForCurrentPlayer());
			echoln("The category is " . $this->currentCategory());
			$this->askQuestion();
		}

	}

    function  askQuestion() {
		if ($this->currentCategory() == "Pop")
			echoln(array_shift($this->popQuestions));
		if ($this->currentCategory() == "Science")
			echoln(array_shift($this->scienceQuestions));
		if ($this->currentCategory() == "Sports")
			echoln(array_shift($this->sportsQuestions));
		if ($this->currentCategory() == "Rock")
			echoln(array_shift($this->rockQuestions));
	}

    function currentCategory() {
		if ($this->getPlaceForCurrentPlayer() == 0) return "Pop";
		if ($this->getPlaceForCurrentPlayer() == 4) return "Pop";
		if ($this->getPlaceForCurrentPlayer() == 8) return "Pop";
		if ($this->getPlaceForCurrentPlayer() == 1) return "Science";
		if ($this->getPlaceForCurrentPlayer() == 5) return "Science";
		if ($this->getPlaceForCurrentPlayer() == 9) return "Science";
		if ($this->getPlaceForCurrentPlayer() == 2) return "Sports";
		if ($this->getPlaceForCurrentPlayer() == 6) return "Sports";
		if ($this->getPlaceForCurrentPlayer() == 10) return "Sports";
		return "Rock";
	}


    function wasCorrectlyAnswered() {
		if ($this->isCurrentPlayerInPenaltyBox()){
			if ($this->isGettingOutOfPenaltyBox) {
				echoln("Answer was correct!!!!");
			$this->addPurseForCurrentPlayer();
				echoln($this->getNameForCurrentPlayer()
						. " now has "
						. $this->getPursesForCurrentPlayer()
						. " Gold Coins.");

				$winner = $this->didPlayerWin();
                $this->nextPlayerTurn();

                return $winner;
			} else {
                $this->nextPlayerTurn();
                return true;
			}



		} else {

			echoln("Answer was corrent!!!!");
		    $this->addPurseForCurrentPlayer();
			echoln($this->getNameForCurrentPlayer()
					. " now has "
					. $this->getPursesForCurrentPlayer()
					. " Gold Coins.");

			$winner = $this->didPlayerWin();
            $this->nextPlayerTurn();

            return $winner;
		}
	}

    function wrongAnswer(){
		echoln("Question was incorrectly answered");
		echoln($this->getNameForCurrentPlayer() . " was sent to the penalty box");
	    $this->setCurrentPlayerInPenaltyBox();

        $this->nextPlayerTurn();
        return true;
	}

    function didPlayerWin() {
		return !($this->getPursesForCurrentPlayer() == 6);
	}

    public function nextPlayerTurn()
    {
        $this->currentPlayer++;
        if ($this->currentPlayer == count($this->players)) $this->currentPlayer = 0;
    }

    /**
     * @return mixed
     */
    public function getCurrentPlayer()
    {
        return $this->players[$this->currentPlayer];
    }
}
