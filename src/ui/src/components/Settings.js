import React, {
  Component
} from 'react';
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
  CloseButton
} from './Utils';

import './Settings.css';

export default class Settings extends Component {
  constructor(props) {
    super(props);
    this.state = {
      isOpen: false
    };
  }
  handleClose = () => this.setState({
    isOpen: false
  });
  handleOpen = () => this.setState({
    isOpen: true
  });
  render() {
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
            <div className='col-md-6 col-md-offset-3'>
              <Filters
                name='Airport Size'
                filterType='airportSize'
                filters={this.props.filters.airportSize}/>
            </div>
          </div>
          <div className='row'>
            <div className='col-md-6'>
              <Filters
                name='Municipality'
                filterType='municipality'
                filters={this.props.filters.municipality}/>
            </div>
            <div className='col-md-6'>
              <Filters
                name='Region'
                filterType='region'
                filters={this.props.filters.municipality}/>
            </div>
          </div>
          <div className='row'>
            <div className='col-md-6'>
              <Filters
                name='Country'
                filterType='country'
                filters={this.props.filters.country}/>
            </div>
            <div className='col-md-6'>
              <Filters
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

export class useMetric extends Component {
  render() {
    return (
      <RaisedButton
        label={this.props.useMetric ? 'Metric' : 'Imperial'}
        onTouchTap={this.props.handleToggleUnits.bind(this)}
        primary={this.props.useMetric}
        fullWidth={true}
        style={{
          margin: '5px 0',
        }}
        icon={<FontIcon className='material-icons'>gps_fixed</FontIcon>}/>
    );
  }
}
