import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter as Router } from 'react-router-dom';

import { PageHome } from './components/home/PageHome/PageHome';

import './index.scss';

ReactDOM.render(<Router><PageHome /></Router>, document.getElementById('root'));
