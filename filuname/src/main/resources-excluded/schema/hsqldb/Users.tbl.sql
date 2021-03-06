DROP TABLE Users IF EXISTS;

CREATE TABLE Users (  
	id       INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) PRIMARY KEY,  
	username VARCHAR(30),  
	password VARCHAR(30)  
);

CREATE UNIQUE INDEX Users_username ON Users (username);