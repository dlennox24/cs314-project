import React, { Component } from 'react';
import GoogleMap from 'google-map-react';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import getMuiTheme from 'material-ui/styles/getMuiTheme';
import AppBar from 'material-ui/AppBar';

import Itinerary from './Itinerary';
import CsuSvgLogo from './CsuBranding';

import config from '../json/config.json';

import '../../node_modules/bootstrap/dist/css/bootstrap.min.css';
import '../../node_modules/rc-slider/dist/rc-slider.min.css';
import './App.css';


const Map = () => {
  return (
    <GoogleMap
      apiKey={config.gMapsApiKey}
      center={config.map.defaultLocation}
      zoom={config.map.defaultZoom}>
    </GoogleMap>
  );
}

export default class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      isOpenItnerary: false,
      isOpenSettings: false,
      isOpenFilters: false
    };
  }
  render() {
    return (
      <MuiThemeProvider muiTheme={getMuiTheme(config.muiTheme)}>
        <section>
          <AppBar
            iconElementRight={<CsuSvgLogo />}
            iconElementLeft={<Itinerary />}
            />
          <div id='main-content'>
            <div id='map-container'>
              <Map />
            </div>
          </div>
        </section>
      </MuiThemeProvider>
    );
  }
}
