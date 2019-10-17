DROP TABLE IF EXISTS Users CASCADE;
CREATE TABLE Users (
  id SERIAL,
  username TEXT NOT NULL UNIQUE,
  password TEXT NOT NULL ,
  type TEXT NOT NULL,
  subscription_type TEXT,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS show_ratings CASCADE;
CREATE TABLE show_ratings (
  user_id SERIAL NOT NULL,
  show_id SERIAL NOT NULL,
  mark INTEGER,
  FOREIGN KEY (user_id) REFERENCES Users(id) ON UPDATE CASCADE
);

INSERT INTO public.Users (id, username, password, type, subscription_type)
VALUES
(1, 'User_1', 'password_1', 'USER', NULL),
(2, 'User_2', 'password_2', 'PREMIUM_USER', 'BASIC'),
(3, 'User_3', 'password_3', 'PREMIUM_USER', 'GOLD');

INSERT INTO public.show_ratings (user_id, show_id, mark)
VALUES
(1, 60574, 1),
(1, 62688, 4),
(1, 71789, 2),
(2, 60574, 2);

