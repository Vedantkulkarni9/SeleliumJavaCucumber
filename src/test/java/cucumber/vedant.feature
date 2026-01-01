@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file

	Background:
	Given I landed on Ecommerce Page

  @vedant
  Scenario Outline: Positive Test of Submitting the order
  
    Given Logged in with username <name> and password <password>
    Then User types in search <search>
    
    #When Select checkbox<checkbox><selected>
  #  Then Select checkbox <checkbox> <selected>
    
 
 Examples: 
      | name  								    |  password		    | search |
      | vedantkulkarni9@gmail.com |  Information@1  | hello  |
 