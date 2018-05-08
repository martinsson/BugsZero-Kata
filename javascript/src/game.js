var Game = /** @class */ (function () {
    function Game() {
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
        for (var i = 0; i < 50; i++) {
            this.popQuestions.push("Pop Question " + i);
            this.scienceQuestions.push("Science Question " + i);
            this.sportsQuestions.push("Sports Question " + i);
            this.rockQuestions.push(this.createRockQuestion(i));
        }
    }
    Game.prototype.add = function (playerName) {
        this.players.push(playerName);
        this.places[this.howManyPlayers() - 1] = 0;
        this.purses[this.howManyPlayers() - 1] = 0;
        this.inPenaltyBox[this.howManyPlayers() - 1] = false;
        console.log(playerName + " was added");
        console.log("They are player number " + this.players.length);
        return true;
    };
    ;
    Game.prototype.howManyPlayers = function () {
        return this.players.length;
    };
    ;
    Game.prototype.didPlayerWin = function () {
        return !(this.purses[this.currentPlayer] == 6);
    };
    ;
    Game.prototype.currentCategory = function () {
        var categories = ['Pop', 'Science', 'Sports', 'Rock'];
        var categoryIndex = this.places[this.currentPlayer] % categories.length;
        return categories[categoryIndex];
    };
    ;
    Game.prototype.createRockQuestion = function (index) {
        return "Rock Question " + index;
    };
    ;
    Game.prototype.isPlayable = function (howManyPlayers) {
        return howManyPlayers >= 2;
    };
    ;
    Game.prototype.askQuestion = function () {
        if (this.currentCategory() == 'Pop') {
            console.log(this.popQuestions.shift());
        }
        if (this.currentCategory() == 'Science') {
            console.log(this.scienceQuestions.shift());
        }
        if (this.currentCategory() == 'Sports') {
            console.log(this.sportsQuestions.shift());
        }
        if (this.currentCategory() == 'Rock') {
            console.log(this.rockQuestions.shift());
        }
    };
    ;
    Game.prototype.roll = function (roll) {
        console.log(this.players[this.currentPlayer] + " is the current player");
        console.log("They have rolled a " + roll);
        if (this.inPenaltyBox[this.currentPlayer]) {
            if (roll % 2 != 0) {
                this.isGettingOutOfPenaltyBox = true;
                console.log(this.players[this.currentPlayer] + " is getting out of the penalty box");
                this._movePlayerAndAskQuestion(roll);
            }
            else {
                console.log(this.players[this.currentPlayer] + " is not getting out of the penalty box");
                this.isGettingOutOfPenaltyBox = false;
            }
        }
        else {
            this._movePlayerAndAskQuestion(roll);
        }
    };
    ;
    Game.prototype._movePlayerAndAskQuestion = function (roll) {
        this.places[this.currentPlayer] = this.places[this.currentPlayer] + roll;
        if (this.places[this.currentPlayer] > 11) {
            this.places[this.currentPlayer] = this.places[this.currentPlayer] - 12;
        }
        console.log(this.players[this.currentPlayer] + "'s new location is " + this.places[this.currentPlayer]);
        console.log("The category is " + this.currentCategory());
        this.askQuestion();
    };
    Game.prototype._doAnswerCorrectly = function () {
        console.log('Answer was correct!!!!');
        this.currentPlayer += 1;
        if (this.currentPlayer == this.players.length)
            this.currentPlayer = 0;
        this.purses[this.currentPlayer] += 1;
        console.log(this.players[this.currentPlayer] + " now has " +
            this.purses[this.currentPlayer] + " Gold Coins.");
        var winner = this.didPlayerWin();
        return winner;
    };
    Game.prototype.wasCorrectlyAnswered = function () {
        if (this.inPenaltyBox[this.currentPlayer]) {
            if (this.isGettingOutOfPenaltyBox) {
                return this._doAnswerCorrectly();
            }
            else {
                this.currentPlayer += 1;
                if (this.currentPlayer == this.players.length)
                    this.currentPlayer = 0;
                return true;
            }
        }
        else {
            return this._doAnswerCorrectly();
        }
    };
    ;
    Game.prototype.wrongAnswer = function () {
        console.log('Question was incorrectly answered');
        console.log(this.players[this.currentPlayer] + " was sent to the penalty box");
        this.inPenaltyBox[this.currentPlayer] = true;
        this.currentPlayer += 1;
        if (this.currentPlayer == this.players.length)
            this.currentPlayer = 0;
        return true;
    };
    ;
    return Game;
}());
module.exports = Game;
