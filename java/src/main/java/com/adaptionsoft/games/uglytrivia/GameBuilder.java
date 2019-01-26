package com.adaptionsoft.games.uglytrivia;


/**
 * This GameBuilder helps to create a game with 2-6 players.
 *
 * It prevents from creating a game with less than two players with its method {@see #createNewGame(String, String)}
 *
 * By implenting each interfaces 'AddXxxPlayer', it totally controls the way players are added.
 * Example:
 * Game aGame = GameBuilder.createNewGame("First player", "Second player") // -> returns an instance of {@see AddThirdPlayer}
 * 	.addPlayer("Third player") // -> returns an instance of {@see AddFourthPlayer}
 * 	.addPlayer("Fourth player") // -> returns an instance of {@see AddFifthPlayer}
 * 	.addPlayer("Fifth player") // -> returns an instance of {@see AddSixthPlayer}
 * 	.addPlayer("Sixth player") // -> returns an instance of {@see BuildingGame}
 */
public class GameBuilder implements BuildingGame.AddThirdPlayer,
        BuildingGame.AddFourthPlayer,
        BuildingGame.AddFifthPlayer,
        BuildingGame.AddSixthPlayer,
        BuildingGame {

    private Game game;

    public static AddThirdPlayer createNewGame(String firstPlayer, String secondPlayer) {
        GameBuilder builderGame = new GameBuilder();
        builderGame.game = new Game();
        builderGame.game.add(firstPlayer);
        builderGame.game.add(secondPlayer);
        return builderGame;
    }


    @Override
    public Game build() {
        return game;
    }

    @Override
    public GameBuilder addPlayer(String player) {
        game.add(player);
        return this;
    }
}
