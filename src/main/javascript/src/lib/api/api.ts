import ky from 'ky';

export const api = ky.extend({ prefixUrl: 'http://localhost:8080' });
