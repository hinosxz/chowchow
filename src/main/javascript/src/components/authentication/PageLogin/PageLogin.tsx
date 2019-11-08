import * as React from 'react';
import { Card } from 'antd';

import { LoginForm } from 'components/authentication/LoginForm/LoginForm';
import { AuthenticationState } from 'lib/types';

import './page-login.scss';

const BLOCK = 'authentication__page-login';

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
