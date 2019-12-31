INSERT INTO USERS (id, username, created_on, modified_on)
VALUES (1, 'Cortázar', '2018-08-08 21:54:00', '2018-08-08 21:54:00'),
       (2, 'Borges', '2018-08-08 21:54:00', '2018-08-08 21:54:00'),
       (3, 'García Márquez', '2018-08-08 21:54:00', '2018-08-08 21:54:00'),
       (4, 'Vargas Llosa', '2018-08-08 21:54:00', '2018-08-08 21:54:00'),
       (5, 'Amado', '2018-08-08 21:54:00', '2018-08-08 21:54:00');

INSERT INTO REL_USER_USER (user_id, following_user_id)
VALUES  (1,2),
        (1,3),
        (1,4),
        (1,5),
        (2,1),
        (2,3),
        (2,4),
        (2,5),
        (3,2),
        (3,1),
        (3,4),
        (3,5);

INSERT INTO POSTS (id, postName, created_on, modified_on, price, description, quantityAvailable, user_id)
VALUES  (1, 'Bike', '2018-08-08 21:50:00', '2018-08-08 21:54:00', 10,'Bike for men', 3, 2),
        (2, 'Bike', '2018-08-08 21:51:00', '2018-08-08 21:54:00', 10,'Bike for women', 3, 3),
        (3, 'Guitar', '2018-08-08 21:52:00', '2018-08-08 21:54:00', 10,'Electric guitar', 3, 4),
        (4, 'Table', '2018-08-08 21:53:00', '2018-08-08 21:54:00', 10,'Wood table', 3, 5),
        (5, 'CellPhone', '2018-08-08 21:54:00', '2018-08-08 21:54:00', 10,'Alcatel xl-a1', 3, 1);
