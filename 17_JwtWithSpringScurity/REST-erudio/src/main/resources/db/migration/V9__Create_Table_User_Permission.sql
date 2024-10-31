CREATE TABLE IF NOT EXISTS `user_permission` (
  `id_user` BIGINT(20) NOT NULL,
  `id_permission` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id_user`, `id_permission`),
  KEY `fk_user_permission_permission` (`id_permission`),
  CONSTRAINT `fk_user_permission` 
    FOREIGN KEY (`id_user`) 
    REFERENCES `users` (`id`) 
    ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_user_permission_permission` 
    FOREIGN KEY (`id_permission`) 
    REFERENCES `permission` (`id`) 
    ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
