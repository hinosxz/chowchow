import * as React from 'react';
import { Card } from 'antd';

import { LoginForm } from 'components/authentication/LoginForm/LoginForm';

import './page-login.scss';

const BLOCK = 'authentication__page-login';

export const PageLogin: React.FunctionComponent = () => (
  <Card
    className={`${BLOCK}__form`}
  >
    <LoginForm />
  </Card>
);
