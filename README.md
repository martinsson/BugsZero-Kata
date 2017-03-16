# BugsZero-Kata
Practice Designing code for less bugs 

Take any codebase that you'e familiar with, and try to identify either existing bugs or bugs that could be easily introduced by other developers while they extend the code. Imagine an alternative design that would make that type of bug impossible or less likely. You'll need existing code to analyze, so take a project like the Legacy Code Retreat [trivia](https://github.com/caradojo/trivia) example.

## Procedure
1. Identify a weakness in the design
2. State what the potential bug is **before explaining your solution**
3. Explain or refactor the code to show the new design.

The purpose is to experiment with design alternatives, so it is not necessary to refactor until perfection, and it is optional to use tests.
The purpose of first explaining the problem in the design is to make sure that the new design is not just different but also adresses one or more specific problems.

Examples of BugsZero design
* [5 steps to mistake-proof software design](http://mozaicworks.com/blog/5-steps-to-mistake-proof-software-design/)
* [It's not a configuration issue. It’s a design issue](http://martinsson-johan.blogspot.fr/2016/06/its-not-configuration-issue-its-design.html)

# List of design problems that can cause (unnecessary) bugs
For initial guidance here's a list of typical problems and some suggested solutions.

## Use of primitives 
Allows for
* logic potentially duplicated
* no business constraints, for instance  
  * a persons age must be positive
  * an interest rate of 2 does it mean 2% or 200%?
* inverted parameters possible
* invalid values,

## Exposed internals
An object has its internals exposed. Aka FeatureEnvy. Allows for
* logic potentially duplicated
* low cohesion between logic and data

## Temporal coupling
use of functions have to be done in a certain order and that order is not enforced by structure (interpretation/compilation). Trap: using exceptions instead of enforcing structure

## Late validations - source of unnecessary exceptions
* ex: if (myservice == null). Probably useless validation
* ex: validation of instance parameters in function (should be in constructor)
* any validation that is **not** in the entry point of an application (rest call, ui-form, command-line, message bus, configuration). Database could be an entry point if data comes from other apps.
* any duplicated validation down the callstack

## Non constrained construction
* Mandatory parameters not in constructor
* Primitive parameters, allows for swapping order
  * type parameters
  * use builder

## Exception handling
When you look closely much of exception handling is accidental, not essential.
* adds complexity, often not essential complexity
* throw to the "top" is better
* try to make the exception impossible instead of handling it (ex, temporal coupling)

## Use of indices 
* Off-by-one errors is common
* index out of bounds

## Business rules incoherent with data structure
* ex: list for a set of colours - duplicated colours
* Solution: validate data and build Business Objects ASAP

# Explanations of #BugsZero
[15 min intro](https://www.youtube.com/watch?v=dUjie_IYFY8) or go for the
[full presentation](https://www.youtube.com/watch?v=gQR1NlkgLZU)
