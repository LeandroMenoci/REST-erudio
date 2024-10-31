CREATE TABLE IF NOT EXISTS `person` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(80) NOT NULL,
  `last_name` VARCHAR(80) NOT NULL,
  `address` VARCHAR(255) NOT NULL, -- Aumentado para 255 para cobrir endereços mais longos
  `gender` ENUM('male', 'female', 'other') NOT NULL, -- Usar ENUM para restringir valores possíveis
  PRIMARY KEY (`id`)
);
