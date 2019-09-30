DROP TABLE IF EXISTS Shows CASCADE;
CREATE TABLE Shows (
  id SERIAL,
  traktId TEXT,
  name TEXT,
  PRIMARY KEY (id)
);
INSERT INTO public.Shows (id, traktId, name)
VALUES
(1, 'trakId_1', 'Peaky Blinders'),
(2, 'trakId_2', 'Im a Chowchow');