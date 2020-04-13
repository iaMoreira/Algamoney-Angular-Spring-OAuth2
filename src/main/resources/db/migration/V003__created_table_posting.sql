CREATE TABLE posting (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	description VARCHAR(191) NOT NULL,
	expired_at DATE NOT NULL,
	payed_at DATE,
	value DECIMAL(10,2) NOT NULL,
	observation VARCHAR(191) NULL,
	type VARCHAR(191) NOT NULL,
	category_id BIGINT(20) NOT NULL,
	person_id BIGINT(20) NOT NULL,
    created_at TIMESTAMP DEFAULT NOW(),
    FOREIGN KEY (category_id) REFERENCES category(id)  ON DELETE CASCADE,
    FOREIGN KEY (person_id) REFERENCES person(id)  ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*
INSERT INTO posting (description, expired_at, payed_at, value, type, category_id, person_id, created_at) VALUES ('Ian Moreira', 'Rua exemplo', '45', 'Centro', 'ao lado da farmacia', '45504-300', 'Ubaitaba', 'BA', NOW());
*/