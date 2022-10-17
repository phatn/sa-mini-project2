use `product-db`;
INSERT INTO category(`name`)
VALUES ('ELECTRONICS'),
       ('FOOD'),
       ('CLOTHES');

INSERT INTO product(`id`, `name`, `vendor`, `category_id`, `quantity`, `price`)
VALUES (1, 'Television', 'Samsung', 1, '100', '700'),
       (2, 'Android box', 'Google', 1, '50', '200'),
       (3, 'Goldfish Cheddar Crackers', 'Goldfish', 2, '75', '20'),
       (4, 'Kraft Original Macaroni', 'Kraft', 2, '30', '30'),
       (5, 'Satin Effect Shirt', 'Zara', 3, '200', '40'),
       (6, 'Cotton Shirt', 'H&M', 3, '300', '29');

