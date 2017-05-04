/* global websocketInit */
import React, {
  Component
} from 'react';
import Dialog from 'material-ui/Dialog';
import CircularProgress from 'material-ui/CircularProgress';
import Snackbar from 'material-ui/Snackbar';

import config from '../json/config.json';

class ConnectingDialog extends Component {
  retryConnection = () => {
    websocketInit();
    this.props.handleUpdateConnectStatus(websocket.readyState);
  }
  render() {
    /* global websocket */
    let waitForServer = setInterval(() => {
      this.props.handleUpdateConnectStatus(websocket.readyState)
      if (websocket.readyState !== 0) {
        clearInterval(waitForServer);
      }
    }, 1000);
    return (
      <div>
        <Dialog
          title='Connecting to Server'
          titleStyle={{textAlign: 'center'}}
          modal={true}
          open={this.props.connected == null ? true : false} >
          <div>
            <CircularProgress
              size={80}
              thickness={4}
              style={{margin: '0 auto',display: 'block'}} />
          </div>
        </Dialog>
        <Snackbar
          open={this.props.connected == null ? false : true}
          message={this.props.connected ? 'Connected to server!' : 'Failed to connect to server!'}
          autoHideDuration={this.props.connected ? config.snackbarAutoHide : null}
          bodyStyle={{backgroundColor: 'rgba(0,0,0,.5)'}}
          action={this.props.connected ? null : 'retry'}
          onActionTouchTap={this.props.connected ? null : this.retryConnection}
          style={{backgroundColor: this.props.connected === true ?
            config.statusTheme.success : config.statusTheme.warning}} />
      </div>
    );
  }
}
export default ConnectingDialog;
