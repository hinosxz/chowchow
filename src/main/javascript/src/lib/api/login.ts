import { api } from './api';

interface LoginResponse {
  success: boolean
}

export function postLogin(username: string, password: string) {
  return api.post('login', {
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8',
    },
    body: `username=${username}&password=${password}`,
  }).text();
}

export function postLogout() {
  return api.post('logout').json<LoginResponse>();
}
