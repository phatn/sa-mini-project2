INSERT INTO category(`name`)
VALUES ('ELECTRONICS'),
       ('FOOD'),
       ('CLOTHES');

INSERT INTO product(`id`, `name`, `vendor`, `category_id`, `quantity`)
VALUES (1, 'Television', 'Samsung', 1, '100'),
       (2, 'Android box', 'Google', 1, '50'),
       (3, 'Goldfish Cheddar Crackers', 'Goldfish', 2, '75'),
       (4, 'Kraft Original Macaroni', 'Kraft', 2, '30'),
       (5, 'Satin Effect Shirt', 'Zara', 3, '200'),
       (6, 'Cotton Shirt', 'H&M', 3, '300');

