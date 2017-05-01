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

import testData from '../json/testData.json';

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
                    onAfterChange={this.props.handleOptimizationChange.bind(this)}/>
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
  render() {
    return (
      <Toggle
        label="Use Kilometers"
        labelPosition='left'
        style={{
          'textAlign': 'right'
        }}
        onToggle={this.props.handleToggleUnits.bind(this)}
        toggled={this.props.useKm} />
    );
  }
}
