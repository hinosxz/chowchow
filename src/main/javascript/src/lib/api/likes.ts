import { api } from './api';

type PostLikesResponse = boolean;

export function postLikes(id: number) {
  return api.post('likes', { json: { show: { id } } }).json<PostLikesResponse>();
}

type PutLikesResponse = boolean;

export function putLikes(id: number, mark: 0 | 1 | 2) {
  return api.put(`likes/${id}`, { json: { mark } }).json<PutLikesResponse>();
}
