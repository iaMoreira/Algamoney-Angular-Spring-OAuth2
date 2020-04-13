CREATE TABLE category (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT NOW()
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO category (name) VALUES ('Laser');
INSERT INTO category (name) VALUES ('Alimentação');
INSERT INTO category (name) VALUES ('Supermercado');
INSERT INTO category (name) VALUES ('Farmacia');
INSERT INTO category (name) VALUES ('Outros');
