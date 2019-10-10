DROP TABLE IF EXISTS Shows CASCADE;
CREATE TABLE Shows (
  id SERIAL,
  trakt_id INTEGER,
  name TEXT,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS Users CASCADE;
CREATE TABLE Users (
  id SERIAL,
  username TEXT,
  password TEXT,
  PRIMARY KEY (id)
);

INSERT INTO public.Shows (id, trakt_id, name)
VALUES
(1, 60158, 'Peaky Blinders'),
(2, 99046, 'Supergirl'),
(3, 119142, 'Seal TEAM');

INSERT INTO public.Users (id, username, password)
VALUES
(1, 'User_1', 'password_1'),
(2, 'User_2', 'password_2');