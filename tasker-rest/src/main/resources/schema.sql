/**
 * Author:  yaque
 * Created: Dec 22, 2023
 */
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  id                BIGINT  PRIMARY KEY NOT NULL,
  user_name         VARCHAR(15),
  full_name           VARCHAR(45));


DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  id                BIGINT  PRIMARY KEY NOT NULL,
  title             VARCHAR(45),
  description       TEXT NOT NULL,
  placed_at         DATETIME NOT NULL,
  status            ENUM('PENDING','IN_PROGRESS','DONE') NOT NULL,
  assigned_to_id    BIGINT);

INSERT INTO `user` (`id`, `full_name`, `user_name`) VALUES ('1', 'John Doe', 'jhon_user');
INSERT INTO `user` (`id`, `full_name`, `user_name`) VALUES ('2', 'Sam Smith', 'sam_user');


INSERT INTO `task` (`id`, `description`, `placed_at`, `status`, `title`, `assigned_to_id`) VALUES ('1', 'testing task', '2023-12-22', 'PENDING', 'task1', '1');
INSERT INTO `task` (`id`, `description`, `placed_at`, `status`, `title`) VALUES ('2', 'testing task 02', '2023-12-22', 'PENDING', 'task2');
