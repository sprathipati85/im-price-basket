# im-price-basket
Application to calculate the price of items in shopping cart

# Overview
Application to calculate the price of items in shopping cart 
This repository contains a Spring boot applciation and an integration test along with associated unit tests 

Prices of the goods can be in CSV files in src/main/resources directory.
The goods that can be purchased, which are all priced in GBP, are:
  Soup – 65p per tin
  Bread – 80p per loaf
  Milk – £1.30 per bottle
  Apples – £1.00 per bag

**Current special offers are:** 
  Apples have 10% off their normal price this week
  Buy 2 tins of soup and get a loaf of bread for half price

The program should accept a list of items in the basket and output the subtotal,
the special offer discounts and the final price. 

Input should be via the command line in the form PriceBasket item1 item2 item3 ...
 
 For example: PriceBasket Apples Milk Bread
 Output should be to the console, for example:
  Subtotal: £3.10
  Apples 10% off: -10p
  Total: £3.00
  If no special offers are applicable, the code should output:
  Subtotal: £1.30
  (no offers available)
  Total: £1.30


The code and design should meet these requirements but be sufficiently flexible to allow for future extensibility. The
code should be well structured, suitably commented, have error handling and be tested.

***********************************
# Implementation details
***********************************

**This is a Spring Boot applicaiton using Java 8 features. The application is built using Maven.** 

**How to run this application:**
**Package the compiled code to distributable format, such as a JAR**
mvn package 

**Run the Jar locally**
java -jar target\im-price-basket-1.0-SNAPSHOT.jar Apples Bread Soup Milk Soup ** 

**Output: ** 

 - SubTotal : 4.40
 - Apples - 10% off: 0.10 Bread - 50% off: 0.40
 - Total : 3.90

***********************************
# Integration test
***********************************
 **AppTest - Integration Test for the Price Basket Application ** 
  
 Run this test to spin the actual spring boot application
 You can see the files are loaded, discount rules are applied, prices are calculated
 
 
 **Output Response** 
 
 - SubTotal : 4.40
 - Apples - 10% off: 0.10 Bread - 50% off: 0.40
 - Total : 3.90
 You can also change the input and test the response
