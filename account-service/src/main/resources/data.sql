INSERT INTO account(`first_name`, `last_name`, `email`,`preferred_payment`)
VALUES ('Oliver', 'Nguyen', 'oliver@gmail.com', 'BANK'),
       ('Lionel', 'Messi', 'messi@gmail.com', 'CARD'),
       ('Cristiano', 'Ronaldo', 'ronaldo@gmail.com', 'PAYPAL');

INSERT INTO address(`street`, `city`, `zip_code`, `owner_id`)
VALUES ('414 NB St', 'Fairfield', '52556', '1'),
       ('1000 Main', 'Fairfield', '52557', '1');

INSERT INTO payment(`type`, `bank_account`, `bank_routing`, `bank_name`, `owner_id`)
VALUES ('BANK', '123456', '777777', 'Tuan Anh', '1');

INSERT INTO payment(`type`, `card_expires`, `card_number`, `card_security_code`, `owner_id`)
VALUES ('CARD', '01-2025', '999 999 999', '123', '2');

INSERT INTO payment(`type`, `paypal_number`, `paypal_token`, `owner_id`)
VALUES ('PAYPAL', '123456789', 'abc123', '3');
