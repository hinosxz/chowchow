import * as React from 'react';
import { Card } from 'antd';

import { postLogout } from 'lib/api/login';
import { useAuth } from 'context/authentication';

export const PageLogout: React.FunctionComponent = () => {
  const { isAuthenticated, setIsAuthenticated } = useAuth();
  if (isAuthenticated) {
    postLogout().then(() => setIsAuthenticated(false));
  }
  return (
    <Card title="Logging out" loading={isAuthenticated}>
      {isAuthenticated ? null : 'You are logged out'}
    </Card>
  );
};
