import * as React from 'react';
import { Card } from 'antd';

import { LoginForm } from '../LoginForm/LoginForm';
import './page-login.scss';
import { AuthenticationState } from '../types';

const BLOCK = 'page-login';

interface PageLoginProps {
  authenticationState: AuthenticationState,
  setAuthenticationState: (value: AuthenticationState) => void;
}

export const PageLogin: React.FunctionComponent<PageLoginProps> = ({
  authenticationState,
  setAuthenticationState,
}) => (
  <Card
    className={`${BLOCK}__form`}
  >
    <LoginForm
      authenticationState={authenticationState}
      setAuthenticationState={setAuthenticationState}
    />
  </Card>
);
