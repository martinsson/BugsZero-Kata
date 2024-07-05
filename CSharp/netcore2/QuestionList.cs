using System;
using System.Collections.Generic;

namespace trivia
{
    internal class QuestionList
    {
        private readonly List<string> _popQuestions = new List<string>();
        private int _index;

        public QuestionList(List<string> popQuestions)
        {
            this._popQuestions = popQuestions;
            this._index = 0;
        }

        public void AskNextQuestion()
        {
            Console.WriteLine(this._popQuestions[this._index % this._popQuestions.Count]);
            this._index++;
        }
    }
}
