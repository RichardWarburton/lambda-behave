Lambda Behave Release Changelog
===============================

0.4 - 09/07/2014
----------------

 * Move to the Junit 4 model of re-initialising objects before each test to improve test isolation.
 * Fix a dependency issue with cglib that was blocked usage of Mockito 2.2
 * Added more power to exception expectation matching (thanks Robert Firek)
 * and syntactic sugar to bounded expectations on (thanks Colin Vipurs)
 * Improved String matchin (thanks Ross Binden)
 * Fix README errors. (thanks Ross Binden)
 * Clarify the license in the Readme.
 * rename randomNumbers(long) to deterministicNumbers(long)

0.3 - 13/08/2014
----------------

 * Rename the methods that run before and after specifications. This is an incompatible change and end users will need
 to update their code bases appropriately.
 * Integrate and simplify the random number generation and the data driven testing APIs.
 * Add the ability to reset mocks.
 * Add combinators for using business domain objects in property based testing.
 * Start logging seed numbers for random number generators used in property based testing.
 * Improve the Javadoc due to feedback from the Great folks at the LJC.
 * Add integration with ScalaTest so that you can run lambda-behave tests through the scala-test infrastructure.
 * Fix several bugs related to API corner cases.
 * Reintegrate the examples project into the maven multi-module project.

See https://github.com/RichardWarburton/lambda-behave/issues?q=milestone%3A0.3+ for more details.

0.2 - 06/07/2014
----------------

 * Adds automatic test-case generation & property based testing.
 * Bans multiple specifications with the same name
 * Fixes stack traces being incorrectly reported in errors
 * Adds in more fluent expectations API with more overloads and support for arrays and exceptions

0.1 - 26/06/2014
----------------

 * Initial Release
 * Adds declaring and running specifications
 * Adds parameterised specifications
 * completes first cut of fluent expectations API
