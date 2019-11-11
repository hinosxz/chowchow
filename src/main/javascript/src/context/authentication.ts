import { createContext, useContext } from 'react';

import { AuthContextType } from 'lib/types';

export const AuthContext = createContext<AuthContextType>({
  isAuthenticated: false,
  setIsAuthenticated: () => {},
});

export function useAuth() {
  return useContext(AuthContext);
}
