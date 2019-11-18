import { api } from './api';

export function postLikes(id: number) {
  return api.post('likes', { json: { show: { id } } });
}

export function putLikes(id: number, mark: 0 | 1 | 2) {
  return api.put(`likes/${id}`, { json: { mark } });
}
