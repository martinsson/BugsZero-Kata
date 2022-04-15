package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import com.adaptionsoft.games.trivia.enums.QuestionEnum;

/*
 * Remarques: 
 * - Il faudrait créer une classe Question qui contienne une liste des propositions
 *  dont une est correcte et tester dans le jeux sur cette valeur pour avoir un jeux de donnée réel
 */

public class Game {
	
    int[] places = new int[6];
    int[] purses  = new int[6];
    
    boolean[] inPenaltyBox  = new boolean[6];
   
    LinkedList<String> popQuestions = new LinkedList<String>();
    LinkedList<String> scienceQuestions = new LinkedList<String>();
    LinkedList<String> sportsQuestions = new LinkedList<String>();
    LinkedList<String> rockQuestions = new LinkedList<String>();
    ArrayList<String> players = new ArrayList<String>();


    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;
    private static String MSG_CORRECT_ANSWER = "Answer was correct!!!!";
    private static String MSG_INCORRECT_ANSWER = "Question was incorrectly answered";

    /*
     *  Cette methode sert à initialiser les liste des questions
     */
    public  Game(){
    	for (int i = 0; i < 50; i++) {
			popQuestions.addLast(createQuestion(QuestionEnum.POP.getCode(),i));
			scienceQuestions.addLast(createQuestion(QuestionEnum.SCIENCE.getCode(),i));
			sportsQuestions.addLast(createQuestion(QuestionEnum.SPORTS.getCode(),i));
			rockQuestions.addLast(createQuestion(QuestionEnum.ROCK.getCode(),i));
    	}
    }

    /*
     *  @param : type
     *  		Le type de la question
     *  @param : index
     *  		L'index de la question dans sa liste
     *  @return 
     *  		Le message à insérer dans la liste
     */
	public String createQuestion(String type, int index){
		StringBuilder str = new StringBuilder();
		str.append(type);
		str.append(" Question ");
		str.append(index);
		return str.toString();
	}

	/*
	 *  Première règle de jeux : Le nombre deux joueurs devra être au moins deux joueurs 
	 */
	public boolean isPlayable() {
		return (howManyPlayers() >= 2);
	}

	/*
	 *  @param : playerName
	 *  		Le nom du joueur
	 *  @result
	 *      Un boolean qui indique si le joueur est bien initialisé dans le jeux
	 */
	public boolean add(String playerName) {
		
		
	    players.add(playerName);
	    places[howManyPlayers()] = 0;
	    purses[howManyPlayers()] = 0;
	    inPenaltyBox[howManyPlayers()] = false;
	    
	    System.out.println(playerName + " was added");
	    System.out.println("They are player number " + players.size());
		return true;
	}
	
	/*
	 *  @return
	 *     		Le nombre total des joueurs dans le jeux
	 */
	public int howManyPlayers() {
		return players.size();
	}

	public void roll(int roll) {
		System.out.println(players.get(currentPlayer) + " is the current player");
		System.out.println("They have rolled a " + roll);
		
		if (inPenaltyBox[currentPlayer]) {
			if (roll % 2 != 0) {
				isGettingOutOfPenaltyBox = true;
				
				System.out.println(players.get(currentPlayer) + " is getting out of the penalty box");
				movePlayerAndAskQuestion(roll);
			} else {
				System.out.println(players.get(currentPlayer) + " is not getting out of the penalty box");
				isGettingOutOfPenaltyBox = false;
				}
			
		} else {

			movePlayerAndAskQuestion(roll);
		}
		
	}

	/*
	 *  Repositionner le joueur et poser une question selon la catégorie actuelle ( nouvelle position du joueur )
	 */
	private void movePlayerAndAskQuestion(int roll) {
		
		places[currentPlayer] = (places[currentPlayer] + roll) % 12;
		
		System.out.println(players.get(currentPlayer)
                + "'s new location is "
                + places[currentPlayer]);
		
        String currentCategory = currentCategory();
		System.out.println("The category is " + currentCategory);
		askQuestion(currentCategory);
	}

	/*
	 *  Suppression de la question selon sa catégorie après le poser au joueur
	 */
	private void askQuestion(String category) {
	
		switch (category) {
        case "Pop":
            System.out.println(popQuestions.removeFirst());
            break;
        case "Science":
            System.out.println(scienceQuestions.removeFirst());
            break;
        case "Sports":
            System.out.println(sportsQuestions.removeFirst());
            break;
        case "Rock":
            System.out.println(rockQuestions.removeFirst());
            break;
        default:
            System.out.println("Unknown category");
            break;
    }
}
	
	/*
	 * @return 
	 * 	  la catégorie de la question selon la position du joueur
	 */
	private String currentCategory() {
        int playerPlace = places[currentPlayer];

        if (Arrays.asList(0, 4, 8).contains(playerPlace)) {
            return QuestionEnum.POP.getCode();
        } else if (Arrays.asList(1, 5, 9).contains(playerPlace)) {
            return QuestionEnum.SCIENCE.getCode();
        } else if (Arrays.asList(2, 6, 10).contains(playerPlace)) {
            return QuestionEnum.SPORTS.getCode();
        } else {
            return QuestionEnum.ROCK.getCode();
        }
    }

	/*
	 *  En cas d'une réponse correcte vérification si le joueur a gagné le jeux si non le passage au joueur suivant
	 */
	public boolean wasCorrectlyAnswered() {
        if (inPenaltyBox[currentPlayer]) {
            if (isGettingOutOfPenaltyBox) {

                correctAnswerEffect();
                // didPlayerWin doit vérifier si c'est le joueur courant qui a gagné, donc avant l'incrémentation
                // du champ "currentPlayer"
                boolean winner = didPlayerWin();
                incrementPlayer();
                return winner;

            /* comment ce cas peut-il arriver ? Si le joueur est en penaltyBox et n'est pas en phase de sortie
            il n'est pas censé avoir la possibilité de répondre à une question */
            } else {
                incrementPlayer();
                return true;
            }

        } else {
            correctAnswerEffect();
            boolean winner = didPlayerWin();
            incrementPlayer();
            return winner;
        }
    }

    public void correctAnswerEffect() {
        System.out.println(MSG_CORRECT_ANSWER);
        purses[currentPlayer]++;
        System.out.println(players.get(currentPlayer)
                + " now has "
                + purses[currentPlayer]
                + " Gold Coins.");
    }

    /*
     *  Passer le role au joueur suivant
     */
    public void incrementPlayer() {
        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;
    }

    /*
     * En cas d'une réponse incorrect : envoyer le joueur à la penalty box et passer le role au joueur suivant
     */
    public boolean wrongAnswer() {
        System.out.println(MSG_INCORRECT_ANSWER);
        System.out.println(players.get(currentPlayer) + " was sent to the penalty box");
        inPenaltyBox[currentPlayer] = true;

        incrementPlayer();
        return true;
    }

    /*
     *  Vérifier si le joueur actuel a gagné 
     */
    private boolean didPlayerWin() {
        return !(purses[currentPlayer] == 6);
    }
}
