import React, {
  Component
} from 'react';
import $ from 'jquery';
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
  UseMetric
} from '../containers/Settings';
import {
  ItineraryObj as ItineraryObjContainer
} from '../containers/Itinerary';
import {
  CloseButton
} from './Utils';
import Snackbar from 'material-ui/Snackbar';

import './Itinerary.css';
import config from '../json/config.json';

export default class Itinerary extends Component {
  constructor(props) {
    super(props);
    this.state = {
      isOpen: true,
      snackBarIsOpen: false,
      snackBarMessage: 'default',
      snackBarBg: config.statusTheme.success,
      searchText: '',
      destinations: []
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
    /* global websocket */
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
        let data = JSON.parse(message.data);
        let destinations = [];
        let i = 0;
        while (i < data.length) {
          destinations.push({
            id: data[i],
            name: data[i + 1],
            lat: parseFloat(data[i + 2]),
            lng: parseFloat(data[i + 3]),
            municipality: data[i + 4] === '' ? null : data[i + 4],
            region: data[i + 5] === '' ? null : data[i + 5],
            country: data[i + 6] === '' ? null : data[i + 6],
            continent: data[i + 7] === '' ? null : data[i + 7],
            elevation: data[i + 8] === '' ? null : data[i + 8],
            airportUrl: data[i + 9] === '' ? null : data[i + 9],
            countryUrl: data[i + 10] === '' ? null : data[i + 10],
            regionUrl: data[i + 11] === '' ? null : data[i + 11],
          });
          i += 12;
        }
        this.setState({
          destinations: destinations,
          searchText: searchText
        });
      }
    }
  }
  handleUploadFile = (file) => {
    let fReader = new FileReader();
    fReader.onload = () => {
      let idObjs = $($.parseXML(fReader.result)).find('id');
      let ids = [];
      for (let i = 0; i < idObjs.length; i++) {
        ids.push(idObjs[i].innerHTML);
      }
      /* global websocket */
      websocket.send(JSON.stringify({
        endpoint: 'load',
        data: ids.toString()
      }));
      websocket.onmessage = (message) => {
        if (message.data !== 'null') {
          let data = JSON.parse(message.data);
          let destinations = [];
          let i = 0;
          while (i < data.length) {
            destinations.push({
              id: data[i],
              name: data[i + 1],
              lat: parseFloat(data[i + 2]),
              lng: parseFloat(data[i + 3]),
              municipality: data[i + 4] === '' ? null : data[i + 4],
              region: data[i + 5] === '' ? null : data[i + 5],
              country: data[i + 6] === '' ? null : data[i + 6],
              continent: data[i + 7] === '' ? null : data[i + 7],
              elevation: data[i + 8] === '' ? null : data[i + 8],
              airportUrl: data[i + 9] === '' ? null : data[i + 9],
              countryUrl: data[i + 10] === '' ? null : data[i + 10],
              regionUrl: data[i + 11] === '' ? null : data[i + 11],
            });
            i += 12;
          }
          this.props.handleImportTrip(destinations);
        }
      }
    }
    fReader.readAsText(file.target.files[0]);
  }
  handleDownloadFile = () => {
    let xml = '<?xml version="1.0" encoding="UTF-8" standalone="no"?>' +
      '\n<selection>' +
      '\n\t<destinations>';
    for (let i = 0; i < this.props.destinations.length; i++) {
      xml += '\n\t\t<id>' + this.props.destinations[i].id + '</id>'
    }
    xml += '\n\t</destinations>' +
      '\n</selection>';
    /* global saveAs */
    saveAs(new File([xml], 'trip.xml', {
      type: 'text/plain;charset=utf-8'
    }));
  }
  handleSnackbarClose = () => {
    this.setState({
      snackBarIsOpen: false
    });
  }
  render() {
    const style = {
      navBtn: {
        'margin': '10px 5px'
      },
      itinerary: {
        'width': '25%'
      },
      addDest: {
        'padding': '0 15px'
      },
      button: {
        margin: '5px 0',
      },
      fileInput: {
        cursor: 'pointer',
        position: 'absolute',
        top: 0,
        bottom: 0,
        right: 0,
        left: 0,
        width: '100%',
        opacity: 0,
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
                listStyle={{maxHeight:window.innerHeight/2,overflow:'auto'}}
                searchText={this.state.searchText}
                dataSource={this.state.destinations}
                floatingLabelText='Add a Destination'
                fullWidth={true}
                filter={AutoComplete.caseInsensitiveFilter}
                style={style.addDest}
                onNewRequest={this.handleNewRequest}
                onUpdateInput={this.handleUpdateInput}
                dataSourceConfig={{
                  'text': 'name',
                  'value': 'id'
                }}/>
              <div className='row'>
                <div className='col-md-12'>
                  <RaisedButton
                    label='Import Itinerary'
                    labelPosition='after'
                    fullWidth={true}
                    style={style.button}
                    icon={<FontIcon className='material-icons'>file_upload</FontIcon>}
                    containerElement='label'>
                    <input
                      type='file'
                      style={style.fileInput}
                      onChange={this.handleUploadFile} />
                  </RaisedButton>
                </div>
              </div>
              {this.props.destinations.length === 0 ? null : (
                <div className='row'>
                  <div className='col-md-12'>
                    <RaisedButton
                      label='Download Itinerary'
                      labelPosition='after'
                      fullWidth={true}
                      style={style.button}
                      icon={<FontIcon className='material-icons'>file_download</FontIcon>}
                      onTouchTap={this.handleDownloadFile}
                      download='trip.xml'/>
                  </div>
                </div>
              )}
              <div className='row'>
                <div className='col-md-6'>
                  <UseMetric />
                </div>
                <div className='col-md-6'>
                  <RaisedButton
                    label='Clear All'
                    labelPosition='after'
                    fullWidth={true}
                    style={style.button}
                    onTouchTap={this.props.handleClearDestinations}
                    icon={<FontIcon className='material-icons'>clear_all</FontIcon>}/>
                </div>
              </div>
              <br/>
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
    const units = this.props.useMetric ? 'Meters' : 'Feet';
    const destinationDetails = (type, data, url = null) => {
      if (data != null) {
        if (type === 'Elevation') {
          if (this.props.useMetric) {
            data = Math.round(data * 0.3048);
          }
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
      (<a target='_blank' href={this.props.destination.airportUrl}>
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
        <CardText
          expandable={true}
          style={{
            padding: '0 16px'
          }}>
          <List>
            {destinationDetails('Coordinates',this.props.destination.lat+', '+this.props.destination.lng)}
            {destinationDetails('Elevation',this.props.destination.elevation)}
            {destinationDetails('Municipality',this.props.destination.municipality)}
            {destinationDetails('Region',this.props.destination.municipality,this.props.destination.regionUrl)}
            {destinationDetails('Country',this.props.destination.country,this.props.destination.countryUrl)}
            {destinationDetails('Continent',this.props.destination.continent)}
          </List>
          <CardActions>
            <RaisedButton
              fullWidth={true}
              label='Remove Destination'
              icon={<FontIcon className='material-icons'>remove_circle_outline</FontIcon>}
              onTouchTap={this.props.handleRemoveDestination.bind(this, this.props.destination.id)}
              />
          </CardActions>
        </CardText>
      </Card>
    );
  }
}
