INSERT IGNORE INTO `VolumeEntity`(`id`, `capacity`, `unitEnum`)
VALUES ('57805b1a-47d0-11e9-b210-d663bd873d93', '250', 'ML');

INSERT IGNORE INTO `VolumeEntity`(`id`, `capacity`, `unitEnum`)
VALUES ('57805b1a-47d0-11e9-b210-d663bd873d93', '100', 'G');


INSERT IGNORE INTO `IngredientEntity`(`id`, `name`, `volume_id`)
VALUES ('7d4d5d2a-47d0-11e9-b210-d663bd873d93', 'Kurczak', '57805b1a-47d0-11e9-b210-d663bd873d93');

INSERT IGNORE INTO `IngredientEntity`(`id`, `name`, `volume_id`)
VALUES ('7d4d5d2a-47d0-11e9-b210-d663bd873d93', 'Talarki', '57805b1a-47d0-11e9-b210-d663bd873d93');

INSERT IGNORE INTO `IngredientEntity`(`id`, `name`, `volume_id`)
VALUES ('7d4d5d2a-47d0-11e9-b210-d663bd873d93', 'Kasza gryczana', '57805b1a-47d0-11e9-b210-d663bd873d93');

INSERT IGNORE INTO `IngredientEntity`(`id`, `name`, `volume_id`)
VALUES ('7d4d5d2a-47d0-11e9-b210-d663bd873d93', 'Makaron pene', '57805b1a-47d0-11e9-b210-d663bd873d93');

INSERT IGNORE INTO `IngredientEntity`(`id`, `name`, `volume_id`)
VALUES ('7d4d5d2a-47d0-11e9-b210-d663bd873d93', 'Surówka z kapusty', '57805b1a-47d0-11e9-b210-d663bd873d93');

# Category
INSERT IGNORE INTO `CategoryEntity`(`id`, `name`, `orderOnList`, `restaurantReference`) VALUES
('00202bc4-47d1-11e9-b210-d663bd873111','Przystawki', 0, 'RES-4b545bf4-47c4-11e9-b210-d663bd873d91');

INSERT IGNORE INTO `CategoryEntity`(`id`, `name`, `orderOnList`, `restaurantReference`) VALUES
('00202bc4-47d1-11e9-b210-d663bd873d11','Zupy', 1, 'RES-4b545bf4-47c4-11e9-b210-d663bd873d91');

INSERT IGNORE INTO `CategoryEntity`(`id`, `name`, `orderOnList`, `restaurantReference`) VALUES
('00202bc4-47d1-11e9-b210-d663bd873d21','Dania główne', 2, 'RES-4b545bf4-47c4-11e9-b210-d663bd873d91');

INSERT IGNORE INTO `CategoryEntity`(`id`, `name`, `orderOnList`, `restaurantReference`) VALUES
('00202bc4-47d1-11e9-b210-d663bd873d31','Napoje', 3, 'RES-4b545bf4-47c4-11e9-b210-d663bd873d91');


INSERT IGNORE INTO `CategoryEntity`(`id`, `name`, `orderOnList`, `restaurantReference`) VALUES
('00202bc4-47d1-11e9-b210-d663bd873d92','Przystawki', 0, 'RES-4b545bf4-47c4-11e9-b210-d663bd873d92');

INSERT IGNORE INTO `CategoryEntity`(`id`, `name`, `orderOnList`, `restaurantReference`) VALUES
('00202bc4-47d1-11e9-b210-d663bd873d12','Zupy', 1, 'RES-4b545bf4-47c4-11e9-b210-d663bd873d92');

INSERT IGNORE INTO `CategoryEntity`(`id`, `name`, `orderOnList`, `restaurantReference`) VALUES
('00202bc4-47d1-11e9-b210-d663bd873d22','Dania główne', 2, 'RES-4b545bf4-47c4-11e9-b210-d663bd873d92');

INSERT IGNORE INTO `CategoryEntity`(`id`, `name`, `orderOnList`, `restaurantReference`) VALUES
('00202bc4-47d1-11e9-b210-d663bd873d32','Napoje', 3, 'RES-4b545bf4-47c4-11e9-b210-d663bd873d92');


INSERT IGNORE INTO `CategoryEntity`(`id`, `name`, `orderOnList`, `restaurantReference`) VALUES
('00202bc4-47d1-11e9-b210-d663bd873d13','Przystawki', 0, 'RES-4b545bf4-47c4-11e9-b210-d663bd873d93');

INSERT IGNORE INTO `CategoryEntity`(`id`, `name`, `orderOnList`, `restaurantReference`) VALUES
('00202bc4-47d1-11e9-b210-d663bd873d23','Zupy', 1, 'RES-4b545bf4-47c4-11e9-b210-d663bd873d93');

INSERT IGNORE INTO `CategoryEntity`(`id`, `name`, `orderOnList`, `restaurantReference`) VALUES
('00202bc4-47d1-11e9-b210-d663bd873d33','Dania główne', 2, 'RES-4b545bf4-47c4-11e9-b210-d663bd873d93');

INSERT IGNORE INTO `CategoryEntity`(`id`, `name`, `orderOnList`, `restaurantReference`) VALUES
('00202bc4-47d1-11e9-b210-d663bd873d43','Napoje', 3, 'RES-4b545bf4-47c4-11e9-b210-d663bd873d93');



INSERT IGNORE INTO `CategoryEntity`(`id`, `name`, `orderOnList`, `restaurantReference`) VALUES
('00202bc4-47d1-11e9-b210-d663bd873d14','Przystawki', 0, 'RES-4b545bf4-47c4-11e9-b210-d663bd873d94');

INSERT IGNORE INTO `CategoryEntity`(`id`, `name`, `orderOnList`, `restaurantReference`) VALUES
('00202bc4-47d1-11e9-b210-d663bd873d24','Zupy', 1, 'RES-4b545bf4-47c4-11e9-b210-d663bd873d94');

INSERT IGNORE INTO `CategoryEntity`(`id`, `name`, `orderOnList`, `restaurantReference`) VALUES
('00202bc4-47d1-11e9-b210-d663bd873d34','Dania główne', 2, 'RES-4b545bf4-47c4-11e9-b210-d663bd873d94');

INSERT IGNORE INTO `CategoryEntity`(`id`, `name`, `orderOnList`, `restaurantReference`) VALUES
('00202bc4-47d1-11e9-b210-d663bd873d44','Napoje', 3, 'RES-4b545bf4-47c4-11e9-b210-d663bd873d94');



INSERT IGNORE INTO `CategoryEntity`(`id`, `name`, `orderOnList`, `restaurantReference`) VALUES
('00202bc4-47d1-11e9-b210-d663bd873d15','Przystawki', 0, 'RES-4b545bf4-47c4-11e9-b210-d663bd873d95');

INSERT IGNORE INTO `CategoryEntity`(`id`, `name`, `orderOnList`, `restaurantReference`) VALUES
('00202bc4-47d1-11e9-b210-d663bd873d25','Zupy', 1, 'RES-4b545bf4-47c4-11e9-b210-d663bd873d95');

INSERT IGNORE INTO `CategoryEntity`(`id`, `name`, `orderOnList`, `restaurantReference`) VALUES
('00202bc4-47d1-11e9-b210-d663bd873d35','Dania główne', 2, 'RES-4b545bf4-47c4-11e9-b210-d663bd873d95');

INSERT IGNORE INTO `CategoryEntity`(`id`, `name`, `orderOnList`, `restaurantReference`) VALUES
('00202bc4-47d1-11e9-b210-d663bd873d45','Napoje', 3, 'RES-4b545bf4-47c4-11e9-b210-d663bd873d95');


# PRODUCTS
INSERT IGNORE INTO `ProductEntity`(`id`, `available`, `description`, `name`, `photoUrl`, `price`, `restaurantReference`, `category_id`, `createdAt`, `updatedAt`) VALUES
('fewfewff-47d1-11e9-b210-d663bd873d93', 1, 'Przepyszna potrawa', 'Bagietka z masłem czosnkowym', null, 12.50, 'RES-4b545bf4-47c4-11e9-b210-d663bd873d91', '00202bc4-47d1-11e9-b210-d663bd873111', now(), now());

INSERT IGNORE INTO `ProductEntity`(`id`, `available`, `description`, `name`, `photoUrl`, `price`, `restaurantReference`, `category_id`, `createdAt`, `updatedAt`) VALUES
('afsafsa-47d1-2343-b210-d663bd873d93', 1, 'Przepyszna potrawa', 'Krem z kopru', null, 12.50, 'RES-4b545bf4-47c4-11e9-b210-d663bd873d91', '00202bc4-47d1-11e9-b210-d663bd873111', '2019-04-17 02:00:00.000000', '2019-04-17 02:00:00.000000');


INSERT IGNORE INTO `ProductEntity`(`id`, `available`, `description`, `name`, `photoUrl`, `price`, `restaurantReference`, `category_id`, `createdAt`, `updatedAt`) VALUES
('xsaxzsvb-4324-11e9-2342-d663bd873d93', 1, 'Przepyszna potrawa', 'Zupka z pomidorków', null, 12.50, 'RES-4b545bf4-47c4-11e9-b210-d663bd873d91', '00202bc4-47d1-11e9-b210-d663bd873d11', '2019-04-17 02:00:00.000000', '2019-04-17 02:00:00.000000');

INSERT IGNORE INTO `ProductEntity`(`id`, `available`, `description`, `name`, `photoUrl`, `price`, `restaurantReference`, `category_id`, `createdAt`, `updatedAt`) VALUES
('vdsvdss-4324-11e9-2fwergt-d663bd873d93', 1, 'Przepyszna potrawa', 'Barszczyk', null, 12.50, 'RES-4b545bf4-47c4-11e9-b210-d663bd873d91', '00202bc4-47d1-11e9-b210-d663bd873d11', '2019-04-17 02:00:00.000000', '2019-04-17 02:00:00.000000');


INSERT IGNORE INTO `ProductEntity`(`id`, `available`, `description`, `name`, `photoUrl`, `price`, `restaurantReference`, `category_id`, `createdAt`, `updatedAt`) VALUES
('jhkljll-4324-11e9-2342-d663bd873d93', 1, 'Przepyszna potrawa', 'Ziemniaczki ze stekiem i buraczkami', null, 12.50, 'RES-4b545bf4-47c4-11e9-b210-d663bd873d91', '00202bc4-47d1-11e9-b210-d663bd873d21', '2019-04-17 02:00:00.000000', '2019-04-17 02:00:00.000000');

INSERT IGNORE INTO `ProductEntity`(`id`, `available`, `description`, `name`, `photoUrl`, `price`, `restaurantReference`, `category_id`, `createdAt`, `updatedAt`) VALUES
('axaasd-4324-11e9-2fwergt-d663bd873d93', 1, 'Przepyszna potrawa', 'Ryba z frytkami', null, 12.50, 'RES-4b545bf4-47c4-11e9-b210-d663bd873d91', '00202bc4-47d1-11e9-b210-d663bd873d21', '2019-04-17 02:00:00.000000', '2019-04-17 02:00:00.000000');



INSERT IGNORE INTO `ProductEntity`(`id`, `available`, `description`, `name`, `photoUrl`, `price`, `restaurantReference`, `category_id`, `createdAt`, `updatedAt`) VALUES
('b6c01952-4324-hgjj-2342-d663bd873d93', 1, 'Woda', 'Woda', null, 12.50, 'RES-4b545bf4-47c4-11e9-b210-d663bd873d91', '00202bc4-47d1-11e9-b210-d663bd873d31', '2019-04-17 02:00:00.000000', '2019-04-17 02:00:00.000000');

INSERT IGNORE INTO `ProductEntity`(`id`, `available`, `description`, `name`, `photoUrl`, `price`, `restaurantReference`, `category_id`, `createdAt`, `updatedAt`) VALUES
('b6c01952-4324-j56jj-2fwergt-d663bd873d93', 1, 'Herbata', 'Herbata', null, 12.50, 'RES-4b545bf4-47c4-11e9-b210-d663bd873d91', '00202bc4-47d1-11e9-b210-d663bd873d31', '2019-04-17 02:00:00.000000', '2019-04-17 02:00:00.000000');
