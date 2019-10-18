import React from 'react';
import './page-home.scss';

export const PageHome: React.FC = () => (
  <div className="Home">
    <header className="Home-header">
      <a
        className="Home-link"
        href="https://reactjs.org"
        target="_blank"
        rel="noopener noreferrer"
      >
          Learn React
      </a>
    </header>
  </div>
);
