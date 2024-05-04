INSERT INTO company (name)
VALUES ('Google'),
       ('Meta'),
       ('Amazon');

INSERT INTO company_locales (company_id, lang, description)
VALUES ((SELECT id FROM company WHERE name = 'Google'), 'en', 'Google description'),
       ((SELECT id FROM company WHERE name = 'Google'), 'ru', 'Google описание'),
       ((SELECT id FROM company WHERE name = 'Meta'), 'en', 'Meta description'),
       ((SELECT id FROM company WHERE name = 'Meta'), 'ru', 'Meta описание'),
       ((SELECT id FROM company WHERE name = 'Amazon'), 'en', 'Amazon description'),
       ((SELECT id FROM company WHERE name = 'Amazon'), 'ru', 'Amazon описание');

INSERT INTO users (birth_date, firstname, lastname, role, username, company_id)
VALUES ('1990-01-10', 'Alex', 'Wood', 'ADMIN', 'alex@gmail.com', (SELECT id FROM company WHERE name = 'Google')),
       ('1995-10-19', 'Mika', 'Freeds', 'USER', 'mika@gmail.com', (SELECT id FROM company WHERE name = 'Google')),
       ('2001-12-23', 'Stive', 'Stivenson', 'USER', 'stive@gmail.com', (SELECT id FROM company WHERE name = 'Meta')),
       ('1984-03-14', 'Vlad', 'Zonchen', 'USER', 'vlad@gmail.com', (SELECT id FROM company WHERE name = 'Amazon')),
       ('1984-03-14', 'Kate', 'Smith', 'ADMIN', 'kate@gmail.com', (SELECT id FROM company WHERE name = 'Amazon'));

INSERT INTO payment (amount, receiver_id)
VALUES (100, (SELECT id FROM users WHERE username = 'alex@gmail.com')),
       (300, (SELECT id FROM users WHERE username = 'alex@gmail.com')),
       (500, (SELECT id FROM users WHERE username = 'alex@gmail.com')),
       (250, (SELECT id FROM users WHERE username = 'mika@gmail.com')),
       (600, (SELECT id FROM users WHERE username = 'mika@gmail.com')),
       (500, (SELECT id FROM users WHERE username = 'mika@gmail.com')),
       (400, (SELECT id FROM users WHERE username = 'stive@gmail.com')),
       (300, (SELECT id FROM users WHERE username = 'stive@gmail.com')),
       (500, (SELECT id FROM users WHERE username = 'vlad@gmail.com')),
       (700, (SELECT id FROM users WHERE username = 'vlad@gmail.com')),
       (340, (SELECT id FROM users WHERE username = 'vlad@gmail.com')),
       (440, (SELECT id FROM users WHERE username = 'kate@gmail.com')),
       (510, (SELECT id FROM users WHERE username = 'kate@gmail.com')),
       (630, (SELECT id FROM users WHERE username = 'kate@gmail.com'));

INSERT INTO chat (name)
VALUES ('rish'),
       ('java'),
       ('database');

INSERT INTO users_chat(user_id, chat_id)
VALUES ((SELECT id FROM users WHERE username = 'alex@gmail.com'), (SELECT id FROM chat WHERE name = 'rish')),
       ((SELECT id FROM users WHERE username = 'alex@gmail.com'), (SELECT id FROM chat WHERE name = 'rish')),
       ((SELECT id FROM users WHERE username = 'stive@gmail.com'), (SELECT id FROM chat WHERE name = 'rish')),
       ((SELECT id FROM users WHERE username = 'mika@gmail.com'), (SELECT id FROM chat WHERE name = 'java')),
       ((SELECT id FROM users WHERE username = 'stive@gmail.com'), (SELECT id FROM chat WHERE name = 'java')),
       ((SELECT id FROM users WHERE username = 'vlad@gmail.com'), (SELECT id FROM chat WHERE name = 'java')),
       ((SELECT id FROM users WHERE username = 'kate@gmail.com'), (SELECT id FROM chat WHERE name = 'java')),
       ((SELECT id FROM users WHERE username = 'mika@gmail.com'), (SELECT id FROM chat WHERE name = 'database')),
       ((SELECT id FROM users WHERE username = 'kate@gmail.com'), (SELECT id FROM chat WHERE name = 'database'));