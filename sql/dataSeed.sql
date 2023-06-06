USE tpjava2;
INSERT INTO category (name) VALUES ('Bureautique'),('Développement'),('Design');
INSERT INTO level (name) VALUES ('Facile'),('Moyen'),('Difficile');

INSERT INTO training (duration, name, online, price, start_at, id_category, id_level)
VALUES
(3, 'Docker', 0, 2000, 2020-05-05, 2, 3),
(5, 'Spring', 1, 5000, 2020-06-10, 2, 3),
(15, 'Photoshop', 0, 15000, 2020-05-02, 3, 1);

INSERT INTO students (first_name, last_name)
VALUES
    ('Olivier', 'Carglass'),
    ('Michel', 'Lambert'),
    ('Justine', 'Trion'),
    ('Marc', 'Aubert');

INSERT INTO former (first_name, last_name)
VALUES
    ('Clémence', 'Breton'),
    ('Christian', 'Demers');

INSERT INTO training_to_former (id_training, id_former)
VALUES
    (1,1),
    (2,2);

INSERT INTO training_to_students (id_training, id_students)
VALUES
    (1, 1),
    (2, 1),
    (3, 1),
    (1, 2),
    (2, 2);
