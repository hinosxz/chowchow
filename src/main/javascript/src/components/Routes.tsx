import * as React from 'react';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';

import { PageHome } from './home/PageHome/PageHome';
import { RoutePath } from './RoutePath';
import { AuthenticationState, PageLogin } from './authentication/PageLogin/PageLogin';
import { PageLogout } from './authentication/PageLogout/PageLogout';

export const Routes = () => {
  const [authenticationState, setAuthenticationState] = React.useState<AuthenticationState>({
    isAuthenticated: false,
  });
  return (
    <Router>
      <Switch>
        <Route path={RoutePath.login}>
          <PageLogin setAuthenticationState={setAuthenticationState} />
        </Route>
        <Route path={RoutePath.logout}>
          <PageLogout setAuthenticationState={setAuthenticationState} />
        </Route>
        <Route path={RoutePath.home}>
          <PageHome />
        </Route>
      </Switch>
    </Router>
  );
};
