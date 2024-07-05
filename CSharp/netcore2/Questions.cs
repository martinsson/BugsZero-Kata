using System;
using System.Collections.Generic;
using System.Linq;

namespace trivia
{
    internal class QuestionsDeck
    {
        private static readonly Lazy<QuestionsDeck> _lazyValue = new Lazy<QuestionsDeck>(() => new QuestionsDeck());
        private static QuestionsDeck _instance => QuestionsDeck._lazyValue.Value;

        public static QuestionsDeck GetQuestionsDeck() => QuestionsDeck._instance;

        private readonly Dictionary<string, QuestionList> _categoryToquestionListMap;
        private readonly List<string> _categoryList;

        private QuestionsDeck()
        {
            this._categoryToquestionListMap = new Dictionary<string, QuestionList>();
            this._categoryList = new List<string>();

            PrepareQuestionsForcategory("Pop");
            PrepareQuestionsForcategory("Science");
            PrepareQuestionsForcategory("Sports");
            PrepareQuestionsForcategory("Rock");

            void PrepareQuestionsForcategory(string category)
            {
                var questions = new List<string>();

                for (int i = 0; i < 50; i++)
                {
                    questions.Add($"{category} Question " + i);
                }

                this._categoryToquestionListMap.Add(category, new QuestionList(questions));
                this._categoryList.Add(category);
            }
        }

        public void AskQuestionForPlayer(Player player)
        {
            this._categoryToquestionListMap[GetQuestionCategoryForPlayer()].AskNextQuestion();

            string GetQuestionCategoryForPlayer()
            {
                var place = player.GetPlace() % this._categoryToquestionListMap.Count;
                this._categoryToquestionListMap.Keys.ElementAt(place);
                return this._categoryList[place];
            }
        }
    }
}
