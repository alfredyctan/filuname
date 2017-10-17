DROP TABLE UserGroupAssn IF EXISTS;

CREATE TABLE UserGroupAssn (  
	user_id  INTEGER,  
	group_id INTEGER,
	PRIMARY KEY (user_id, group_id)   
);