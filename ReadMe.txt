Howitzer Read Me

Install application
   1. Install or use existing  Apache Tomcat 8.0.35.
   
   2. Install or use existing MySQL 5.1.47.
   
   3. Edit Howitzer.properties - change database connection to match your MySQL database:
      # MySQL connection
	  ## DEV
	  ## http://localhost:8080
	  server.name=localhost
	  database.name=howitzer
	  port.number=3306
	  user=root
	  password=phil55.cindy61
	  
    4. Copy Howitzer.properties to folder in classpath.
    
    5. Edit Howitzer_TablesCreate.sql - change database name to match your MySQL database:
    
       DROP DATABASE IF EXISTS howitzer;

	   CREATE DATABASE IF NOT EXISTS howitzer CHARACTER SET utf8;

	   use howitzer;
    
    6. Run Howitzer_TablesCreate.sql
    
    7. Copy Howitzer.war to Tomcat /webapps folder.
    
    8. Start Tomcat.
    
    9. URL = http://localhost:8080/howitzer/login
    
    10. User = phil
        Password = fire
        
                
Notes:

1. User statistics are available by selecting Users from the navigation bar.

2. History is available by selecting History from the navigation bar.

3. To fire:
   a. Select a user id from the drop down.
   b. Enter a distance to target.
   c. Enter an initial muzzle velocity.
   d. Enter an angle.
   e. Enter the target diameter.
   f. Press the Fire button.
   
4. All users are be re-ranked after each shell is fired.

5. I'm assuming no air resistance (mass and weight of the shell are irrelevant).


Create .war file:
1. Open terminal
   a. cd /Users/phil/Dev/Howitzer/Howitzer
   b. mvn clean install


