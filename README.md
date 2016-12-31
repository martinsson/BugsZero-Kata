# BugsZero-Kata
Practice Designing code for less bugs 

Take any codebase that you'e familiar with, and try to identify potential errors that a user could provoque,
or another developer could introduce while extending the code. Imagine an alternative design that would solve or improve that problem.

You could take a project like the Legacy Code Retreat [trivia](https://github.com/jbrains/trivia) example

# List of design problems that can cause (unnecessary) bugs
For initial guidance here's a list of typical problems and some suggested solutions.

## Use of primitives 
Allows for
* logic potentially duplicated
* no business constraints
* inverted parameters possible
* invalid values,

## Expose internals 
Allows for
* logic potentially duplicated
* low cohesion between logic and data

## Temporal coupling
use of functions have to be done in a certain order and that order is not enforced by structure (interpretation/compilation). Trap: using exceptions instead of enforcing structure

## Late validations - source of unnecessary exceptions
* ex: if (myservice == null). Probably useless validation
* ex: validation of instance parameters in function (should be in constructor)
* any validation that is **not** in the entry point of an application (rest call, ui-form, command-line, message bus, configuration). Database could be an entry point if data comes from other apps.
* any duplicated validation down the callstack

## Construction
* Mandatory parameters not in constructor
* Primitive parameters, allows for swapping order
** type parameters
** use builder

## Exception handling
* adds complexity, often not essential complexity
* throw to the "top" is better
* try to make the exception impossible instead of handling it (ex, temporal coupling)

## Use of indices 
* Off-by-one errors is common
* index out of bounds

## business rules incoherent with data structure
* ex: list for a set of colours - duplicated colours
