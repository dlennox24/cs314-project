import React, { Component } from 'react';
import Layout, {ItineraryObj} from './Layout';
import GoogleMap from 'google-map-react';
import config from '../json/config.json';
import Settings from './Settings';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import RaisedButton from 'material-ui/RaisedButton';

import '../../node_modules/rc-slider/dist/rc-slider.min.css';


class Map extends Component {
  render() {
    return (
      <GoogleMap
        center={config.map.defaultLocation}
        zoom={config.map.defaultZoom}>
      </GoogleMap>
    );
  }
}

class Itinerary extends Component{
  componentDidMount(){
  }
  render() {
    return (
      <div id='itinerary-container'>
        <div className='container-fluid'>
          <RaisedButton
            label={(<i className="material-icons">flight_takeoff</i>)}
            data-toggle='collapse'
            href='#loc-slide-down'
            aria-expanded='false'
            aria-controls='loc-slide-down'
            style={{'margin':'10px 5px'}}
            />
          <RaisedButton
            label={(<i className="material-icons">settings</i>)}
            href='#loc-slide-down'
            data-toggle='modal'
            data-target='#settings-modal'
            style={{'margin':'10px 5px'}}
            />
          <div className='clearfix'/>
        </div>
        <div id='loc-slide-down' className='collapse container-fluid'>
          <div className='loc-container'>
            <ItineraryObj/>
            <ItineraryObj/>
            <ItineraryObj/>
            <ItineraryObj/>
            <ItineraryObj/>
            <ItineraryObj/>
            <ItineraryObj/>
          </div>
        </div>
      </div>
    );
  }
}

export default class App extends Component{
  render() {
    return (
      <MuiThemeProvider>
        <Layout>
          <Itinerary />
          <div className='row'>
            <div id='map-container' className='col-md-12'>
              <Map />
            </div>
          </div>
          <Settings />
        </Layout>
      </MuiThemeProvider>
    );
  }
}
