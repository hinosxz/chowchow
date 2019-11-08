import * as React from 'react';
import { Card } from 'antd';

import { AuthenticationState } from '../types';
import { postLogout } from '../../../lib/api/login';

interface PageLogoutProps {
  isAuthenticated: boolean;
  setAuthenticationState: (value: AuthenticationState) => void;
}

export const PageLogout: React.FunctionComponent<PageLogoutProps> = ({
  isAuthenticated,
  setAuthenticationState,
}) => {
  if (isAuthenticated) {
    postLogout().then(() => setAuthenticationState({ isAuthenticated: false }));
  }
  return (
    <Card title="Logging out" loading={isAuthenticated}>
      {isAuthenticated ? false : 'You are logged out'}
    </Card>
  );
};
