CREATE TABLE IF NOT EXISTS `users` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(255) NOT NULL,
  `full_name` VARCHAR(255) DEFAULT NULL,
  `password` VARCHAR(255) NOT NULL,
  `account_non_expired` TINYINT(1) DEFAULT 1,
  `account_non_locked` TINYINT(1) DEFAULT 1,
  `credentials_non_expired` TINYINT(1) DEFAULT 1,
  `enabled` TINYINT(1) DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_user_name` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
