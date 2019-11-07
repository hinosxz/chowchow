import * as React from 'react';
import { Card } from 'antd';

import { LoginForm } from '../LoginForm/LoginForm';
import './page-login.scss';

const BLOCK = 'page-login';

export interface AuthenticationState {
  isAuthenticated: boolean
}

interface PageLoginProps {
  setAuthenticationState: (value: AuthenticationState) => void;
}

export const PageLogin: React.FunctionComponent<PageLoginProps> = ({ setAuthenticationState }) => (
  <Card className={`${BLOCK}__form`}>
    <LoginForm />
  </Card>
);
