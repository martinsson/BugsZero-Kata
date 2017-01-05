# BugsZero-Kata
Practice Designing code for less bugs 

Take any codebase that you'e familiar with, and try to identify potential errors that a user could provoque,
or another developer could introduce while extending the code. Imagine an alternative design that would solve or improve that problem. The purpose is to experiment with design alternatives, so it is optional to actually write code and you probably don't want to write tests at all - you'll get more time for experimentation that way.

You could take a project like the Legacy Code Retreat [trivia](https://github.com/caradojo/trivia) example

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
