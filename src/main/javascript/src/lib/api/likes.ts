import { api } from './api';

type PostLikesResponse = boolean;

export function postLikes(id: number) {
  return api.post('likes', { json: { show: { id } } }).json<PostLikesResponse>();
}
