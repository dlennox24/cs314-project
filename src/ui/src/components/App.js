import React, { Component } from 'react';
import Layout, {Sidebar, ItineraryObj} from './Layout';
import GoogleMap from 'google-map-react';
import config from '../json/config.json';

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
  render() {
    return (
      <div>
        <h3>Itinerary
          <a type="button" className="pull-right" data-toggle="modal" data-target="#myModal">
            <i className="fa fa-sliders"></i>
          </a>
        </h3>
        <hr/>
        <div className='loc-container'>
          <ItineraryObj/>
          <ItineraryObj/>
          <ItineraryObj/>
          <ItineraryObj/>
          <ItineraryObj/>
        </div>
      </div>
    );
  }
}

class Settings extends Component{
  render() {
    return (
      <div className="modal fade" id="myModal" tabIndex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div className="modal-dialog modal-lg" role="document">
          <div className="modal-content">
            <div className="modal-header">
              <button type="button" className="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
              <h4 className="modal-title" id="myModalLabel">Settings</h4>
            </div>
            <div className="modal-body">
              ...
            </div>
            <div className="modal-footer">
              <button type="button" className="btn btn-default" data-dismiss="modal">Close</button>
              <button type="button" className="btn btn-primary">Save changes</button>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default class App extends Component{
  render() {
    return (
      <Layout>
        <div className='row'>
          <div className='col-md-3'>
            <Sidebar>
              <Itinerary />
            </Sidebar>
          </div>
          <div id='map-container' className='col-md-9'>
            <Map />
          </div>
        </div>
        <Settings />
      </Layout>
    );
  }
}
