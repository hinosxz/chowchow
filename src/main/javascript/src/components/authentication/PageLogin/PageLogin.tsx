import * as React from 'react';
import { Collapse } from 'antd';

import { LoginForm } from 'components/authentication/LoginForm/LoginForm';

import './page-login.scss';

const BLOCK = 'authentication_page-login';
const { Panel } = Collapse;

enum Panels {
  LOGIN = 'login',
  REGISTER = 'register'
}

export const PageLogin: React.FunctionComponent = () => (
  <Collapse className={BLOCK} accordion defaultActiveKey={Panels.LOGIN}>
    <Panel header="Login" key={Panels.LOGIN}>
      <LoginForm />
    </Panel>
    <Panel header="Register" key={Panels.REGISTER}>
        Register
    </Panel>
  </Collapse>
);
