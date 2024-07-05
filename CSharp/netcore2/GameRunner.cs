using System;
using System.Collections.Generic;

namespace Trivia
{
    public class GameRunner
    {

        private static bool notAWinner;

        public static void Main(String[] args)
        {
            var players = new List<string> { "Chet", "Pat", "Sue" };
            Game aGame = new Game(players);

            Random rand = new Random();

            do
            {

                aGame.Roll(rand.Next(5) + 1);

                if (rand.Next(9) == 7)
                {
                    notAWinner = aGame.WrongAnswer();
                }
                else
                {
                    notAWinner = aGame.WasCorrectlyAnswered();
                }



            } while (notAWinner);

        }


    }

}
