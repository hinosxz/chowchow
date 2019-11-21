import * as React from 'react';
import { Collapse } from 'antd';

import { LoginForm } from 'components/authentication/LoginForm/LoginForm';
import { RegistrationForm } from 'components/authentication/RegistrationForm/RegistrationForm';

import './page-login.scss';

const BLOCK = 'authentication_page-login';
const { Panel } = Collapse;

enum Panels {
  LOGIN = 'login',
  REGISTER = 'register'
}

export const PageLogin: React.FunctionComponent = () => (
  <>
  <div className={`${BLOCK}__logo-wrapper`} >
      <img src="./chowchow-10.png" className={`${BLOCK}__logo`}/>
  </div>
  <Collapse className={BLOCK} accordion defaultActiveKey={Panels.LOGIN}>
    <Panel header="Login" key={Panels.LOGIN}>
      <LoginForm />
    </Panel>
    <Panel header="Register" key={Panels.REGISTER}>
      <RegistrationForm />
    </Panel>
  </Collapse>
  </>
);
