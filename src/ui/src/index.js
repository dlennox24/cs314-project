import React from 'react';
import {
  render
} from 'react-dom';
import {
  Provider
} from 'react-redux';
import {
  createStore
} from 'redux'

import injectTapEventPlugin from 'react-tap-event-plugin';
injectTapEventPlugin();

import App from './containers/App';
import reducer from './reducers';

import config from './json/config.json';
/* global websocketInit */
websocketInit(config.websocketUrl);

const store = createStore(
  reducer,
  config.tripSettings.defaults,
  window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__()
);

render(
  <Provider store={store}>
    <App/>
  </Provider>,
  document.getElementById('root')
)
