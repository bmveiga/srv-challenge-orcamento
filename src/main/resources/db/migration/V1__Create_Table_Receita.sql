CREATE TABLE `tb_despesa` (
  `id` INT(10) AUTO_INCREMENT PRIMARY KEY,
  `descricao` longtext NOT NULL,
  `Double` decimal(65,2) NOT NULL,
  `data` datetime(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
