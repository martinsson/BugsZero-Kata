using System;
using System.Collections.Generic;
using trivia;

namespace Trivia
{
    public class Game
    {
        private readonly List<Player> _players;
        private readonly QuestionsDeck _questionsDeck;

        private int _currentPlayerIndex = 0;

        public Game(List<string> players)
        {
            if (players.Count < 2)
            {
                throw new ArgumentException("You need at least 2 players to play the game");
            }
            if (players.Count > 6)
            {
                throw new ArgumentException("You need at most 6 players to play the game");
            }

            this._players = new List<Player>();

            foreach (var player in players)
            {
                this.Add(player);
            }

            this._questionsDeck = QuestionsDeck.GetQuestionsDeck();
        }

        public bool IsPlayable()
        {
            return (this._players.Count >= 2);
        }

        private bool Add(String playerName)
        {

            this._players.Add(new Player(playerName));

            Console.WriteLine(playerName + " was Added");
            Console.WriteLine("They are player number " + this._players.Count);
            return true;
        }

        public void Roll(int roll)
        {
            var currentPlayer = this._players[_currentPlayerIndex];
            Console.WriteLine(currentPlayer + " is the current player");
            Console.WriteLine("They have rolled a " + roll);

            if (currentPlayer.IsInPenaltyBox() && roll % 2 == 0)
            {
                Console.WriteLine(currentPlayer + " is not getting out of the penalty box");
                currentPlayer.CantGetOutOfPenaltyBox();
                return;
            }

            if (currentPlayer.IsInPenaltyBox())
            {
                currentPlayer.MightGetOutOfPenaltyBox();
                Console.WriteLine(currentPlayer + " is getting out of the penalty box");
            }

            currentPlayer.Move(roll);

            Console.WriteLine("The category is " + this.CurrentCategory());
            this._questionsDeck.AskQuestionForPlayer(currentPlayer);

        }


        private String CurrentCategory()
        {
            int currentPlayerPlace = this._players[_currentPlayerIndex].GetPlace();
            if (currentPlayerPlace == 0) return "Pop";
            if (currentPlayerPlace == 4) return "Pop";
            if (currentPlayerPlace == 8) return "Pop";
            if (currentPlayerPlace == 1) return "Science";
            if (currentPlayerPlace == 5) return "Science";
            if (currentPlayerPlace == 9) return "Science";
            if (currentPlayerPlace == 2) return "Sports";
            if (currentPlayerPlace == 6) return "Sports";
            if (currentPlayerPlace == 10) return "Sports";
            return "Rock";
        }

        public bool WasCorrectlyAnswered()
        {
            var currentPlayer = this._players[_currentPlayerIndex];
            if (currentPlayer.IsInPenaltyBox() && !currentPlayer.CanGetOutOfPenaltyBox())
            {
                this.GiveTurnToNextPlayer();
                return true;
            }

            currentPlayer.TakeOutOfPenaltyBox();

            Console.WriteLine("Answer was correct!!!!");
            currentPlayer.AddPurse();

            this.GiveTurnToNextPlayer();

            return currentPlayer.YetToWin();
        }

        private void GiveTurnToNextPlayer()
        {
            _currentPlayerIndex++;
            if (_currentPlayerIndex == this._players.Count) _currentPlayerIndex = 0;
        }

        public bool WrongAnswer()
        {
            var currentPlayer = this._players[_currentPlayerIndex];
            Console.WriteLine("Question was incorrectly answered");
            Console.WriteLine(currentPlayer + " was sent to the penalty box");
            currentPlayer.PutInPenaltyBox();

            this.GiveTurnToNextPlayer();
            return true;
        }
    }

}
