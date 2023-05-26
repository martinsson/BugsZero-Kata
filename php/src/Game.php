<?php
namespace Game;
function echoln($string) {
  echo $string."\n";
}

class Game {
    private $playersOnline;
    var $popQuestions;
    var $scienceQuestions;
    var $sportsQuestions;
    var $rockQuestions;

    var $isGettingOutOfPenaltyBox;

    function  __construct(){

        $this->playersOnline = new PlayersList();
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
		return ($this->playersOnline->howManyPlayers() >= 2);
	}

    function addPlayer($player) {
        $this->playersOnline->add($player);

        echoln($player->getName() . " was added");
	    echoln("They are player number " . $this->playersOnline->howManyPlayers());
		return true;
	}

    function removePlayer($playerNumber) {
        $player = $this->playersOnline->remove($playerNumber);

        echoln( $player->getName() . " leaved");
        foreach($this->playersOnline->getInfo() as $playerInfo) {
            echoln($playerInfo['name'] . " is now number " . $playerInfo['number'] );
        }
    }

    function  roll($roll) {
		echoln($this->playersOnline->getCurrentPlayer()->getName() . " is the current player");
		echoln("They have rolled a " . $roll);

		if ($this->playersOnline->getCurrentPlayer()->isInPenaltyBox()) {
			if ($roll % 2 != 0) {
				$this->isGettingOutOfPenaltyBox = true;

				echoln($this->playersOnline->getCurrentPlayer()->getName() . " is getting out of the penalty box");
			    $this->playersOnline->getCurrentPlayer()->moveBy($roll);

				echoln($this->playersOnline->getCurrentPlayer()->getName()
						. "'s new location is "
						. $this->playersOnline->getCurrentPlayer()->getPlace());
				echoln("The category is " . $this->currentCategory());
				$this->askQuestion();
			} else {
				echoln($this->playersOnline->getCurrentPlayer()->getName() . " is not getting out of the penalty box");
				$this->isGettingOutOfPenaltyBox = false;
				}

		} else {

            $this->playersOnline->getCurrentPlayer()->moveBy($roll);

            echoln($this->playersOnline->getCurrentPlayer()->getName()
					. "'s new location is "
					. $this->playersOnline->getCurrentPlayer()->getPlace());
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
		if ($this->playersOnline->getCurrentPlayer()->getPlace() == 0) return "Pop";
		if ($this->playersOnline->getCurrentPlayer()->getPlace() == 4) return "Pop";
		if ($this->playersOnline->getCurrentPlayer()->getPlace() == 8) return "Pop";
		if ($this->playersOnline->getCurrentPlayer()->getPlace() == 1) return "Science";
		if ($this->playersOnline->getCurrentPlayer()->getPlace() == 5) return "Science";
		if ($this->playersOnline->getCurrentPlayer()->getPlace() == 9) return "Science";
		if ($this->playersOnline->getCurrentPlayer()->getPlace() == 2) return "Sports";
		if ($this->playersOnline->getCurrentPlayer()->getPlace() == 6) return "Sports";
		if ($this->playersOnline->getCurrentPlayer()->getPlace() == 10) return "Sports";
		return "Rock";
	}


    function wasCorrectlyAnswered() {
		if ($this->playersOnline->getCurrentPlayer()->isInPenaltyBox()){
			if ($this->isGettingOutOfPenaltyBox) {
				echoln("Answer was correct!!!!");
			$this->playersOnline->getCurrentPlayer()->addCoin();
				echoln($this->playersOnline->getCurrentPlayer()->getName()
						. " now has "
						. $this->playersOnline->getCurrentPlayer()->getCoins()
						. " Gold Coins.");

				$winner = $this->didPlayerWin();
                $this->playersOnline->nextPlayerTurn();

                return $winner;
			} else {
                $this->playersOnline->nextPlayerTurn();
                return true;
			}



		} else {

			echoln("Answer was corrent!!!!");
		    $this->playersOnline->getCurrentPlayer()->addCoin();
			echoln($this->playersOnline->getCurrentPlayer()->getName()
					. " now has "
					. $this->playersOnline->getCurrentPlayer()->getCoins()
					. " Gold Coins.");

			$winner = $this->didPlayerWin();
            $this->playersOnline->nextPlayerTurn();

            return $winner;
		}
	}

    function wrongAnswer(){
		echoln("Question was incorrectly answered");
		echoln($this->playersOnline->getCurrentPlayer()->getName() . " was sent to the penalty box");
	    $this->playersOnline->getCurrentPlayer()->setInPenaltyBox();

        $this->playersOnline->nextPlayerTurn();
        return true;
	}

    function didPlayerWin() {
		return !($this->playersOnline->getCurrentPlayer()->getCoins() == 6);
	}

}
