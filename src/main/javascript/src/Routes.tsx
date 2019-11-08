import * as React from 'react';
import {
  BrowserRouter as Router, Switch, Route,
} from 'react-router-dom';

import { AuthenticationState } from 'lib/types';
import { RoutePath } from 'lib/constants';

import { PageHome } from './components/home/PageHome/PageHome';
import { PageLogin } from './components/authentication/PageLogin/PageLogin';
import { PageLogout } from './components/authentication/PageLogout/PageLogout';
import { AppLayout } from './components/ui/AppLayout/AppLayout';
import { PageShow } from './components/show/PageShow/PageShow';

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
          <AppLayout>
            <Route exact path={RoutePath.home}>
              <PageHome />
            </Route>
            <Route path={`${RoutePath.shows}/:id`}>
              <PageShow />
            </Route>
          </AppLayout>
        </Route>
      </Switch>
    </Router>
  );
};
