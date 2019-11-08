import React from 'react';
import { Redirect, Route } from 'react-router-dom';

import { RoutePath } from 'lib/constants';

// A wrapper for <Route> that redirects to the login screen if you're not yet authenticated.
export const PrivateRoute: React.FunctionComponent = ({ children, ...rest }) => (
  <Route
    // Disabling this rule here because we need all route props
    // eslint-disable-next-line react/jsx-props-no-spreading
    {...rest}
    render={({ location }) => (true ? (
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
