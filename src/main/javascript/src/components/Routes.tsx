import * as React from 'react';
import {
  BrowserRouter as Router, Switch, Route,
} from 'react-router-dom';

import { PageHome } from './home/PageHome/PageHome';
import { RoutePath } from './RoutePath';
import { PageLogin } from './authentication/PageLogin/PageLogin';
import { PageLogout } from './authentication/PageLogout/PageLogout';
import { AuthenticationState } from './authentication/types';

export const Routes = () => {
  const [authenticationState, setAuthenticationState] = React.useState<AuthenticationState>({
    isAuthenticated: false,
  });
  console.log(authenticationState);
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
        <Route>
          <PageHome />
        </Route>
      </Switch>
    </Router>
  );
};
