INSERT INTO `role`(`Id`, `name`) VALUES ('1','ROLE_ADMIN');
INSERT INTO `role`(`Id`, `name`) VALUES ('2','ROLE_USER');

INSERT INTO `user_role`(`user_id`, `role_id`) VALUES (1,1);
INSERT INTO `user_role`(`user_id`, `role_id`) VALUES (1,2);

