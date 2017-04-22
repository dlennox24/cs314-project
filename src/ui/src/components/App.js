import React, { Component } from 'react';
import Layout, {ItineraryObj} from './Layout';
import $ from 'jquery';
import GoogleMap from 'google-map-react';
// import Select2 from 'react-select2-wrapper';
import config from '../json/config.json';
import {SearchResults, UseKm, AirportSizes, TripOptimizations} from './Settings';

import '../../node_modules/font-awesome/css/font-awesome.min.css';
import '../../node_modules/bootstrap/dist/css/bootstrap.min.css';
import '../../node_modules/rc-slider/dist/rc-slider.min.css';
import '../../node_modules/bootstrap-material-design/dist/css/bootstrap-material-design.min.css';
import '../../node_modules/bootstrap-material-design/dist/css/ripples.min.css';

const material = window.$.material;

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
          <h3 className='btn btn-default btn-raised pull-left' role='button' data-toggle='collapse' href='#loc-slide-down' aria-expanded='false' aria-controls='loc-slide-down'>
            <i className='fa fa-plane'/>
          </h3>
          <h3 className='btn btn-defaut btn-raised pull-left' role='button' data-toggle='modal' data-target='#settings-modal'>
            <i className='fa fa-sliders'/>
          </h3>
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

class Settings extends Component{
  componentDidMount(){
    material.init();
    $('#btn-adv-opts').off().on('click',function(){
      $('.btn-adv-opts-chevron').toggleClass('fa-rotate-180');
    });
  }
  render() {
    return (
      <div className='modal fade' id='settings-modal' tabIndex='-1' role='dialog' aria-labelledby='settings-modal'>
        <div className='modal-dialog modal-lg' role='document'>
          <div className='modal-content'>
            <div className='modal-header'>
              <button type='button' className='close' data-dismiss='modal' aria-label='Close'>
                <span aria-hidden='true'>&times;</span>
              </button>
              <h4 className='modal-title'><i className='fa fa-sliders'></i> Settings</h4>
            </div>
            <div className='modal-body'>
              <div className='row'>
                <div className='col-md-8 col-md-offset-2'>
                  <div className='form-group form-group-lg label-floating'>
                    <label htmlFor='main-search' className='control-label'>Search Airports</label>
                    <input id='main-search' className='form-control' type='text' />
                    <span className='help-block'></span>
                  </div>
                  <SearchResults />
                </div>
              </div>
              <h5 id='btn-adv-opts' className='text-center' role='button' data-toggle='collapse' href='#adv-opts' aria-expanded='false' aria-controls='adv-opts'>
                <i className='btn-adv-opts-chevron fa fa-chevron-down'/>
                <span> Advanced Settings </span>
                <i className='btn-adv-opts-chevron fa fa-chevron-down'/>
              </h5>
              <div id='adv-opts' className='collapse'>
                <div className='row'>
                  <div className='col-md-4'>
                    <UseKm />
                  </div>
                  <div className='col-md-4'>
                    <AirportSizes />
                  </div>
                  <div className='col-md-4'>
                    <TripOptimizations />
                  </div>
                </div>
                <div className='row'>
                  <div className='col-md-offset-2 col-md-8'>
                    <div className='form-group label-floating'>
                      <label htmlFor='continent-search' className='control-label'>Search Continents</label>
                      <input id='continent-search' className='form-control' type='text' />
                      <span className='help-block'></span>
                    </div>
                  </div>
                </div>
                <div className='row'>
                  <div className='col-md-4'>
                    <div className='form-group label-floating'>
                      <label htmlFor='municipality-search' className='control-label'>Search Municipalities</label>
                      <input id='municipality-search' className='form-control' type='text' />
                      <span className='help-block'></span>
                    </div>
                  </div>
                  <div className='col-md-4'>
                    <div className='form-group label-floating'>
                      <label htmlFor='region-search' className='control-label'>Search Regions</label>
                      <input id='region-search' className='form-control' type='text' />
                      <span className='help-block'></span>
                    </div>
                  </div>
                  <div className='col-md-4'>
                    <div className='form-group label-floating'>
                      <label htmlFor='country-search' className='control-label'>Search Countries</label>
                      <input id='country-search' className='form-control' type='text' />
                      <span className='help-block'></span>
                    </div>
                  </div>
                </div>
                <div className='row'>
                  <div className='col-md-offset-2 col-md-8'>
                    <div className='form-group'>
                      <input type='file' id='inputFile4' multiple='true'/>
                      <div className='input-group'>
                        <span className='input-group-btn input-group-sm'>
                          <button type='button' className='btn btn-fab btn-fab-mini'>
                            <i className='fa fa-upload'/>
                          </button>
                        </span>
                        <input type='text' readOnly='true' className='form-control' placeholder='Import Existing Trip' />
                      </div>
                    </div>
                  </div>
                </div>
              </div>
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
        <Itinerary />
        <div className='row'>
          <div id='map-container' className='col-md-12'>
            <Map />
          </div>
        </div>
        <Settings />
      </Layout>
    );
  }
}
