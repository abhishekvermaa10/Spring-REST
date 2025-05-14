INSERT INTO pet_table (name, gender, type)
VALUES
('Fluffy', 'F', 'CAT'),
('Luna', 'F', 'CAT');

INSERT INTO domestic_pet_table (id, date_of_birth)
VALUES
(1, '2018-07-26');

INSERT INTO wild_pet_table (id, place_of_birth)
VALUES
(2, 'Jim Corbett National Park');

INSERT INTO owner_table (first_name, last_name, gender, city, state, mobile_number, email_id, pet_id)
VALUES
('John', 'Doe', 'M', 'Hyderabad', 'Andhra Pradesh', '9009009001', 'john.doe@scaleupindia.com', 1),
('William', 'Ward', 'M', 'Bhubaneswar', 'Odisha', '9009009002', 'william.ward@scaleupindia.com', 2);
