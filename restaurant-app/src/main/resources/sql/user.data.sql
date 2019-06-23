INSERT IGNORE INTO `RestaurantUserEntity`(`id`, `restaurantReference`) VALUES ('RES-1UER-ENTITY-1233321', 'RES-4b545bf4-47c4-11e9-b210-d663bd873d91');
INSERT IGNORE INTO `RestaurantUserEntity`(`id`, `restaurantReference`) VALUES ('RES-2UER-ENTITY-1233321', 'RES-4b545bf4-47c4-11e9-b210-d663bd873d91');
INSERT IGNORE INTO `RestaurantUserEntity`(`id`, `restaurantReference`) VALUES ('RES-3UER-ENTITY-1233321', 'RES-4b545bf4-47c4-11e9-b210-d663bd873d91');

INSERT IGNORE INTO `restaurant_user_roles`(`restaurant_user_id`, `restaurant_role_type`) VALUES ('RES-1UER-ENTITY-1233321', 'ROLE_WAITER');
INSERT IGNORE INTO `restaurant_user_roles`(`restaurant_user_id`, `restaurant_role_type`) VALUES ('RES-2UER-ENTITY-1233321', 'ROLE_COOK');
INSERT IGNORE INTO `restaurant_user_roles`(`restaurant_user_id`, `restaurant_role_type`) VALUES ('RES-3UER-ENTITY-1233321', 'ROLE_ADMIN');


INSERT IGNORE INTO `ClientEntity`(`id`, `name`, `phoneNumber`, `surname`) VALUES
('4ce8ecda-47d5-11e9-b210-d663bd873d93', 'Adam', '123-321-234', 'Adam');

INSERT IGNORE INTO `UserEntity`(`id`, `email`, `password`, `username`, `client_id`, `restaurant_user_id`) VALUES
('039e48f4-47d5-11e9-b210-d663bd873d93', 'sample@email.com', '$2a$10$0JHt1mtLosCir1HypAT3ZeZ669rFU8bj/dA3ojR4AKlKt0Lnu09Mq', 'adamson', '4ce8ecda-47d5-11e9-b210-d663bd873d93', 'RES-1UER-ENTITY-1233321');

# INSERT INTO `user_roles`(`user_id`, `role_type`) VALUES ('039e48f4-47d5-11e9-b210-d663bd873d93', 'ROLE_USER');


INSERT IGNORE INTO `ClientEntity`(`id`, `name`, `phoneNumber`, `surname`) VALUES
('4ce8ecda-aasd3-11e9-b210-d663bd873d93', 'Janusz', '654-321-234', 'Janusz');

INSERT IGNORE INTO `UserEntity`(`id`, `email`, `password`, `username`, `client_id`, `restaurant_user_id`, `createdAt`, `updatedAt`) VALUES
('039e48f4-47d5-6544-b210-d663bd873d93', 'janusz@email.com', '$2a$10$cvYBVfdfoUoQxBXw2qZKruHDDvf4U8w305qI1Z8rVIwV8l53yoida', 'janusz', '4ce8ecda-aasd3-11e9-b210-d663bd873d93','RES-2UER-ENTITY-1233321', now(), now());

INSERT IGNORE INTO `ClientEntity`(`id`, `name`, `phoneNumber`, `surname`) VALUES
('4ce8ecda-aasd3-11234e9-b210-d663bd873d93', 'Janusz', '654-321-234', 'Janusz');

INSERT IGNORE INTO `UserEntity`(`id`, `email`, `password`, `username`, `client_id`, `restaurant_user_id`, `createdAt`, `updatedAt`) VALUES
('7867e48f4-47d5-6544-b210-d663bd873d93', 'admin@email.com', '$2a$10$aHImSWFl.z91XMi5FCei5u5DVER0LDULpo5h2x.Ocx.IrRAegp06C', 'admin', '4ce8ecda-aasd3-11234e9-b210-d663bd873d93', 'RES-3UER-ENTITY-1233321', now(), now());


INSERT IGNORE INTO `ClientEntity`(`id`, `name`, `phoneNumber`, `surname`) VALUES
('4ce8ecda-47d5-11e9-b210-23423432', 'user', '321321987', 'user');
INSERT IGNORE INTO `UserEntity`(`id`, `email`, `password`, `username`, `client_id`, `createdAt`, `updatedAt`) VALUES
('7867e48f4-47d5-6544-b210-235235235', 'user@email.com', '$2a$10$aHImSWFl.z91XMi5FCei5u5DVER0LDULpo5h2x.Ocx.IrRAegp06C', 'user', '4ce8ecda-aasd3-11234e9-b210-23423432', now(), now());



