import React from 'react';
import { Redirect, Route, RouteProps } from 'react-router-dom';

import { RoutePath } from 'lib/constants';
import { useAuth } from 'context/authentication';

// A wrapper for <Route> that redirects to the login screen if you're not yet authenticated.
export const PrivateRoute: React.FunctionComponent<RouteProps> = ({ children, ...rest }) => {
  const { isAuthenticated } = useAuth();
  return (
    <Route
      // Disabling this rule here because we need all route props
      // eslint-disable-next-line react/jsx-props-no-spreading
      {...rest}
      render={({ location }) => (isAuthenticated ? (
        children
      ) : (
        <Redirect
          to={{
            pathname: RoutePath.login,
            state: { from: location },
          }}
        />
      ))}
    />
  );
};
