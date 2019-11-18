import { api } from './api';

export function postLikes(id: number) {
  return api.post('likes', { json: { show: { id } } });
}

export function putLike(id: number, mark: 0 | 1 | 2) {
  return api.put(`likes/${id}`, { json: { mark } });
}

export function deleteLike(id: number) {
  return api.delete(`likes/${id}`);
}
