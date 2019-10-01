DROP TABLE IF EXISTS Shows CASCADE;
CREATE TABLE Shows (
  id SERIAL,
  trakt_id TEXT,
  name TEXT,
  PRIMARY KEY (id)
);
INSERT INTO public.Shows (id, trakt_id, name)
VALUES
(1, 'trakId_1', 'Peaky Blinders'),
(2, 'trakId_2', 'Im a Chowchow');