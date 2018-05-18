The CSharp code base is adapted from https://github.com/evilz/kata

The code uses [ApprovalTests](http://approvaltests.com), which means that
when the test fails it will open up a diff-tool to compare the files
*TriviaTests.RefactoringTests.approved.txt* and the result of the run 
*TriviaTests.RefactoringTests.received.txt*. 
If you have a « Could not find a diff program to use » exception, you can 
change the configuration in TrivaTests.cs BuildConfiguration method.