CREATE TABLE IF NOT EXISTS `permission` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(255) NOT NULL, -- Tornando o campo obrigatório
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4; -- Adicionando charset para suporte a caracteres especiais
