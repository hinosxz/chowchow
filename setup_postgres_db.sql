DROP TABLE IF EXISTS Users CASCADE;
CREATE TABLE Users (
  id SERIAL,
  username TEXT NOT NULL UNIQUE,
  password TEXT NOT NULL ,
  subscription_type INTEGER ,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS likes CASCADE;
CREATE TABLE likes (
  user_id SERIAL NOT NULL,
  show_id SERIAL NOT NULL,
  mark INTEGER,
  FOREIGN KEY (user_id) REFERENCES Users(id) ON UPDATE CASCADE
);

INSERT INTO public.Users (id, username, password, subscription_type)
VALUES
(1, 'User_1', 'password_1', NULL),
(2, 'User_2', 'password_2', 0),
(3, 'User_3', 'password_3', 1);

INSERT INTO public.likes (user_id, show_id, mark)
VALUES
(1, 60574, 1),
(1, 62688, 0),
(1, 71789, 2),
(2, 60574, 2);

