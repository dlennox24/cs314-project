import React, {
  Component
} from 'react';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import getMuiTheme from 'material-ui/styles/getMuiTheme';
import AppBar from 'material-ui/AppBar';
import Dialog from 'material-ui/Dialog';
import Snackbar from 'material-ui/Snackbar';
import CircularProgress from 'material-ui/CircularProgress';

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
    this.props.handleCreateWebsocket(this.props.handleSetWebsocketError.bind(this));
  }
  render() {
    const connected = this.props.websocket.error == null ?
      null :
      !this.props.websocket.error;
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
          <Dialog
            title='Connecting to Server'
            titleStyle={{
              textAlign: 'center'
            }}
            modal={true}
            open={connected == null ? true : false}
            >
            <div>
              <CircularProgress
                size={80}
                thickness={4}
                style={{
                  margin: '0 auto',
                  display: 'block'
                }} />
            </div>
          </Dialog>
          <Snackbar
              open={connected == null ? false : true}
              message={connected ?
                'Connected to server' :
                'Failed to connect to server'
              }
              autoHideDuration={config.snackbarAutoHide}
              bodyStyle={{
                backgroundColor: 'rgba(0,0,0,.5)'
              }}
              style={{
                backgroundColor: (connected === true ?
                  config.statusTheme.success :
                  config.statusTheme.warning)
              }}
            />
        </section>
      </MuiThemeProvider>
    );
  }
}
