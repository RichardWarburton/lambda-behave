### Introduction

If you're a Java developer and you've seen the fluent, modern specification frameworks available in other programming languages such as spock or jasmine then Lambda Behave is for you. Its goal is to make testing a more pleasant experience than it currently is with junit.

The [changelog](https://raw.githubusercontent.com/RichardWarburton/lambda-behave/master/CHANGELOG.md) explains what features have been added in each release.

### Fluent Specifications

The Lambda Behave Specification design has several goals in mind:

* To read like plain English.
* To encourage describing tests using long and descriptive sentences, rather than a few words.
* An API that is fluent and discoverable nearly entirely through IDE auto-completion.

```java
public class StackSpec {{

    Stack<Integer> stack = new Stack<>();

    describe("a stack", it -> {

        it.isSetupWith(stack::clear);

        it.isConcludedWith(stack::clear);

        it.should("be empty when created", expect -> {
            expect.that(stack).isEmpty();
        });
```

There are many, many, expectations builtin to the framework - not just `isEmpty()`.

Every specification suite starts its declaration using the `Suite.describe` method. From that point onwards your IDE should be able to auto-complete the domain specific language for declaring specifications, but just in case you want more information, here's the details.

* If you want to specify a property about your system use `it.should`.
* If you want describe an expectation of that property, use `expect.that`. This will get you to a fluent API restricted to the type of value that you're making the expectation about. The expectation system is based upon hamcrest. Lambda Behave doesn't compromise the ability to compose matchers in favour of fluency - if you want to compose in more complex flavours simply use `expect.that(value).is()` and then you can use regular Hamcrest matchers. In my experience this is a rare, albeit useful, breakout option.
* If you want to setup or teardown data before or after each specification use `it.isSetupWith` and `it.isConcludedWith`.
* If you want to setup or teardown data before or after each suite use `it.initializesWith` and `it.completesWith`.
* Don't worry - I know some Java 8 lambdafied APIs don't deal with exceptions very well but you can throw exceptions in all our callbacks and the appropriate error will be reported, not just break the library.

### Data Driven Specifications

The ability to parametrise specifications by different data inputs.
Data driven tests in TestNG or the `@Parameterized` junit annotation perform a similar task.
`@Parameterized` only parameterises at the level of a class, whereas Lambda Behave parameterises at the level of a specification.

```java
describe("a pair of numbers", it -> {
    it.uses(4, 2)
      .and(6, 3)
      .toShow("%d / %d is two", (expect, x, y) -> {
          expect.that(x / y).is(2);
      });
});
```

The API in Lambda Behave is both fluent and also type safe and doesn't rely on reflection magic.
The `uses` method is overloaded to allow a different number of columns of data to be used. It also supports taking
streams or lists of data as its inputs, rather than explicitly chaining individual values.

Not only is the specification parameterised by the data, but the description is also parameterised, its name being interpreted as a format `String`.
The aforementioned test would output the following:

```
a pair of numbers
  4 / 2 is two
  6 / 3 is two
```
### Generated Specifications

Lambda Behave can automatically generate testcases for your to test your code with, similar to quick check or scala check.
The Fluent API for this is similar to data driven specifications allows for control over the way that the values are generated
and how many need to be generated. Here is an example of how to show that reversing a `String` twice returns the same `String`
using randomly generated test case values.

```java
it.requires(10)
  .example(asciiStrings())
  .toShow("reversing a String twice returns the original String", (expect, str) -> {
      String same = new StringBuilder(str).reverse().reverse().toString();
      expect.that(same).isEqualTo(str);
  });
```

All generated specifications follow this common pattern where;

 * The `require` clause expresses how many values to generate,
 * The `example` clause states what type of objects to generate and how to generate them, This is overloaded to allow multiple columns of testcase values to be generated.
 * The `toShow` clause behaves like a `toShow` clause for a data drive spec. It is type safe against the the different columns.
 So in the above example the paramter `str` will have had its type correctly inferred as `String`.

### Downloading Lambda Behave

If you're using a maven project then you can download Lambda Behave using the following pom entry.

```xml
<dependency>
    <groupId>com.insightfullogic</groupId>
    <artifactId>lambda-behave</artifactId>
    <version>0.4</version>
    <scope>test</scope>
</dependency>
```

If you're using a gradle project then you can use:

```gradle
testCompile group: 'com.insightfullogic', name: 'lambda-behave', version: '0.3'
```

There's also an [example project](https://github.com/RichardWarburton/lambda-behave/tree/lambda-behave-parent-0.3/lambda-behave-examples)
and there's published [Javadoc](http://javadoc.insightfullogic.com/lambda-behave).

### Junit Integration

Lambda Behave also offers a junit runner. This lets you easily integrate into existing your existing test suite, or the tests via an Eclipse, Intellij, Netbeans, Maven, Gradle or Ant. You just add an annotation to enable this,
and it can be run through your normal tooling.

```java
@RunWith(JunitSuiteRunner.class)
public class StackSpec {{
```
### Lambdas - what the hell are they?

Conveniently I've written a [book](http://shop.oreilly.com/product/0636920030713.do?cmp=af-prog-books-videos-product_cj_9781491900154_%25zp') on Lambda expressions in Java 8 and the cleaner code they enable!

### Licensing

This library is licensed under the liberal MIT license - see LICENSE for the
full details. This means that it's free for any use.

### More Details and How to contribute

[The wiki](https://github.com/RichardWarburton/lambda-behave/wiki) has more information.
