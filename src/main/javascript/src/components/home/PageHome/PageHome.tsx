import React from 'react';
import {
  Switch,
  Route, Link, withRouter,
} from 'react-router-dom';
import {
  Menu, Icon, Layout, Divider,
} from 'antd';

import { OptionType, SearchBar } from 'components/home/SearchBar/SearchBar';
import { PageShow } from 'components/show/PageShow/PageShow';
import { ShowView } from 'components/home/ShowView/ShowView';

import './page-home.scss';
import { AuthenticationState } from '../../authentication/PageLogin/PageLogin';

const { Content, Footer, Sider } = Layout;

const BLOCK = 'home_page-home';

export const PageHome = withRouter(({ location }) => {
  const [selectedShow, setSelectedShow] = React.useState<OptionType | null>(null);
  return (
    <Layout style={{ minHeight: '100vh' }}>
      <Sider collapsed>
        <Menu
          mode="inline"
          selectedKeys={[location.pathname]}
          theme="dark"
        >
          <Menu.Item key="/">
            <Link to="/">
              <Icon type="home" />
              <span>Home</span>
            </Link>
          </Menu.Item>
          <Menu.Item key="/shows">
            <Link to="/shows">
              <Icon type="unordered-list" />
              <span>My Shows</span>
            </Link>
          </Menu.Item>
        </Menu>
      </Sider>
      <Layout>
        <Content className={`${BLOCK}__content`}>
          <Switch>
            <Route exact path="/">
              <SearchBar value={selectedShow} setValue={setSelectedShow} />
              <Divider />
              {selectedShow && selectedShow.value && <ShowView show={selectedShow.value} />}
            </Route>
            <Route path="/shows/:id">
              <PageShow />
            </Route>
          </Switch>
        </Content>
        <Footer className={`${BLOCK}__footer`}>ChowChow © 2019 - Created by Donatien, Domitille & Gauthier</Footer>
      </Layout>
    </Layout>
  );
});
