const Game = require('./game');

module.exports = function gameRunner(randomInt) {

    let notAWinner = false;

    const game = new Game();

    game.add('Chet');
    game.add('Pat');
    game.add('Sue');

    do {

        game.roll(randomInt(6));

        if (randomInt(10) == 7) {
            notAWinner = game.wrongAnswer();
        } else {
            notAWinner = game.wasCorrectlyAnswered();
        }

    } while (notAWinner);
};

