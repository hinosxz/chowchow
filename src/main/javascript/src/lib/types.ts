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
