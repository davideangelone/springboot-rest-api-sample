INSERT INTO USER (ID, EMAIL, USERNAME) VALUES (99, 'test@test.it', 'testuser');
INSERT INTO PAYMENT (ID, HASH, IMPORTO, TIMESTAMP, TIPO, USER_ID) VALUES (100,'HBpF5kqTPc9HS5kxzFV3lIpxZgR5HQYlDdwARJ8GktI=', '98765', '2021-05-17 09:12:45.106', 'cash', 99);
INSERT INTO PAYMENT (ID, HASH, IMPORTO, TIMESTAMP, TIPO, USER_ID) VALUES (101,'m9BE9wygPYI39yAhHR8uDeQ5z6AwZ1rZUctX36ffM68=', '12345', '2021-05-17 09:51:53.727', 'carta', 99);
INSERT INTO PAYMENT (ID, HASH, IMPORTO, TIMESTAMP, TIPO, USER_ID) VALUES (102,'Z00VTqRaR6gon3j2wHwn0GO1A222zJp0vHY+Kg9oFTM=', '67890', '2021-05-17 09:52:00.769', 'bonifico', 99);