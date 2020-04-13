CREATE TABLE person (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(191) NOT NULL,
	status BOOLEAN DEFAULT 1,
	place VARCHAR(191) NOT NULL,
	number VARCHAR(191) NOT NULL,
	complement VARCHAR(191) NOT NULL,
	district VARCHAR(191) NOT NULL,
	cep VARCHAR(191) NOT NULL,
	city VARCHAR(191) NOT NULL,
    state VARCHAR(191) NOT NULL,
    created_at TIMESTAMP DEFAULT NOW()
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO person (NAME, PLACE, NUMBER, DISTRICT, COMPLEMENT, CEP, CITY, STATE, CREATED_AT) VALUES ('Ian Moreira', 'Rua exemplo', '45', 'Centro', 'ao lado da farmacia', '45504-300', 'Ubaitaba', 'BA', NOW());