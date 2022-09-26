CREATE TABLE `tb_receita` (
  `id` INT(10) AUTO_INCREMENT PRIMARY KEY,
  `descricao` longtext NOT NULL,
  `Double` decimal(65,2) NOT NULL,
  `data` datetime(6) NOT NULL
  `categoria` ENUM('ALIMENTACAO', 'SAUDE', 'MORADIA', 'TRANSPORTE', 'EDUCACAO', 'LAZER', 'IMPREVISTOS',	'OUTROS')
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
