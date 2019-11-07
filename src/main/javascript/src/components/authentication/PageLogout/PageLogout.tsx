import * as React from 'react';

import { AuthenticationState } from '../PageLogin/PageLogin';

interface PageLogoutProps {
  setAuthenticationState: (value: AuthenticationState) => void;
}

export const PageLogout: React.FunctionComponent<PageLogoutProps> = ({ setAuthenticationState }) => null;
