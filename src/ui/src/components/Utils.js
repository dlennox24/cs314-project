import React, {
  Component
} from 'react';
import FontIcon from 'material-ui/FontIcon';
import IconButton from 'material-ui/IconButton';
import Snackbar from 'material-ui/Snackbar';

import config from '../json/config.json';

export class CloseButton extends Component {
  render() {
    const tooltipPosition = this.props.tooltipPosition == null ?
      'bottom-center' : this.props.tooltipPosition;
    return (
      <div>
        <IconButton
          onTouchTap={this.props.onTouchTap}
          tooltip='Close'
          tooltipPosition={tooltipPosition}
          >
          <FontIcon className='material-icons'>close</FontIcon>
        </IconButton>
      </div>
    );
  }
}

export const parseDestinationJson = (jsonData) => {
  let destinations = [];
  let i = 0;
  while (i < jsonData.length) {
    destinations.push({
      id: jsonData[i],
      name: jsonData[i + 1],
      lat: parseFloat(jsonData[i + 2]),
      lng: parseFloat(jsonData[i + 3]),
      municipality: jsonData[i + 4] === '' ? null : jsonData[i + 4],
      region: jsonData[i + 5] === '' ? null : jsonData[i + 5],
      country: jsonData[i + 6] === '' ? null : jsonData[i + 6],
      continent: jsonData[i + 7] === '' ? null : jsonData[i + 7],
      elevation: jsonData[i + 8] === '' ? null : jsonData[i + 8],
      airportUrl: jsonData[i + 9] === '' ? null : jsonData[i + 9],
      countryUrl: jsonData[i + 10] === '' ? null : jsonData[i + 10],
      regionUrl: jsonData[i + 11] === '' ? null : jsonData[i + 11]
    });
    i += 12;
  }
  return destinations;
}

export class SnackbarNotify extends Component {
  constructor(props) {
    super(props);
    this.state = {
      message: this.props.success ?
        this.props.successText : this.props.warningText,
      background: this.props.success ?
        config.statusTheme.success : config.statusTheme.warning
    }
  }
  render() {
    return (
      <Snackbar
        open={this.props.open}
        message={this.state.message}
        autoHideDuration={config.snackbarAutoHide}
        onRequestClose={this.handleClose}
        bodyStyle={{backgroundColor: 'rgba(0,0,0,.5)'}}
        style={{backgroundColor: this.state.background}} />
    );
  }
}

export const optimizeTrip = (optimization, destinations, functions) => {
  /* global websocket */
  if (optimization > 1 && destinations.length > 3) {
    functions.handleToggleDisableSettings();
    functions.handleToggleIsOptimizing();
    websocket.send(JSON.stringify({
      endpoint: 'updateItinerary',
      optimization: optimization,
      data: JSON.stringify(destinations)
    }));
    websocket.onmessage = (message) => {
      if (message.data !== 'null') {
        functions.handleSetDestinations(JSON.parse(message.data));
        functions.handleToggleIsOptimizing();
        functions.handleToggleDisableSettings();
        functions.handleUpdateTotalDistance(JSON.parse(message.data));
      }
    }
  }
}
