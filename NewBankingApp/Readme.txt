Jason Steinberg Week 2 Banking App

Set up the database in SQL Developer with 1801 Banking.sql.
To reset the databse, run 1801 Banking.sql again.

Run LogInSQL.java to start program.
Use capital letters only when deciding on course of action.
To access employee section, input password.
To access admin section, input superpassword.

Logs are in log.txt in src/main/resources.

com.revature.banking contains the objects.
User is an abstract class with no instances.
Account, Admin, Customer, and Employee are all classes where user creates and updates instances.
Audit is automatically updated when an Account object is updated.

BankOracle.java is where the SQL statements are set up.
ConnectionUtil.java and database.properties help the program connect to the SQL database.