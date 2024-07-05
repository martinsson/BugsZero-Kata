using System;

namespace trivia
{
    internal class Player
    {
        private readonly string _name;
        private int _place;
        private int _purse;
        private bool _inPenaltyBox;
        private bool _isGettingOutOfPenaltyBox;

        public Player(string name)
        {
            this._name = name;
            this._place = 0;
            this._purse = 0;
            this._inPenaltyBox = false;
        }

        public override string ToString() => this._name;

        public bool IsInPenaltyBox()
        {
            return this._inPenaltyBox;
        }

        public void PutInPenaltyBox()
        {
            this._inPenaltyBox = true;
        }

        public void TakeOutOfPenaltyBox()
        {
            this._inPenaltyBox = false;
        }

        public void MightGetOutOfPenaltyBox()
        {
            this._isGettingOutOfPenaltyBox = true;
        }

        public void CantGetOutOfPenaltyBox()
        {
            this._isGettingOutOfPenaltyBox = false;
        }

        public bool CanGetOutOfPenaltyBox() => this._isGettingOutOfPenaltyBox;

        public void Move(int roll)
        {
            this._place += roll;
            if (this._place > 11)
            {
                this._place -= 12;
            }

            Console.WriteLine(this._name
                            + "'s new location is "
                            + this._place);
        }

        public int GetPlace()
        {
            return this._place;
        }

        public void AddPurse()
        {
            this._purse++;
            Console.WriteLine(this._name
                            + " now has "
                            + this._purse
                            + " Gold Coins.");
        }

        public bool YetToWin()
        {
            return this._purse != 6;
        }
    }
}
