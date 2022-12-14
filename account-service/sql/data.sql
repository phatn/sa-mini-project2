use `account-db`;
INSERT INTO account(`first_name`, `last_name`, `email`,`preferred_payment`, `preferred_address`)
VALUES ('Admin', 'User', 'admin@gmail.com', '1', '1'),
       ('Phat', 'Nguyen', 'phatnguyen@outlook.com', '2', '2'),
       ('Anh', 'Nguyen', 'tuananh@gmail.com', '3', '3');

INSERT INTO address(`street`, `city`, `zip_code`, `owner_id`)
VALUES ('414 NB St', 'Fairfield', '52556', '1'),
       ('1000 Main', 'Fairfield', '52557', '2'),
       ('505 East', 'Fairfield', '52557', '3');

INSERT INTO paymentDto(`type`, `bank_account`, `bank_routing`, `bank_name`, `owner_id`)
VALUES ('BANK', '123456', '777777', 'Tuan Anh', '1');

INSERT INTO paymentDto(`type`, `card_expires`, `card_number`, `card_security_code`, `owner_id`)
VALUES ('CREDIT', '01-2025', '999 999 999', '123', '2');

INSERT INTO paymentDto(`type`, `paypal_number`, `paypal_token`, `owner_id`)
VALUES ('PAYPAL', '123456789', 'abc123', '3');
