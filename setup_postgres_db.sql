DROP TABLE IF EXISTS Shows CASCADE;
CREATE TABLE Shows (
  id SERIAL,
  tmdb_id INTEGER NOT NULL UNIQUE,
  name TEXT NOT NULL,
  year INTEGER NOT NULL,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS Users CASCADE;
CREATE TABLE Users (
  id SERIAL,
  username TEXT NOT NULL UNIQUE,
  password TEXT NOT NULL ,
  type TEXT NOT NULL,
  subscription_type TEXT,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS ShowRatings CASCADE;
CREATE TABLE ShowRatings (
  user_id SERIAL NOT NULL,
  show_id SERIAL NOT NULL,
  mark INTEGER,
  FOREIGN KEY(user_id) REFERENCES Users(id) ON UPDATE CASCADE,
  FOREIGN KEY(show_id) REFERENCES Shows(id) ON UPDATE CASCADE
);

INSERT INTO public.Shows (id, tmdb_id, name, year)
VALUES
(1, 60574, 'Peaky Blinders', 2018),
(2, 62688, 'Supergirl', 2019),
(3, 71789, 'Seal TEAM', 2008);

INSERT INTO public.Users (id, username, password, type, subscription_type)
VALUES
(1, 'User_1', 'password_1', 'USER', NULL),
(2, 'User_2', 'password_2', 'PREMIUM_USER', 'BASIC'),
(3, 'User_3', 'password_3', 'PREMIUM_USER', 'GOLD');


INSERT INTO public.ShowRatings (id, user_id, show_id, mark)
VALUES
(1, 1, 1, 1),
(2, 2, 1, 2);

