
# BugsZero Kata

Practice refactoring code to repel bugs. 

Bugs get their way into the code because the design allows it. Bugs are not a fatality
and finding ways to improve the design is a very powerful way of keeping them away from our code. 

You can think of this dojo like a Poka-Yoke training, understand and fix the root cause of the bugs.

# What is the task?

[Trivia](https://github.com/caradojo/trivia) from the legacy code retreat is a good codebase to start this with.
There are a few bugs in the code and a few weaknesses in the design to fix. 
By a weakness we mean that it'd be easy for a developer to introduce a certain type of bug 
while working with the code. Your job is to change the design so that it is either impossible
or at least much less likely that that kind of bug would be introduced.

# Where to start?

Pick any of the listed problems

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

# Next steps
A few pointers on typical weak design that is bug prone can be [found here](https://github.com/martinsson/BugsZero-Kata)

Go use this on some other code base.
