package com.adaptionsoft.games.uglytrivia;

public interface BuildingGame {

    Game build();

    interface AddThirdPlayer extends BuildingGame {

        AddFourthPlayer addPlayer(String player);
    }

    interface AddFourthPlayer extends BuildingGame {

        AddFifthPlayer addPlayer(String player);
    }

    interface AddFifthPlayer extends BuildingGame {

        AddSixthPlayer addPlayer(String player);
    }

    interface AddSixthPlayer extends BuildingGame {

        BuildingGame addPlayer(String player);
    }

}
