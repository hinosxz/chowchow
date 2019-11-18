import { api } from './api';

type PostRegisterResponse = {
  id: number;
  username: string;
  subscriptionType: number;
};

export function postRegister(username: string, password: string) {
  return api.post('register', { json: { username, password } }).json<PostRegisterResponse>();
}
