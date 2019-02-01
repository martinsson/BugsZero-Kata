
# BugsZero Kata

Bugs are optional, they sneak into our code much thanks the design choices we do, or fail to do. Let's 
try refactoring some existing code to make it more bug-repellent!

You'll practice reading code, looking for parts where it is likely that developers would create a bug if they extended the code. Whenever you've found such a weakness in the design your challenge is to strengthen the design in order to make that kind of bug very unlikely, or even impossible!

**First** Identify a bug or a weakness in the design. **Then** Explain what feature could cause a bug. Or how the design contributed to its introduction. This is important, simply saying another solution is better avoids thinking of why it is actually better.
**Finally** Explain or refactor the code to show the new design. 

Prefer small changes in the design, for instance before applying a design pattern try simpler things, like maybe ExtractConstant or replace conditional logic with a map/dictionnary.

# Where do I start?

## Beginner
Make sure you know of a few ways to design away bugs, by looking at a few examples
* [5 steps to mistake-proof software design](http://mozaicworks.com/blog/5-steps-to-mistake-proof-software-design/)
* [It's not a configuration issue. It’s a design issue](http://martinsson-johan.blogspot.fr/2016/06/its-not-configuration-issue-its-design.html)

Have a look at the long list of common [bug-patterns](https://github.com/martinsson/BugsZero-Kata/blob/master/bug-patterns.md), mostly unnecessary weaknesses in design.
Beware, those are a bit terse, most patterns need to be explained. 

## Intermediate
We've found some weaknesses for you. Pick your choice in [instructions-intermediate.md](https://github.com/martinsson/BugsZero-Kata/blob/master/instructions-intermediate.md)

## Advanced
If you'e already accustomed this thinking this way, have a look at [instructions-advanced.md](https://github.com/martinsson/BugsZero-Kata/blob/master/instructions-advanced.md)

## Solutions
After doing your version, have a look at the [various solutions](https://github.com/martinsson/BugsZero-Kata/blob/master/solutions.md) in the form of merge-requests. It is easy to see what the change was using the diff-view. They are meant to be (one of) the best trade-offs for the given problem. There's always trade-offs, but if you find a better or simply good alternative, please fork and merge-request and we'll reference it.

## Next steps
Go use this on some other code base, like your project!
 
Give us some suggestions in the issues.

# Resources on #BugsZero
Some [slides specifically on code design](http://www.changit.fr/bug-free-by-design/)

The general part
[15 min intro](https://www.youtube.com/watch?v=dUjie_IYFY8) or go for the
[full presentation](https://www.youtube.com/watch?v=gQR1NlkgLZU)

## Blog posts
[Bug teams](http://agileotter.blogspot.fr/2014/01/bug-teams-well-meaning-foolishness.html)

## Contribute
Contributions of all forms are welcome. Submit another language by fork/merge-request. If you're missing something or hava a question please create an issue.
