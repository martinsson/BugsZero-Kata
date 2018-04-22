# Bug patterns
Classification of typical design problems that cause (unnecessary) bugs and some suggested solutions.

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

## Non constrained construction
* Mandatory parameters not in constructor
* Primitive parameters, allows for swapping order
  * type parameters
  * use builder

## Temporal coupling
use of functions have to be done in a certain order and that order is not enforced by structure (interpretation/compilation). Trap: using exceptions instead of enforcing structure

## Hidden testable code
Code that would be easy to test is hidden in between calls to difficult-to-test dependencies. [Hidden testable code](http://martinsson-johan.blogspot.fr/2018/04/bug-pattern-hidden-testable-code.html) is likely to contain bugs or generate them in the future. Extract Pure Function (easy to test) is the refactoring to apply.

## Late validations - source of unnecessary exceptions
* ex: if (myservice == null). Probably useless validation
* ex: validation of instance parameters in function (should be in constructor)
* any validation that is **not** in the entry point of an application (rest call, ui-form, command-line, message bus, configuration). Database could be an entry point if data comes from other apps.
* any duplicated validation down the callstack

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
