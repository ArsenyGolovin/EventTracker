CREATE TABLE IF NOT EXISTS Client (
	id			BIGINT 		AUTO_INCREMENT PRIMARY KEY,
	name		VARCHAR(50) NOT NULL,
	password	VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS Event (
	id				BIGINT		AUTO_INCREMENT PRIMARY KEY,
	creator_id		BIGINT		NOT NULL,
	name			VARCHAR(50) NOT NULL,
	start_datetime	DATETIME,
	end_datetime	DATETIME
);

CREATE TABLE IF NOT EXISTS Entry (
	id				BIGINT		AUTO_INCREMENT PRIMARY KEY,
	parttaker_id	BIGINT		NOT NULL,
	event_id		int NOT NULL,
    FOREIGN KEY (parttaker_id) REFERENCES Client(id) ON DELETE CASCADE,
    FOREIGN KEY (event_id) REFERENCES Event(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Stage (
	id 		BIGINT			AUTO_INCREMENT PRIMARY KEY,
    title 	VARCHAR(255)	NOT NULL,
    event	BIGINT			NOT NULL,
    FOREIGN KEY (event) REFERENCES Event(id) ON DELETE CASCADE
);