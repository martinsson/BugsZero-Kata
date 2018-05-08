class Game {

    rockQuestions: any[];
    isGettingOutOfPenaltyBox: boolean;
    currentPlayer: number;
    sportsQuestions: any[];
    scienceQuestions: any[];
    inPenaltyBox: any[];
    popQuestions: any[];
    purses: any[];
    places: any[];
    players: any[];

    private questionsDeck;

    constructor() {
        this.players = new Array();
        this.places = new Array(6);
        this.purses = new Array(6);
        this.inPenaltyBox = new Array(6);

        this.popQuestions = new Array();
        this.scienceQuestions = new Array();
        this.sportsQuestions = new Array();
        this.rockQuestions = new Array();

        this.currentPlayer = 0;
        this.isGettingOutOfPenaltyBox = false;

        for (let i = 0; i < 50; i++) {
            this.popQuestions.push("Pop Question " + i);
            this.scienceQuestions.push("Science Question " + i);
            this.sportsQuestions.push("Sports Question " + i);
            this.rockQuestions.push(this.createRockQuestion(i));
        }
        this.questionsDeck = {
            Pop: this.popQuestions,
            Science: this.scienceQuestions,
            Sports: this.sportsQuestions,
            Rock: this.rockQuestions,
        };

    }

    add(playerName) {
        this.players.push(playerName);
        this.places[this.howManyPlayers() - 1] = 0;
        this.purses[this.howManyPlayers() - 1] = 0;
        this.inPenaltyBox[this.howManyPlayers() - 1] = false;

        console.log(playerName + " was added");
        console.log("They are player number " + this.players.length);

        return true;
    };

    howManyPlayers() {
        return this.players.length;
    };

    didPlayerWin() {
        return !(this.purses[this.currentPlayer] == 6)
    };

    /**
     * This code expresses the business rules that categories
     *   - are evenly distributed over the board
     *   - are equally frequent
     * The fact that currentCategory() and askQuestion() use the same data - questionsDeck
     * instead of a random collection of if-statements makes adds the necessary cohesion
     * for introducing a new category for instance.
     *
     * A key aspect is the use of a data structure (questionsDeck) that models the Domain well,
     * indeed often when we replace conditional logic with a data-structure (List, Set, Map, Maybe)
     * we gain cohesion
     */
    currentCategory() {
        const categoryNames = Object.keys(this.questionsDeck);
        let categoryIndex = this.places[this.currentPlayer] % categoryNames.length;
        return categoryNames[categoryIndex]
    };

    askQuestion() {
        let currentCategory = this.currentCategory();
        let questions = this.questionsDeck[currentCategory];
        console.log(questions.shift());
    };

    createRockQuestion(index) {
        return "Rock Question " + index;
    };


    isPlayable(howManyPlayers) {
        return howManyPlayers >= 2;
    };

    roll(roll) {
        console.log(this.players[this.currentPlayer] + " is the current player");
        console.log("They have rolled a " + roll);

        if (this.inPenaltyBox[this.currentPlayer]) {
            if (roll % 2 != 0) {
                this.isGettingOutOfPenaltyBox = true;

                console.log(this.players[this.currentPlayer] + " is getting out of the penalty box");
                this._movePlayerAndAskQuestion(roll);
            } else {
                console.log(this.players[this.currentPlayer] + " is not getting out of the penalty box");
                this.isGettingOutOfPenaltyBox = false;
            }
        } else {

            this._movePlayerAndAskQuestion(roll);
        }
    };

    _movePlayerAndAskQuestion(roll) {
        this.places[this.currentPlayer] = this.places[this.currentPlayer] + roll;
        if (this.places[this.currentPlayer] > 11) {
            this.places[this.currentPlayer] = this.places[this.currentPlayer] - 12;
        }

        console.log(this.players[this.currentPlayer] + "'s new location is " + this.places[this.currentPlayer]);
        console.log("The category is " + this.currentCategory());
        this.askQuestion();
    }

    _doAnswerCorrectly() {
        console.log('Answer was correct!!!!');
        this.currentPlayer += 1;
        if (this.currentPlayer == this.players.length)
            this.currentPlayer = 0;

        this.purses[this.currentPlayer] += 1;
        console.log(this.players[this.currentPlayer] + " now has " +
            this.purses[this.currentPlayer] + " Gold Coins.");

        var winner = this.didPlayerWin();

        return winner;
    }

    wasCorrectlyAnswered() {
        if (this.inPenaltyBox[this.currentPlayer]) {
            if (this.isGettingOutOfPenaltyBox) {
                return this._doAnswerCorrectly();
            } else {
                this.currentPlayer += 1;
                if (this.currentPlayer == this.players.length)
                    this.currentPlayer = 0;
                return true;
            }


        } else {

            return this._doAnswerCorrectly();
        }
    };

    wrongAnswer() {
        console.log('Question was incorrectly answered');
        console.log(this.players[this.currentPlayer] + " was sent to the penalty box");
        this.inPenaltyBox[this.currentPlayer] = true;

        this.currentPlayer += 1;
        if (this.currentPlayer == this.players.length)
            this.currentPlayer = 0;
        return true;
    };

}

module.exports = Game
