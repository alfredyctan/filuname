DROP TABLE UserGroupAuthority IF EXISTS;

CREATE TABLE UserGroupAuthority (  
	group_id  INTEGER,  
	authority VARCHAR(50),
	PRIMARY KEY (group_id, authority)
);