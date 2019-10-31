import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter as Router } from 'react-router-dom';

import { PageHome } from './PageHome';

it('renders without crashing', () => {
  const div = document.createElement('div');
  ReactDOM.render(<Router><PageHome /></Router>, div);
  ReactDOM.unmountComponentAtNode(div);
});
