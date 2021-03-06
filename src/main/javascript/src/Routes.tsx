import * as React from 'react';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';

import { AuthContext } from 'context/authentication';
import { RoutePath } from 'lib/constants';

import { PageHome } from './components/home/PageHome/PageHome';
import { PageLogin } from './components/authentication/PageLogin/PageLogin';
import { PageLogout } from './components/authentication/PageLogout/PageLogout';
import { AppLayout } from './components/ui/AppLayout/AppLayout';
import { PageShow } from './components/show/PageShow/PageShow';
import { PrivateRoute } from './components/authentication/PrivateRoute/PrivateRoute';
import { useBooleanStateWithSessionStorage } from './lib/hooks';
import { PageShowList } from './components/show-list/PageShowList/PageShowList';

export const Routes = () => {
  const [
    isAuthenticated,
    setIsAuthenticated,
  ] = useBooleanStateWithSessionStorage('isAuthenticated');

  return (
    <AuthContext.Provider value={{ isAuthenticated, setIsAuthenticated }}>
      <Router>
        <Switch>
          <Route path={RoutePath.login}>
            <PageLogin />
          </Route>
          <Route path={RoutePath.logout}>
            <PageLogout />
          </Route>
          <PrivateRoute path={RoutePath.home}>
            <AppLayout>
              <Route exact path={RoutePath.home}>
                <PageHome />
              </Route>
              <Route exact path={RoutePath.shows}>
                <PageShowList />
              </Route>
              <Route exact path={`${RoutePath.shows}/:id`}>
                <PageShow />
              </Route>
            </AppLayout>
          </PrivateRoute>
        </Switch>
      </Router>
    </AuthContext.Provider>
  );
};
