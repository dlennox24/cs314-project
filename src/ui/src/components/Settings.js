import React, { Component } from 'react';
import Slider from 'rc-slider';
import AutoComplete from 'material-ui/AutoComplete';
import Toggle from 'material-ui/Toggle';
import Dialog from 'material-ui/Dialog';
import FontIcon from 'material-ui/FontIcon';
import IconButton from 'material-ui/IconButton';
import {Toolbar, ToolbarGroup, ToolbarSeparator, ToolbarTitle} from 'material-ui/Toolbar';

import Filters from './Filters';
import {CloseButton} from './Utils';

import config from '../json/config.json';
import testData from '../json/testData.json';

export default class Settings extends Component {
  constructor(props) {
    super(props);
    this.state = {
      isOpen: false,
      filters: {
        airportSize: config.tripSettings.defaults.filters.airportSize,
        municipality: null,
        region: testData.regions,
        country: testData.countries,
        continent: testData.continents
      }
    };
  }
  handleClose = () => this.setState({isOpen: false});
  handleOpen = () => this.setState({isOpen: true});
  handleRemoveFilter = () => {
    console.log('removed');
  };
  render() {
    const dialogTitle = (
      <div>
        <Toolbar>
          <ToolbarGroup firstChild={true}>
            <CloseButton
              onTouchTap={this.handleClose}
              tooltipPosition='bottom-right'
              />
            <ToolbarSeparator style={{'margin':'0 10px 0 0'}} />
            <ToolbarTitle text='Settings' />
          </ToolbarGroup>
          <ToolbarGroup lastChild={true}>
            <IconButton
              onTouchTap={this.handleClearFilters}
              tooltip='Restore Defaults'
              tooltipPosition='bottom-left'
              >
              <FontIcon className='material-icons'>settings_backup_restore</FontIcon>
            </IconButton>
          </ToolbarGroup>
        </Toolbar>
      </div>
    );
    return (
      <div>
        <IconButton
          onTouchTap={this.handleOpen}
          tooltip='Settings'
          >
          <FontIcon className='material-icons'>settings</FontIcon>
        </IconButton>
        <Dialog
          title={dialogTitle}
          open={this.state.isOpen}
          titleStyle={{padding:0}}
          >
          <div className='row'>
            <div className='col-md-offset-2 col-md-8'>
              <TripOptimizations />
            </div>
          </div>
          <div className='row'>
            <div className='col-md-6 col-md-offset-3'>
              <AutoComplete
                dataSource={testData.airportSizes}
                floatingLabelText='Filter by Airport Size'
                fullWidth={true}
                filter={AutoComplete.caseInsensitiveFilter}
                maxSearchResults={5}
                />
              <Filters
                name='Airport Size Filters'
                filters={this.state.filters.airportSize}
                onTouchTap={this.handleRemoveFilter}
                />
            </div>
          </div>
          <div className='row'>
            <div className='col-md-6'>
              <AutoComplete
                dataSource={testData.municipalities}
                floatingLabelText='Filter by Municipality'
                fullWidth={true}
                filter={AutoComplete.caseInsensitiveFilter}
                maxSearchResults={5}
                />
                <Filters
                  name='Airport Size Filters'
                  filters={this.state.filters.municipality}
                  onTouchTap={this.handleRemoveFilter}
                  />
            </div>
            <div className='col-md-6'>
              <AutoComplete
                dataSource={this.state.filters.region}
                floatingLabelText='Filter by Region'
                fullWidth={true}
                filter={AutoComplete.caseInsensitiveFilter}
                maxSearchResults={5}
                />
                <Filters
                  name='Airport Size Filters'
                  filters={this.state.filters.municipality}
                  onTouchTap={this.handleRemoveFilter}
                  />
            </div>
          </div>
          <div className='row'>
            <div className='col-md-6'>
              <AutoComplete
                dataSource={testData.countries}
                floatingLabelText='Filter by Country'
                fullWidth={true}
                filter={AutoComplete.caseInsensitiveFilter}
                maxSearchResults={5}
                />
                <Filters
                  name='Airport Size Filters'
                  filters={this.state.filters.country}
                  onTouchTap={this.handleRemoveFilter}
                  />
            </div>
            <div className='col-md-6'>
              <AutoComplete
                dataSource={testData.continents}
                floatingLabelText='Filter by Continent'
                fullWidth={true}
                filter={AutoComplete.caseInsensitiveFilter}
                maxSearchResults={5}
                />
                <Filters
                  name='Airport Size Filters'
                  filters={this.state.filters.continent}
                  onTouchTap={this.handleRemoveFilter}
                  />
            </div>
          </div>
        </Dialog>
      </div>
    );
  }
}

export class UseKm extends Component{
  constructor(props) {
    // always call super(props) to call React.Component constructor
    super(props);
    var loc = window.location, new_uri;
    // If page is using securte HTTP, use secure WebSocket, otherwise use plain WebSocket
    if (loc.protocol === "https:") {
      new_uri = "wss:";
    } else {
      new_uri = "ws:";
    }
    // add hostname and server file path to URI
    new_uri += "//" + config.glassfish.host + document.location.pathname;
    // set the websocket path to the endpoint of the java server, linked to @ServerEndpoint in java
    let ws = new WebSocket(new_uri+'websocket');
    // when the websocket gets a message, call the messageHandler function
    ws.onmessage = e => {this.messageHandler(e)};
    // Write an error to the error class variable
    ws.onerror = e => this.setState({error: 'WebSocket error'});
    // if the websocket does not close cleanly, set an error to the error class variable
    ws.onclose = e => !e.wasClean && this.setState({error: `WebSocket error: ${e.code} ${e.reason}`});
    // declare class state variables
    // state is anything of the React component that will update at some point
    this.state = {
      error: null,
      image: null,
      xml: null,
      useKm: false,
      socket: ws
    };
    // bind functions to this component so they can be passed to other methods
    this.handleUnits = this.handleUnits.bind(this);
  }
  messageHandler(message) {
    console.log(message);
    // websocket messages are JSON. The JSON message the server sends is in the data attribute of this message JSON
    // So, parse the JSON message containing the message data
    let jsonMessage = JSON.parse(message.data);
    console.log(jsonMessage);
  }
  handleUnits(){
    this.setState({useKm: !this.state.useKm});
    let json = {
      'status': this.state.useKm
    };
    this.state.socket.send(JSON.stringify(json));
  }
  render() {
    return (
      <Toggle
        label="Use Kilometers"
        labelPosition='left'
        style={{'textAlign':'right'}}
        onToggle={this.handleUnits.bind(this)}
        toggled={this.state.useKm}
        />
    );
  }
}

const TripOptimizations = () => {
  const optMarks = {
    0:'None',
    33:'Nearest Neighbor',
    66:'2-opt',
    99:'3-opt',
  };
  return (
    <div style={{'margin':'20px 0 50px'}}>
      <p className='text-center'>Trip Optimization</p>
      <div className='rc-slider-marks-container'>
        <Slider
          dots
          min={0}
          max={99}
          marks={optMarks}
          step={config.tripSettings.defaults.optimzation}
          defaultValue={66}
          />
      </div>
      <hr className='visible-xs-block'/>
    </div>
  );
}
