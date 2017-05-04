/* global websocket */
import React, {
  Component
} from 'react';
import Slider from 'rc-slider';
import '../../node_modules/rc-slider/dist/rc-slider.min.css';
import RaisedButton from 'material-ui/RaisedButton';
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
  CloseButton,
  optimizeTrip
} from './Utils';

import config from '../json/config.json';
import './Settings.css';

export default class Settings extends Component {
  constructor(props) {
    super(props);
    this.state = {
      isOpen: false,
      downloadPopoverIsOpen: false
    };
  }
  handleClose = () => this.setState({
    isOpen: false
  });
  handleOpen = () => this.setState({
    isOpen: true
  });
  handleSetOptimization = (newOptmization) => {
    this.props.handleSetOptimization(newOptmization);
    optimizeTrip(newOptmization + 1, this.props.destinations, {
      handleToggleDisableSettings: this.props.handleToggleDisableSettings,
      handleToggleIsOptimizing: this.props.handleToggleIsOptimizing,
      handleSetDestinations: this.props.handleSetDestinations,
      handleUpdateTotalDistance: this.props.handleUpdateTotalDistance
    });
  }
  render() {
    const optMarks = {
      0: 'None',
      1: '2-opt',
      2: '3-opt'
    };
    const dialogTitle = (
      <div>
        <Toolbar>
          <ToolbarGroup firstChild={true}>
            <CloseButton onTouchTap={this.handleClose} tooltipPosition='bottom-right'/>
            <ToolbarSeparator style={{
              'margin': '0 10px 0 0'
            }} />
            <ToolbarTitle text='Settings'/>
          </ToolbarGroup>
          <ToolbarGroup lastChild={true}>
            <IconButton
              tooltip='Restore Defaults'
              tooltipPosition='bottom-left'
              onTouchTap={this.props.handleRestoreDefaults}>
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
          tooltipPosition='bottom-left'
          tooltip={this.props.disabled ? 'Settings disabled during upload' : 'Settings'}
          disabled={this.props.disabled}>
          <FontIcon className='material-icons'>settings</FontIcon>
        </IconButton>
        <Dialog title={dialogTitle}
          open={this.state.isOpen}
          titleStyle={{padding: 0}}
          autoScrollBodyContent={true}>
          <div className='row'>
            <div className='col-md-6'>
              <div style={{'margin': '20px 10px 50px'}}>
                <p className='text-center'>Trip Optimization</p>
                  <div className='rc-slider-marks-container'>
                    <Slider
                      disabled={this.props.disabled}
                      className='optimization-slider'
                      dots
                      min={0}
                      max={2}
                      marks={optMarks}
                      minimumTrackStyle={{backgroundColor: config.muiTheme.palette.accent1Color}}
                      handleStyle={{borderColor: config.muiTheme.palette.accent1Color}}
                      step={1}
                      defaultValue={this.props.optimization-1}
                      onAfterChange={this.handleSetOptimization.bind(this)} />
                  </div>
              </div>
            </div>
            <div className='col-md-6'>
              <Filters
                name='Airport Size'
                filterType='airportSize'
                filters={this.props.filters.airportSize} />
            </div>
          </div>
          <div className='row'>
            <div className='col-md-6'>
              <Filters
                name='Municipality'
                filterType='municipality'
                filters={this.props.filters.municipality} />
            </div>
            <div className='col-md-6'>
              <Filters
                name='Region'
                filterType='region'
                filters={this.props.filters.municipality} />
            </div>
          </div>
          <div className='row'>
            <div className='col-md-6'>
              <Filters
                name='Country'
                filterType='country'
                filters={this.props.filters.country} />
            </div>
            <div className='col-md-6'>
              <Filters
                name='Continent'
                filterType='continent'
                filters={this.props.filters.continent} />
            </div>
          </div>
        </Dialog>
      </div>
    );
  }
}

export class useMetric extends Component {
  render() {
    return (
      <RaisedButton
        label={this.props.useMetric ? 'Metric' : 'Imperial'}
        onTouchTap={this.props.handleToggleUnits.bind(this)}
        primary={this.props.useMetric}
        fullWidth={true}
        style={{
          ...this.props.style
        }}
        icon={<FontIcon className='material-icons'>gps_fixed</FontIcon>} />
    );
  }
}
