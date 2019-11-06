export interface SearchShow {
  original_name: string;
  genre_ids: number[];
  name: string;
  popularity: number;
  origin_country: string[];
  vote_count: number;
  first_air_date: string;
  backdrop_path: string;
  original_language: string;
  id: number;
  vote_average: number;
  overview: string;
  poster_path: string;
}

export interface AuthContextType {
  isAuthenticated: boolean;
  setIsAuthenticated: (isAuthenticated: boolean) => void;
}

interface CreatedBy {
  id: number;
  name: string;
  gender: number;
  profile_path: string;
}

interface Genre {
  id: number;
  name: string;
}

interface Network {
  id: number;
  logo_path: string;
  name: string;
  origin_country: string;
}

interface Season {
  air_date: string;
  episode_count: number;
  id: number;
  name: string;
  overview: string;
  poster_path: string;
  season_number: number;
}

interface ProductionCompany {
  id: number;
  name: string;
  logo_path: string;
  origin_country: string;
}

export interface Episode {
  air_date: string;
  episode_number: number;
  id: number;
  name: string;
  overview: string;
  production_code: string;
  season_number: number;
  show_id: number;
  still_path: string;
  vote_average: number;
  vote_count: number;
}

export interface Show {
  backdrop_path: string;
  created_by: CreatedBy[];
  episode_run_time: number[];
  first_air_date: string;
  genres: Genre[];
  homepage: string;
  id: number;
  in_production: boolean;
  languages: string[];
  last_air_date: string;
  last_episode_to_air: Episode;
  name: string;
  next_episode_to_air: Episode;
  networks: Network[];
  number_of_episodes: number;
  number_of_seasons: number;
  origin_country: string[];
  origin_language: string;
  original_name: string;
  overview: string;
  popularity: number;
  poster_path: string;
  production_companies: ProductionCompany[];
  seasons: Season[];
  status: string;
  type: string;
  vote_average: number;
  vote_count: number;
}

export interface Like {
  mark: 0 | 1 | 2 | null;
  show: Show;
}

interface CreatedBy {
  id: number;
  name: string;
  gender: number;
  profilePath: string;
}

interface Genre {
  id: number;
  name: string;
}

interface Network {
  id: number;
  logoPath: string;
  name: string;
  originCountry: string;
}

interface Season {
  airDate: [number, number, number];
  episodeCount: number;
  id: number;
  name: string;
  overview: string;
  posterPath: string;
  seasonNumber: number;
}

interface ProductionCompany {
  id: number;
  name: string;
  logoPath: string;
  originCountry: string;
}

export interface Episode {

  airDate: [number, number, number];
  episodeNumber: number;
  id: number;
  name: string;
  overview: string;
  productionCode: string;
  seasonNumber: number;
  showId: number;
  stillPath: string;
  voteAverage: number;
  voteCount: number;
}

export interface Show {
  backdropPath: string;
  createdBy: CreatedBy[];
  episodeRunTime: number[];
  firstAirDate: [number, number, number];
  genres: Genre[];
  homepage: string;
  id: number;
  inProduction: boolean;
  languages: string[];
  lastAirDate: [number, number, number];
  lastEpisodeToAir: Episode;
  name: string;
  nextEpisodeToAir: Episode;
  networks: Network[];
  numberOfEpisodes: number;
  numberOfSeasons: number;
  originCountry: string[];
  originalLanguage: string;
  originalName: string;
  overview: string;
  popularity: number;
  posterPath: string;
  productionCompanies: ProductionCompany[];
  seasons: Season[];
  status: string;
  type: string;
  voteAverage: number;
  voteCount: number;
}

export interface Like {
  mark: number;
  show: Show;
}

export interface Alert {
  showId: number;
  showName: string;
  nextEpisodeToAir: Episode;
}
