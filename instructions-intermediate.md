
Clone [Trivia](https://github.com/caradojo/trivia) from the legacy code retreat is a good codebase to start this with. 

## Procedure
1. Identify a weakness in the design - now that's where we'e helping you
2. State what the potential bug is **before explaining your solution**. This is important, simply saying another 
solution is better avoids thinking of **why** it is actually better.
3. Explain or refactor the code to show the new design.

## Existing bugs and weaknesses

* A Game could have less than two players - make sure it always has at least two.
  * Use a compiled language or a static type checker like flowtype
* Someone could try to create a game with 7 players, make sure that's impossible.
  * or slightly easier, allow for 7 players or more
* A player that get's into prison always stays there
  * Other than just fixing the bug, try to understand what's wrong with the design and fix the root cause 
* The deck could run out of questions
  * Make sure that can't happen (a deck with 1 billion questions is cheating :)
* Introducing new categories of questions seems like tricky business.
  * Could you make sure all places have the "right" question and that the distribution 
  is always correct?
* Similarly changing the board size greatly affects the questions distribution
  * Is there a design where it is guaranteed that the question category distribution stays the same
