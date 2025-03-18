CREATE TABLE IF NOT EXISTS cadastro_usuario(
id INT PRIMARY KEY,
nome varchar(45),
email varchar(255) UNIQUE,
senha varchar(30)
);