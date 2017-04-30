import React, {
  Component
} from 'react';
import RaisedButton from 'material-ui/RaisedButton';
import FontIcon from 'material-ui/FontIcon';
import Paper from 'material-ui/Paper';
import Drawer from 'material-ui/Drawer';
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

import Settings from '../containers/Settings';
import {
  UseKm
} from '../containers/Settings';
import {
  ItineraryObj as ItineraryObjContainer
} from '../containers/Itinerary';
import {
  CloseButton
} from './Utils';
import Snackbar from 'material-ui/Snackbar';

import config from '../json/config.json';
import testData from '../json/testData.json'; //TODO: remove

export default class Itinerary extends Component {
  constructor(props) {
    super(props);
    this.state = {
      isOpen: true,
      snackBarIsOpen: false,
      snackBarMessage: 'default',
      snackBarBg: config.statusTheme.success,
      searchText: ''
    };
  }
  handleOpenToggle = () => this.setState({
    isOpen: !this.state.isOpen
  });
  handleNewRequest = (destination) => {
    if (this.props.destinations.filter(obj => obj.id === destination.id).length > 0) {
      this.setState({
        searchText: '',
        snackBarIsOpen: true,
        snackBarBg: config.statusTheme.warning,
        snackBarMessage: 'Destination already exists!'
      })
    } else {
      this.setState({
        searchText: '',
        snackBarIsOpen: true,
        snackBarBg: config.statusTheme.success,
        snackBarMessage: 'Destination added!'
      });
      this.props.handleAddDestination(destination);
    }
  }
  handleUpdateInput = (searchText) => {
    this.setState({
      searchText: searchText
    });
  }
  handleSnackbarClose = () => {
    this.setState({
      snackBarIsOpen: false
    });
  }
  render() {
    const style = {
      'navBtn': {
        'margin': '10px 5px'
      },
      'itinerary': {
        'width': '25%'
      },
      'addDest': {
        'padding': '0 15px'
      }
    };
    let drawerWidth = window.innerWidth > 992 ?
      25 :
      100;
    if (drawerWidth !== 100 && drawerWidth / 100 * window.innerWidth < 400) {
      while (drawerWidth !== 100 && drawerWidth / 100 * window.innerWidth < 400) {
        drawerWidth++;
      }
    }
    return (
      <div>
        <RaisedButton
          icon={(
            <FontIcon className='material-icons'>flight_takeoff</FontIcon>
          )}
          style={style.navBtn}
          secondary={true}
          onTouchTap={this.handleOpenToggle}
          />
        <Drawer
          width={drawerWidth + '%'}
          open={this.state.isOpen}
          className='itinerary-container'>
          <div id='itinerary-header'>
            <Toolbar>
              <ToolbarGroup>
                <ToolbarTitle text='Itinerary'/>
              </ToolbarGroup>
              <ToolbarGroup lastChild={true}>
                <Settings/>
                <ToolbarSeparator style={{'marginLeft': '0'}}/>
                <CloseButton
                  onTouchTap={this.handleOpenToggle}
                  tooltipPosition='bottom-left'
                  />
              </ToolbarGroup>
            </Toolbar>
            <div style={style.addDest}>
              <AutoComplete
                searchText={this.state.searchText}
                dataSource={testData.locations}
                floatingLabelText='Add a Destination'
                fullWidth={true}
                filter={AutoComplete.caseInsensitiveFilter}
                maxSearchResults={10}
                style={style.addDest}
                onNewRequest={this.handleNewRequest}
                onUpdateInput={this.handleUpdateInput}
                dataSourceConfig={{
                  'text': 'name',
                  'value': 'id'
                }}/>
                <UseKm />
              </div>
            </div>
            <div id='itinerary-body'>
              {this.props.destinations.map((destination, i) => (
                <ItineraryObjContainer
                  destination={destination}
                  key={i}/>
              ))}
            </div>
            <Snackbar
              open={this.state.snackBarIsOpen}
              message={this.state.snackBarMessage}
              autoHideDuration={config.snackbarAutoHide}
              onRequestClose={this.handleSnackbarClose}
              bodyStyle={{
                backgroundColor: 'rgba(0,0,0,.5)'
              }}
              style={{
                backgroundColor: this.state.snackBarBg
              }}
              />
          </Drawer>
        </div>
    );
  }
}


export class ItineraryObj extends Component {
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
    const units = this.props.useKm ? 'Kilometers' : 'Miles';
    const destinationDetails = (type, data, url = null) => {
      if (data != null) {
        if (type === 'Elevation') {
          data += ' ' + units;
        }
        if (url != null) {
          data = (
            <a target='_blank' href={url}>
            {data}
          </a>
          );
        }
        return (
          <ListItem
          disabled={true}
          disableKeyboardFocus={true}
          secondaryText={type}
          primaryText={data}/>
        )
      }
      return null;
    }
    const destinationId = this.props.destination.airportUrl != null ?
      (<a href={this.props.destination.airportUrl}>
        {this.props.destination.id}
    </a>) :
      this.props.destination.id;
    return (
      <Card className='itinerary-obj'>
        <CardHeader
          title={this.props.destination.name}
          subtitle={destinationId}
          avatar={icon}
          actAsExpander={true}
          showExpandableButton={true}
          />
        <CardText expandable={true}>
          <List>
            {destinationDetails('Coordinates',this.props.destination.lat+', '+this.props.destination.lng)}
            {destinationDetails('Elevation',this.props.destination.elevation)}
            {destinationDetails('Municipality',this.props.destination.municipality)}
            {destinationDetails('Region',this.props.destination.municipality,this.props.destination.regionUrl)}
            {destinationDetails('Country',this.props.destination.country,this.props.destination.countryUrl)}
            {destinationDetails('Continent',this.props.destination.country)}
          </List>
          <CardActions>
            <RaisedButton
              fullWidth={true}
              label='Remove Destination'
              icon={<FontIcon className="material-icons">remove_circle_outline</FontIcon>}
              onTouchTap={this.props.handleRemoveDestination.bind(this, this.props.destination.id)}
              />
          </CardActions>
        </CardText>
      </Card>
    );
  }
}
