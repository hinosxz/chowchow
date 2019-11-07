import ky from 'ky';
// @ts-ignore
import runtimeEnv from '@mars/heroku-js-runtime-env';

const ENV = runtimeEnv();
const API_URL = ENV.REACT_APP_API_URL as string;

export const api = ky.extend({ prefixUrl: API_URL, credentials: 'include' });
