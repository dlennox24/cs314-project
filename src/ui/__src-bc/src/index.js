import React from 'react';
import {render} from 'react-dom';

// import '../node_modules/font-awesome/css/font-awesome.min.css';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import '../node_modules/bootstrap-material-design/dist/css/bootstrap-material-design.min.css';
import '../node_modules/bootstrap-material-design/dist/css/ripples.min.css';
import './css/main.css';

import '../node_modules/bootstrap/dist/js/bootstrap.min.js';
// import '../node_modules/bootstrap-material-design/dist/js/material.js';
// import '../node_modules/bootstrap-material-design/dist/js/ripples.min.js';

import App from './components/App';

render(
  <App/>,
  document.getElementById('root')
)
