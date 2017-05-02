/* global google */
import React, {
  Component
} from 'react';
import FontIcon from 'material-ui/FontIcon';
import Paper from 'material-ui/Paper';
import GoogleMap from 'google-map-react';

import config from '../json/config.json';
var flightPath, map;

export default class Map extends Component {
  clearPolylines = (polylines) => {
    if (polylines.length > 0) {
      for (let i = polylines.length; i >= 0; i--) {
        polylines.removeAt(i);
      }
    }
  }
  setMap(mapObj) {
    map = mapObj;
    this.updateMap();
  }
  createMapOptions(maps) {
    return {
      mapTypeId: google.maps.MapTypeId.HYBRID
    }
  }
  updateMap() {
    if (map != null) {
      if (flightPath != null) {
        this.clearPolylines(flightPath.getPath());
      }

      let destinationList = this.props.destinations;
      if (destinationList.length > 2) {
        destinationList = [...this.props.destinations, this.props.destinations[0]];
      } else if (destinationList.length === 0) {
        destinationList = [];
      }

      flightPath = new google.maps.Polyline({
        path: destinationList,
        geodesic: config.map.theme.geodesic,
        strokeColor: config.muiTheme.palette.accent1Color,
        strokeOpacity: config.map.theme.strokeOpacity,
        strokeWeight: config.map.theme.strokeWeight,
        mapTypeId: google.maps.MapTypeId.SATELLITE
      });

      flightPath.setMap(map);
    }
    return this.props.destinations.map((loc, i) => (
      <AirportMapIcon
          key={i}
          lat={loc.lat}
          lng={loc.lng}
          />
    ));
  }
  render() {
    return (
      <GoogleMap
        onGoogleApiLoaded={({map, maps}) => this.setMap(map)}
        yesIWantToUseGoogleMapApiInternals
        bootstrapURLKeys={{
          key: config.gMapsApiKey,
          language: 'en'
        }}
        center={[config.map.defaultLocation.lat,config.map.defaultLocation.lng]}
        options={this.createMapOptions}
        zoom={config.map.defaultZoom}>
        {this.updateMap()}
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
            backgroundColor: config.muiTheme.palette.accent2Color
          }}
          lat={this.props.lat}
          lng={this.props.lng}
          >
          <FontIcon
            className='material-icons'
            style={{
              padding: (paperSize-24)/2+'px',
              color: config.muiTheme.palette.primary1Color
            }}
            >
            local_airport
          </FontIcon>
        </Paper>
    );
  }
}
