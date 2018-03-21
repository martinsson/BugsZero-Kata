The CSharp code base is a copy from https://github.com/evilz/kata

The test script is changed to not contain illegal input, however the
test reference file is not changed. So once you get the test running it 
will fail. You'll have to just validate the new result as the reference 
result. I don't have an environment to do it.

The code uses [ApprovalTests](http://approvaltests.com), which means that
when the test fails it will open up a diff-tool to compare the files
*TriviaTests.RefactoringTests.approved.txt* and the result of the run 
*TriviaTests.RefactoringTests.received.txt*