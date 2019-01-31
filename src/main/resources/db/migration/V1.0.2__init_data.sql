-- //pass: 'admin' for Vitalii Radchenko, 'user' for Some User
INSERT INTO users values (1, 'Vitalii', 'Radchenko', 'admin', '$2a$04$qkkrb7d.GklW5qD.RpmZuObsCESrIZCmIlW/Acfoi.G1jPWxruLWm');
INSERT INTO users values (2, 'Some', 'User', 'user', '$2a$04$SjF2pzY/NinDFB.0VOJ3.OtcsHzKiuefvikqMxOuuH1ybwksiRVyi');
INSERT INTO roles values (1, 'ROLE_ADMIN', 1), (2, 'ROLE_USER', 2), (3, 'ROLE_USER', 1);


INSERT INTO messages VALUES (1, 'm_one', 'tag1', 1), (2, 'm_two', 'tag2', 1);