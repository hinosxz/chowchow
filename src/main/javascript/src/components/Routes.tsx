import * as React from 'react';
import {
  BrowserRouter as Router, Switch, Route,
} from 'react-router-dom';

import { AuthenticationState } from 'lib/types';
import { RoutePath } from 'lib/constants';

import { PageHome } from './home/PageHome/PageHome';
import { PageLogin } from './authentication/PageLogin/PageLogin';
import { PageLogout } from './authentication/PageLogout/PageLogout';

export const Routes = () => {
  const [authenticationState, setAuthenticationState] = React.useState<AuthenticationState>({
    isAuthenticated: false,
  });
  return (
    <Router>
      <Switch>
        <Route path={RoutePath.login}>
          <PageLogin
            authenticationState={authenticationState}
            setAuthenticationState={setAuthenticationState}
          />
        </Route>
        <Route path={RoutePath.logout}>
          <PageLogout
            isAuthenticated={authenticationState.isAuthenticated}
            setAuthenticationState={setAuthenticationState}
          />
        </Route>
        <Route path={RoutePath.home}>
          <PageHome />
        </Route>
      </Switch>
    </Router>
  );
};
