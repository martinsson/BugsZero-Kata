
# BugsZero Kata
<img align="right" width="500" height="500" src="https://raw.githubusercontent.com/martinsson/BugsZero-Kata/master/bugs-zero-image.jpg"/> 
Bugs are optional, they sneak into our code much thanks the design choices we do, or fail to do. Let's 
try refactoring some existing code to make it more bug-repellent!

You'll practice reading code, looking for parts where it is likely that developers would create a bug if they extended the code. Whenever you've found such a weakness in the design your challenge is to strengthen the design in order to make that kind of bug very unlikely, or even impossible!

**First** Identify a bug or a weakness in the design. **Then** Explain what feature could cause a bug. Or how the design contributed to its introduction. This is important, simply saying another solution is better avoids thinking of why it is actually better.
**Finally** Explain or refactor the code to show the new design. 

Prefer small changes in the design, for instance before applying a design pattern try simpler things, like maybe ExtractConstant or replace conditional logic with a map/dictionnary.

# Where do I start?

**Instructions and hints**
: We've found some weaknesses for you. Pick your choice in [instructions-intermediate.md](https://github.com/martinsson/BugsZero-Kata/blob/master/instructions-intermediate.md)


## Bug Patterns
Have a look at the long list of common [bug-patterns](https://github.com/martinsson/BugsZero-Kata/blob/master/bug-patterns.md), mostly unnecessary weaknesses in design.

# Resources on #BugsZero
The presentation specifically on code design [Bug Free, by Design](https://vimeo.com/275530228)
with [slides](http://www.changit.fr/bug-free-by-design/)

Make sure you know of a few ways to design away bugs, by looking at a few examples
* [5 steps to mistake-proof software design](http://mozaicworks.com/blog/5-steps-to-mistake-proof-software-design/)
* [It's not a configuration issue. It’s a design issue](http://martinsson-johan.blogspot.fr/2016/06/its-not-configuration-issue-its-design.html)


By Arlo Belshee
[15 min intro](https://www.youtube.com/watch?v=dUjie_IYFY8) or go for the
[full presentation](https://www.youtube.com/watch?v=gQR1NlkgLZU)

## Blog posts
[Bug teams](http://agileotter.blogspot.fr/2014/01/bug-teams-well-meaning-foolishness.html)

## Contribute
Contributions of all forms are welcome. Submit another language by fork/merge-request. If you're missing something or hava a question please create an issue.
