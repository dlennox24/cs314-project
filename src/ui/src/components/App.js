import React, {
  Component
} from 'react';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import getMuiTheme from 'material-ui/styles/getMuiTheme';
import AppBar from 'material-ui/AppBar';

import Map from '../containers/Map';
import Itinerary from '../containers/Itinerary';
import ConnectingDialog from '../containers/ConnectingDialog';
import CsuSvgLogo from './CsuBranding';

import config from '../json/config.json';

import '../../node_modules/bootstrap/dist/css/bootstrap.min.css';
import './App.css';

export default class App extends Component {
  render() {
    return (
      <MuiThemeProvider muiTheme={getMuiTheme(config.muiTheme)}>
        <section>
          <AppBar
            iconElementRight={<CsuSvgLogo />}
            iconElementLeft={<Itinerary />} />
          <div id='main-content'>
            <div id='map-container'>
              <Map />
            </div>
          </div>
          <ConnectingDialog connected={this.props.connected} />
        </section>
      </MuiThemeProvider>
    );
  }
}
