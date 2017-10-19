DROP TABLE HitCount IF EXISTS;

CREATE TABLE HitCount (  
	visit_date DATE,
	website VARCHAR(100),
	count int,
	PRIMARY KEY (visit_date, website)
);