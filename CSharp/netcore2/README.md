This version is running with [dotnet](https://www.microsoft.com/net) >= 2.0

The test script is changed to not contain illegal input, however the
test reference file is not changed. So once you get the test running it
will fail. You'll have to just validate the new result as the reference
result. I don't have an environment to do it.
Some method names have been renamed to comply with default PascalCase C# style guide

The code uses [Assent](https://github.com/droyad/Assent), which means that
when the test fails it will open up a diff-tool to compare the files
*TriviaTests.RefactoringTests.approved.txt* and the result of the run
*TriviaTests.RefactoringTests.received.txt*

If you're using CLI tools :
- installing required dependencies:
```
dotnet restore
```
- running tests and opening (default) diff tools:
```
dotnet test
```
