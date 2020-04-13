DROP DATABASE IF EXISTS cbioportal;

CREATE DATABASE IF NOT EXISTS cbioportal CHARACTER SET utf8;

use cbioportal;

SET FOREIGN_KEY_CHECKS = 0;

/* Properties */
DROP TABLE IF EXISTS Properties;

CREATE TABLE Properties (
  id 					int				NOT NULL 	AUTO_INCREMENT,
  user					varchar(50)		NOT NULL,
  password				varbinary(255)	NOT NULL,
  lastModified			TIMESTAMP 		NOT NULL	DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO Properties VALUES(1, "phil", 
AES_ENCRYPT("fire", "Cynthia1"), CURRENT_TIMESTAMP);

/* Logs */
DROP TABLE IF EXISTS Logs;

CREATE TABLE Logs (
	dateCreated 	datetime       	NOT NULL,
   	logger  		varchar(100)    NOT NULL,
   	level   		varchar(10)    	NOT NULL,
   	message 		text  			NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;