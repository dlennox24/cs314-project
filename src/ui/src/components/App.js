import React, {
  Component
} from 'react';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import getMuiTheme from 'material-ui/styles/getMuiTheme';
import AppBar from 'material-ui/AppBar';

import Map from '../containers/Map';
import Itinerary from '../containers/Itinerary';
import CsuSvgLogo from './CsuBranding';

import config from '../json/config.json';

import '../../node_modules/bootstrap/dist/css/bootstrap.min.css';
import '../../node_modules/rc-slider/dist/rc-slider.min.css';
import './App.css';

export default class App extends Component {
  constructor(props) {
    super(props);
    var loc = window.location,
      new_uri;
    // If page is using securte HTTP, use secure WebSocket, otherwise use plain WebSocket
    if (loc.protocol === "https:") {
      new_uri = "wss:";
    } else {
      new_uri = "ws:";
    }
    // add hostname and server file path to URI
    new_uri += "//" + config.glassfish.host + document.location.pathname;
    // set the websocket path to the endpoint of the java server, linked to @ServerEndpoint in java
    let ws = new WebSocket(new_uri + 'websocket');
    // when the websocket gets a message, call the messageHandler function
    ws.onmessage = e => {
      console.log(JSON.parse(e));
    };
    // Write an error to the error class variable
    ws.onerror = e => this.setState({
      error: 'WebSocket error'
    });
    // if the websocket does not close cleanly, set an error to the error class variable
    ws.onclose = e => !e.wasClean && this.setState({
      error: `WebSocket error: ${e.code} ${e.reason}`
    });
  }
  componentDidMount() {

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
