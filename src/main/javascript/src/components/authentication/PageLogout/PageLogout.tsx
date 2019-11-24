import * as React from 'react';
import {
  Button, Card, Col, Icon, Row,
} from 'antd';
import { NavLink } from 'react-router-dom';

import { postLogout } from 'lib/api/login';
import { useAuth } from 'context/authentication';
import { RoutePath } from 'lib/constants';

export const PageLogout: React.FunctionComponent = () => {
  const { isAuthenticated, setIsAuthenticated } = useAuth();
  if (isAuthenticated) {
    postLogout().then(() => setIsAuthenticated(false));
  }
  return (
    <Card title="Logging out" loading={isAuthenticated}>
      {isAuthenticated ? null : (
        <>
          <Row gutter={[32, 16]}>
            <Col>You are logged out</Col>
          </Row>
          <Row gutter={[32, 16]}>
            <Col>
              <NavLink to={RoutePath.login}>
                <Button type="primary">
                  Login
                  <Icon type="arrow-right" />
                </Button>
              </NavLink>
            </Col>
          </Row>
        </>
      )}
    </Card>
  );
};
