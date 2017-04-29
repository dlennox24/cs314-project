import React, {
  Component
} from 'react';
import Slider from 'rc-slider';
import Toggle from 'material-ui/Toggle';
import Dialog from 'material-ui/Dialog';
import FontIcon from 'material-ui/FontIcon';
import IconButton from 'material-ui/IconButton';
import {
  Toolbar,
  ToolbarGroup,
  ToolbarSeparator,
  ToolbarTitle
} from 'material-ui/Toolbar';

import Filters from '../containers/Filters';
import {
  CloseButton
} from './Utils';

import './Settings.css';

import config from '../json/config.json';
import testData from '../json/testData.json';

export default class Settings extends Component {
  constructor(props) {
    super(props);
    this.state = {
      isOpen: true
    };
    this.handleOptimizationChange = this.props.handleOptimizationChange.bind(this);
  }
  handleClose = () => this.setState({
    isOpen: false
  });
  handleOpen = () => this.setState({
    isOpen: true
  });
  render() {
    const optMarks = {
      0: 'None',
      1: 'Nearest Neighbor',
      2: '2-opt',
      3: '3-opt'
    };
    const sliderColor = '#D9782D';
    const dialogTitle = (
      <div>
        <Toolbar>
          <ToolbarGroup firstChild={true}>
            <CloseButton onTouchTap={this.handleClose} tooltipPosition='bottom-right'/>
            <ToolbarSeparator style={{
              'margin': '0 10px 0 0'
            }}/>
            <ToolbarTitle text='Settings'/>
          </ToolbarGroup>
          <ToolbarGroup lastChild={true}>
            <IconButton
              onTouchTap={this.handleClearFilters}
              tooltip='Restore Defaults'
              tooltipPosition='bottom-left'>
              <FontIcon className='material-icons'>settings_backup_restore</FontIcon>
            </IconButton>
          </ToolbarGroup>
        </Toolbar>
      </div>
    );
    return (
      <div>
        <IconButton onTouchTap={this.handleOpen} tooltip='Settings'>
          <FontIcon className='material-icons'>settings</FontIcon>
        </IconButton>
        <Dialog title={dialogTitle}
          open={this.state.isOpen}
          titleStyle={{
            padding: 0
          }}
          autoScrollBodyContent={true}>
          <div className='row'>
            <div className='col-md-offset-2 col-md-8'>
              <div style={{
                'margin': '20px 10px 50px'
              }}>
                <p className='text-center'>Trip Optimization</p>
                <div className='rc-slider-marks-container'>
                  <Slider
                    className='optimization-slider'
                    dots
                    min={0}
                    max={3}
                    marks={optMarks}
                    minimumTrackStyle={{
                      backgroundColor: sliderColor
                    }}
                    handleStyle={{
                      borderColor: sliderColor
                    }}
                    step={1}
                    defaultValue={this.props.optimization}
                    onAfterChange={this.handleOptimizationChange}/>
                </div>
              </div>
            </div>
          </div>
          <div className='row'>
            <div className='col-md-6 col-md-offset-3'>
              <Filters
                dataSource={testData.airportSizes}
                name='Airport Size'
                filterType='airportSize'
                filters={this.props.filters.airportSize}/>
            </div>
          </div>
          <div className='row'>
            <div className='col-md-6'>
              <Filters
                dataSource={testData.municipalities}
                name='Municipality'
                filterType='municipality'
                filters={this.props.filters.municipality}/>
            </div>
            <div className='col-md-6'>
              <Filters
                dataSource={testData.regions}
                name='Region'
                filterType='region'
                filters={this.props.filters.municipality}/>
            </div>
          </div>
          <div className='row'>
            <div className='col-md-6'>
              <Filters
                dataSource={testData.countries}
                name='Country'
                filterType='country'
                filters={this.props.filters.country}/>
            </div>
            <div className='col-md-6'>
              <Filters
                dataSource={testData.continents}
                name='Continent'
                filterType='continent'
                filters={this.props.filters.continent}/>
            </div>
          </div>
        </Dialog>
      </div>
    );
  }
}

export class UseKm extends Component {
  constructor(props) {
    // always call super(props) to call React.Component constructor
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
      this.messageHandler(e)
    };
    // Write an error to the error class variable
    ws.onerror = e => this.setState({
      error: 'WebSocket error'
    });
    // if the websocket does not close cleanly, set an error to the error class variable
    ws.onclose = e => !e.wasClean && this.setState({
      error: `WebSocket error: ${e.code} ${e.reason}`
    });
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
    // console.log(message);
    // websocket messages are JSON. The JSON message the server sends is in the data attribute of this message JSON
    // So, parse the JSON message containing the message data
    let jsonMessage = message.data;
    console.log(JSON.parse(jsonMessage));
  }
  handleUnits() {
    this.setState({
      useKm: !this.state.useKm
    });
    const test = {
      endpoint: 'sql',
      data: 'Denver'
    };

    // let json = {
    //   'string': 'test',
    //   'size': test.a2.toString(),
    //   'region': test.a1.toString()
    // };
    this.state.socket.send(JSON.stringify(test));
  }
  render() {
    return (<Toggle label="Use Kilometers" labelPosition='left' style={{
      'textAlign': 'right'
    }} onToggle={this.handleUnits.bind(this)} toggled={this.state.useKm}/>);
  }
}
