/* global websocket */
import React, {
  Component
} from 'react';
import RaisedButton from 'material-ui/RaisedButton';
import FontIcon from 'material-ui/FontIcon';
import Paper from 'material-ui/Paper';
import Drawer from 'material-ui/Drawer';
import LinearProgress from 'material-ui/LinearProgress';
import {
  List,
  ListItem
} from 'material-ui/List';
import {
  Card,
  CardActions,
  CardHeader,
  CardText
} from 'material-ui/Card';
import {
  Toolbar,
  ToolbarGroup,
  ToolbarSeparator,
  ToolbarTitle
} from 'material-ui/Toolbar';
import AutoComplete from 'material-ui/AutoComplete';
import Snackbar from 'material-ui/Snackbar';

import Settings from '../containers/Settings';
import {
  UseMetric
} from '../containers/Settings';
import {
  ItineraryObj as ItineraryObjContainer
} from '../containers/Itinerary';
import {
  CloseButton,
  parseDestinationJson,
  optimizeTrip
} from './Utils';
import ImportExport from '../containers/ImportExport';

import './Itinerary.css';
import config from '../json/config.json';

export default class Itinerary extends Component {
  constructor(props) {
    super(props);
    this.state = {
      isOpen: true,
      snackbarIsOpen: false,
      snackbarSuccess: false,
      searchText: '',
      autoCompleteDestinations: [],
      totalDistance: null
    };
  }
  handleOpenToggle = () => this.setState({
    isOpen: !this.state.isOpen
  });
  handleNewRequest = (destination) => {
    if (this.props.destinations.filter(obj => obj.id === destination.id).length > 0) {
      this.setState({
        searchText: '',
        snackbarIsOpen: true,
        snackbarSuccess: false
      })
    } else {
      this.setState({
        searchText: '',
        snackbarIsOpen: true,
        snackbarSuccess: true
      });

      this.props.handleAddDestination(destination);
      optimizeTrip(this.props.optimization, this.props.destinations, {
        handleToggleDisableSettings: this.props.handleToggleDisableSettings,
        handleToggleIsOptimizing: this.props.handleToggleIsOptimizing,
        handleSetDestinations: this.props.handleSetDestinations,
        handleUpdateTotalDistance: this.props.handleUpdateTotalDistance
      });
    }
  }
  handleUpdateInput = (searchText) => {
    websocket.send(JSON.stringify({
      endpoint: 'destination',
      data: searchText,
      filter_airportSize: this.props.filters.airportSize.toString(),
      filter_municipality: this.props.filters.municipality.toString(),
      filter_region: this.props.filters.region.toString(),
      filter_country: this.props.filters.country.toString(),
      filter_continent: this.props.filters.continent.toString()
    }));
    websocket.onmessage = (message) => {
      if (message.data !== 'null') {
        this.setState({
          autoCompleteDestinations: parseDestinationJson(JSON.parse(message.data)),
          searchText: searchText
        });
      }
    }
  }
  handleSnackbarClose = () => {
    this.setState({
      snackbarIsOpen: false
    });
  }
  renderTotalDistances = (totalDistance, useMetric) => {
    if (totalDistance != null) {
      totalDistance = useMetric ?
        Math.round(totalDistance * 1.0693) + ' Kilometers' :
        totalDistance + ' Miles';
      console.log(useMetric);
      return (
        <ListItem
         style={totalDistance == null ? {display: 'none'} : {display: 'block'}}
         disabled={true}
         disableKeyboardFocus={true}
         secondaryText={'Total Trip Distance'}
         primaryText={totalDistance} />
      );
    }
  }
  render() {
    const style = {
      navBtn: {
        'margin': '10px 5px'
      },
      button: {
        margin: '5px 0'
      },
      itinerary: {
        'width': '25%'
      },
      addDest: {
        'padding': '0 15px'
      }
    };
    let drawerWidth = window.innerWidth > 992 ? 25 : 100;
    if (drawerWidth !== 100 && drawerWidth / 100 * window.innerWidth < 400) {
      while (drawerWidth !== 100 && drawerWidth / 100 * window.innerWidth < 400) {
        drawerWidth++;
      }
    }
    return (
      <div>
        <RaisedButton
          icon={<FontIcon className='material-icons'>flight_takeoff</FontIcon>}
          style={style.navBtn}
          secondary={true}
          onTouchTap={this.handleOpenToggle} />
        <Drawer
          width={drawerWidth + '%'}
          open={this.state.isOpen}
          className='itinerary-container'>
          <div id='itinerary-header'>
            <Toolbar>
              <ToolbarGroup>
                <ToolbarTitle text='Itinerary' />
              </ToolbarGroup>
              <ToolbarGroup lastChild={true}>
                <Settings/>
                <ToolbarSeparator style={{'marginLeft': '0'}} />
                <CloseButton
                  onTouchTap={this.handleOpenToggle}
                  tooltipPosition='bottom-left' />
              </ToolbarGroup>
            </Toolbar>
            <div style={style.addDest}>
              <AutoComplete
                disabled={this.props.disabled}
                listStyle={{maxHeight:window.innerHeight/2,overflow:'auto'}}
                searchText={this.state.searchText}
                dataSource={this.state.autoCompleteDestinations}
                floatingLabelText='Add a Destination'
                fullWidth={true}
                filter={AutoComplete.caseInsensitiveFilter}
                style={style.addDest}
                onNewRequest={this.handleNewRequest}
                onUpdateInput={this.handleUpdateInput}
                dataSourceConfig={{'text': 'name','value': 'id'}} />
              <ImportExport/>
              <div className='row'>
                <div className='col-md-6'>
                  <UseMetric style={style.button} />
                </div>
                <div className='col-md-6'>
                  <RaisedButton
                    label='Clear All'
                    labelPosition='after'
                    fullWidth={true}
                    disabled={this.props.disabled}
                    style={style.button}
                    onTouchTap={this.props.handleClearDestinations}
                    icon={<FontIcon className='material-icons'>clear_all</FontIcon>} />
                </div>
              </div>
              <List>
                 {this.renderTotalDistances(this.props.totalDistance, this.props.useMetric)}
              </List>
              <LinearProgress
                className='findme'
                color={config.muiTheme.palette.accent1Color}
                style={{
                  marginLeft: '-10%',
                  width: '120%',
                  backgroundColor: config.muiTheme.palette.accent2Color,
                  display: this.props.isOptimizing ? 'block' : 'none'
                }} />
              <br/>
            </div>
          </div>
          <div id='itinerary-body'>
            {this.props.destinations.map((destination, i) => (
              <ItineraryObjContainer
                destination={destination}
                key={i} />
            ))}
          </div>
          <Snackbar
            open={this.state.snackbarIsOpen}
            message={this.state.snackbarSuccess ?
              'Destination added!' : 'Destination already exists!'}
            onRequestClose={this.handleSnackbarClose}
            autoHideDuration={config.snackbarAutoHide}
            bodyStyle={{backgroundColor: 'rgba(0,0,0,.5)'}}
            style={{backgroundColor: this.state.snackbarSuccess ?
              config.statusTheme.success : config.statusTheme.warning}} />
        </Drawer>
      </div>
    );
  }
}

export class ItineraryObj extends Component {
  destinationDetails = (type, data, url = null) => {
    let units;
    if (data != null) {
      if (type === 'Elevation' || type === 'Distance to next destination') {
        if (type === 'Elevation') {
          units = this.props.useMetric ? 'Meters' : 'Feet';
          if (this.props.useMetric) {
            data = Math.round(data * 0.3048);
          }
        }
        if (type === 'Distance to next destination') {
          units = this.props.useMetric ? 'Kilometers' : 'Miles';
          if (this.props.useMetric) {
            data = Math.round(data * 1.6093);
          }
        }
        data += ' ' + units;
      }
      if (url != null) {
        data = (<a target='_blank' href={url}>{data}</a>);
      }
      return (
        <ListItem
          disabled={true}
          disableKeyboardFocus={true}
          secondaryText={type}
          primaryText={data} />
      )
    }
    return null;
  }
  handleRemoveDestination = (destinationId) => {
    this.props.handleRemoveDestination(destinationId);
    let newDestinations = this.props.destinations.filter(function(obj) {
      return obj.id !== destinationId;
    });
    optimizeTrip(this.props.optimization, newDestinations, {
      handleToggleDisableSettings: this.props.handleToggleDisableSettings,
      handleToggleIsOptimizing: this.props.handleToggleIsOptimizing,
      handleSetDestinations: this.props.handleSetDestinations,
      handleUpdateTotalDistance: this.props.handleUpdateTotalDistance
    });
  }
  render() {
    const icon = (
      <Paper
        zDepth={3} circle={true}
        style={{
          height: 36,
          width: 36,
          textAlign: 'center',
          display: 'inline-block',
          backgroundColor: config.muiTheme.palette.primary1Color
        }}>
        <FontIcon className='material-icons' style={{
            padding: (36 - 24) / 2 + 'px',
            color: config.muiTheme.palette.accent2Color
          }}>
          local_airport
        </FontIcon>
      </Paper>
    );
    const destinationId = this.props.destination.airportUrl != null ?
      <a target='_blank' href={this.props.destination.airportUrl}>{this.props.destination.id}</a> :
      this.props.destination.id;
    return (
      <Card className='itinerary-obj'>
        <CardHeader
          title={this.props.destination.name}
          subtitle={destinationId}
          avatar={icon}
          actAsExpander={true}
          showExpandableButton={true} />
        <CardText
          expandable={true}
          style={{padding: '0 16px'}}>
          <List>
            {this.destinationDetails('Coordinates',this.props.destination.lat+', '+this.props.destination.lng)}
            {this.destinationDetails('Elevation',this.props.destination.elevation)}
            {this.destinationDetails('Municipality',this.props.destination.municipality)}
            {this.destinationDetails('Region',this.props.destination.municipality,this.props.destination.regionUrl)}
            {this.destinationDetails('Country',this.props.destination.country,this.props.destination.countryUrl)}
            {this.destinationDetails('Continent',this.props.destination.continent)}
            {this.destinationDetails('Distance to next destination',this.props.destination.distance)}
          </List>
          <CardActions>
            <RaisedButton
              fullWidth={true}
              label='Remove Destination'
              icon={<FontIcon className='material-icons'>remove_circle_outline</FontIcon>}
              onTouchTap={this.handleRemoveDestination.bind(this, this.props.destination.id)} />
          </CardActions>
        </CardText>
      </Card>
    );
  }
}
