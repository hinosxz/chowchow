import React from 'react';
import { useLocation, Link } from 'react-router-dom';
import { Icon, Layout, Menu } from 'antd';

import { RoutePath } from 'lib/constants';

import './app-layout.scss';

const BLOCK = 'ui_app-layout';
const { Content, Footer, Sider } = Layout;

export const AppLayout: React.FunctionComponent = ({ children }) => {
  const { pathname } = useLocation();
  return (
    <Layout className={BLOCK} style={{ minHeight: '100vh' }}>
      <Sider collapsed>
        <div className={`${BLOCK}__logo-wrapper`}>
          <img
            src="./chowchow-10.png"
            className={`${BLOCK}__logo`}
            alt="logo"
          />
        </div>
        <Menu mode="inline" selectedKeys={[pathname]} theme="dark">
          <Menu.Item key={RoutePath.home}>
            <Link to={RoutePath.home}>
              <Icon type="home" />
              <span>Home</span>
            </Link>
          </Menu.Item>
          <Menu.Item key={RoutePath.shows}>
            <Link to={RoutePath.shows}>
              <Icon type="unordered-list" />
              <span>My Shows</span>
            </Link>
          </Menu.Item>
          <Menu.Item>
            <Link to={RoutePath.logout}>
              <Icon type="logout" />
              <span>Logout</span>
            </Link>
          </Menu.Item>
        </Menu>
      </Sider>
      <Layout>
        <Content className={`${BLOCK}__content`}>{children}</Content>
        <Footer className={`${BLOCK}__footer`}>
          ChowChow © 2019 - Created by Donatien, Domitille & Gauthier
        </Footer>
      </Layout>
    </Layout>
  );
};
