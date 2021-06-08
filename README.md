# Gpay
This is the Main Application i.e. Gpay Application. Which interact with the Bank Application for the Account Creation in Gpay.
API:
    1.Register User in Gpay
    2.Fund Transfer By Phone Number
    3.Latest 10 Transaction History

Condition to register in Gpay : 
  1.User must have an Account in Bank with Registered Phone Number.
  2.If user Dont Have an Account in bank It will give Exception.
Condition to Transfer Fund :
  1.In Gpay we use Phone Number for transfering Fund 
  2.Both User must have a Gpay Registered with Phone
  3.Source Account must have proper fund to transfer
  
#Bank
 This is a Bank Service Application. 
 API :
      1.In this Application User Create account  
      2.Do Fund Transfer to another account
      3.Get Statement By account number
#exureka Server
   Eureka server for the monitorig the both application status up/down.
 
