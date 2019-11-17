import { api } from './api';

type PostRegisterResponse = boolean;

export function postRegister(username: string, password: string) {
  return api.post('register', { json: { username, password } }).json<PostRegisterResponse>();
}
