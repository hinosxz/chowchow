import { SearchShow } from 'lib/types';

import { api } from './api';

interface GetSearchResponse {
  page: number;
  total_results: number;
  total_pages: number;
  results: SearchShow[];
}

export function getSearch(name: string) {
  const searchParams = new URLSearchParams({ name });
  return api.get('search', { searchParams }).json<GetSearchResponse>();
}
