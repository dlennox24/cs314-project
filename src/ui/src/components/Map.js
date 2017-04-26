import React, { Component } from 'react';
import FontIcon from 'material-ui/FontIcon';
import Paper from 'material-ui/Paper';
import GoogleMap from 'google-map-react';

import config from '../json/config.json';
import testData from '../json/testData.json'; //TODO: remove

export default class Map extends Component {
  componentDidMount(){
  }
  addRoutes(map){
    testData.locations.push(testData.locations[0]);
    var flightPlanCoordinates = testData.locations;
    /* global google */
    var flightPath = new google.maps.Polyline({
      path: flightPlanCoordinates,
      geodesic: config.map.theme.geodesic,
      strokeColor: config.muiTheme.palette.accent1Color,
      strokeOpacity: config.map.theme.strokeOpacity,
      strokeWeight: config.map.theme.strokeWeight
    });
    flightPath.setMap(map);
  }
  render() {
    return (
      <GoogleMap
        onGoogleApiLoaded={({map, maps}) => this.addRoutes(map)}
        yesIWantToUseGoogleMapApiInternals
        bootstrapURLKeys={{
          key: config.gMapsApiKey,
          language: 'en'
        }}
        center={[config.map.defaultLocation.lat,config.map.defaultLocation.lng]}
        zoom={config.map.defaultZoom}>
        {testData.locations.map((loc,i) => (
            <AirportMapIcon
              key={i}
              lat={loc.lat}
              lng={loc.lng}
              />
          ))}
      </GoogleMap>
    );
  }
}

class AirportMapIcon extends Component {
  render() {
    const paperSize = this.props.paperSize || config.map.icons.size;
    return (
        <Paper
          zDepth={3}
          circle={true}
          style={{
            height: paperSize,
            width: paperSize,
            margin: '-'+paperSize/2+'px 0 0 -'+paperSize/2+'px',
            textAlign: 'center',
            display: 'inline-block',
            backgroundColor: config.muiTheme.palette.primary1Color
          }}
          lat={this.props.lat}
          lng={this.props.lng}
          >
          <FontIcon
            className='material-icons'
            style={{
              padding: (paperSize-24)/2+'px',
              color: config.muiTheme.palette.accent2Color
            }}
            >
            local_airport
          </FontIcon>
        </Paper>
    );
  }
}
