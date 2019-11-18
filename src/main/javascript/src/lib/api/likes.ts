import { api } from './api';

export function postLikes(id: number) {
  return api.post('likes', { json: { show: { id } } });
}
