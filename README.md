### Problem Statement

Consider a store where each item has a price per unit, and may also have a volume price. For example, apples may be $1.00 each or 4 for $3.00.

Implement a point-of-sale scanning API that accepts an arbitrary ordering of products (similar to what would happen at a checkout line) and then returns the correct total price for an entire shopping cart based on the per unit prices or the volume prices as applicable.

Here are the products listed by code and the prices to use (there is no sales tax):
```
Product Code | Price
--------------------
A           | $1.25 each or 3 for $3.00
B           | $4.25
C           | $1.00 or $5 for a six pack
D           | $0.75
```

There should be a top level point of sale terminal service object that looks something like the pseudo-code below. You are free to design and implement the rest of the code however you wish, including how you specify the prices in the system:

```java
PointOfSaleTerminal terminal = new PointOfSaleTerminal();
terminal.setPricing(...);
terminal.scan(“A”);
terminal.scan(“C”);
... etc.
BigDecimal result = terminal.calculateTotal();
```

### Test Cases

Here are the minimal inputs you should use for your test cases. These test cases must be shown to work in your program:

* Scan these items in this order: ABCDABA; Verify the total price is $13.25.
* Scan these items in this order: CCCCCCC; Verify the total price is $6.00.
* Scan these items in this order: ABCD; Verify the total price is $7.25.

A very simple solution to a sample problem
Requirements:
    jdk 1.7
    maven - Make sure maven is setup to use java version 1.7 (check with mvn -v)
    junit - Test framework

Solution:
Path: ./src/main/java
Two different solutions to the problem.
They are both a little kludgy in my opinion

Version 1:
com.sample.kisspointofsale.KissPointOfSaleTerminal.java
A very procedural solution to the problem in a single file that is not very flexible.
It was primarily used to verify logic (prototype), may have more bugs that version 2 ;)

Version 2:
```com.sample.pointofsale
    PointOfSaleTerminal.java 
        - An interface as described in the problem
    SimplePointOfSaleTerminal.java 
        - A simple "controller" like implementation of the PointOfSaleTerminal interface
          User input checking is performed and appropriate calls to other classes are made
    *Exception.java 
        - Various exception classes used 

com.sample.pointofsale.model 
    - Classes for modeling Orders, LineItems, Prices, and Products

com.sample.pointofsale.services 
    - Very stripped down "services" that do the actual work
```
Testing:
```
Path: ./src/test/java
com.sample.pointofsale.TestAll.java 
    - A junit test suite that runs all the tests
com.sample.pointofsale.TestMiscSimplePointOfSaleTerminal 
    - A few junit test cases, mostly for corner/exception testing
com.sample.pointofsale.TestParameterizedPointOfSaleTerminal
    - A simple parameterized junit test that checks a few data sets against both
    KissPointOfSaleTerminal and SimplePointOfSaleTerminal
```
Command line testing 
cleaning:
```mvn clean```

compiling:
```mvn compile```

testing:
```mvn test```

all-in-one:
```mvn clean compile test```

Test results will be output to: ./target/surefire-reports
