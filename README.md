# CBioPortal
 CBioPortal

Install application
   1. Install or use existing  Apache Tomcat 9.0.3.0.
   
   2. Install or use existing MySQL 5.1.47.
   
   3. Edit CBioPortal.properties - change database connection to match your MySQL database:
      # MySQL connection
	  ## DEV
	  ## http://localhost:8080
	  server.name=localhost
	  database.name=cbioportal
	  port.number=3306
	  user=root
	  password=phil55.cindy61
	  
    4. Copy CBioPortal.properties to folder in classpath (not necessary for this demo, .paroprties file wikl be in .war).
    
    5. Edit CBioPortal_TablesCreate.sql - change database name to match your MySQL database:
    
       DROP DATABASE IF EXISTS cbioportal;

	   CREATE DATABASE IF NOT EXISTS cbioportal CHARACTER SET utf8;

	   use cbioportal;
    
    6. Run CBioPortal_TablesCreate.sql
    
    7. Copy CBioPortal.war to Tomcat /webapps folder.
    
    8. Start Tomcat.
    
    9. URL = http://localhost:8080/cBioPortal/login
    
    10. User = phil
        Password = fire        
               

 