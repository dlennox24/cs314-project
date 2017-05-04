/* global websocket saveAs */
import React, {
  Component
} from 'react';
import $ from 'jquery';
import Popover from 'material-ui/Popover';
import Menu from 'material-ui/Menu';
import MenuItem from 'material-ui/MenuItem';
import RaisedButton from 'material-ui/RaisedButton';
import FontIcon from 'material-ui/FontIcon';
import CircularProgress from 'material-ui/CircularProgress';

import {
  parseDestinationJson,
  optimizeTrip
} from './Utils';

import config from '../json/config.json';

export default class ImportExport extends Component {
  constructor(props) {
    super(props);
    this.state = {
      downloadPopoverIsOpen: false,
      isUploading: false,
      uploadingText: 'Import Itinerary'
    }
  }
  handleUploadFile = (file) => {
    this.props.handleToggleDisableSettings();
    this.setState({
      isUploading: true,
      uploadingText: 'Uploading'
    });
    let fReader = new FileReader();
    file.persist();
    fReader.onload = () => {
      let ids = [];
      if (file.target.files[0].type === 'text/xml') {
        let idObjs = $($.parseXML(fReader.result)).find('id');
        for (let i = 0; i < idObjs.length; i++) {
          ids.push(idObjs[i].innerHTML);
        }
      } else {
        ids = JSON.parse(fReader.result).destinations;
      }
      file = null;
      websocket.send(JSON.stringify({
        endpoint: 'load',
        data: ids.toString()
      }));
      this.setState({
        uploadingText: 'Fetching Data'
      });
      websocket.onmessage = (message) => {
        if (message.data !== 'null') {
          this.props.handleAddDestinations(parseDestinationJson(JSON.parse(message.data)));
          this.setState({
            isUploading: false,
            uploadingText: 'Import Itinerary'
          });
          this.props.handleToggleDisableSettings();
          optimizeTrip(this.props.optimization, this.props.destinations, {
            handleToggleDisableSettings: this.props.handleToggleDisableSettings,
            handleToggleIsOptimizing: this.props.handleToggleIsOptimizing,
            handleSetDestinations: this.props.handleSetDestinations,
            handleUpdateTotalDistance: this.props.handleUpdateTotalDistance
          });
        } else {
          console.error('Message data is null');
        }
      }
    }
    fReader.readAsText(file.target.files[0]);
  }
  handleDownloadFile = (fileType) => {
    switch (fileType) {
      case 'json':
        let json = {
          destinations: []
        };
        for (let i = 0; i < this.props.destinations.length; i++) {
          json.destinations.push(this.props.destinations[i].id);
        }
        saveAs(new File([JSON.stringify(json)], 'trip.json', {
          type: 'text/plain;charset=utf-8'
        }));
        break;
      case 'xml':
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
        break;
      case 'kml':
        let kml = '<?xml version="1.0" encoding="UTF-8"?>' +
          '\n<kml xmlns="http://earth.google.com/kml/2.1">' +
          '\n\t<Document>\n\t\t<name>TripCo Trip</name>\n\t\t<Style id="csu">\n\t\t\t<LineStyle>' +
          '\n\t\t\t\t<color>' + config.muiTheme.palette.accent1Color + '</color>' +
          '\n\t\t\t\t<width>' + config.map.theme.strokeWeight + '</width>\n\t\t\t</LineStyle>' +
          '\n\t\t</Style>' +
          '\n\t\t<Placemark>\n\t\t\t<name>Trip</name>\n\t\t\t<styleUrl>#csu</styleUrl>' +
          '\n\t\t\t<LineString>\n\t\t\t\t<altitudeMode>clampToGround</altitudeMode>\n\t\t\t\t<coordinates>';
        for (let i = 0; i < this.props.destinations.length; i++) {
          kml += '\n' + this.props.destinations[i].lng + ',' + this.props.destinations[i].lat + ',0';
        }
        kml += '\n\t\t\t\t</coordinates>\n\t\t</LineString>\n\t</Placemark>\n\t</Document>\n</kml>';
        saveAs(new File([kml], 'trip.kml', {
          type: 'text/plain;charset=utf-8'
        }));
        break;
      default:
        console.error('Invalid download fileType: ' + fileType);
    }
  }
  handleTouchTap = (event) => {
    // This prevents ghost click.
    event.preventDefault();
    this.setState({
      downloadPopoverIsOpen: true,
      anchorEl: event.currentTarget,
    });
  };
  handleRequestClose = () => {
    this.setState({
      downloadPopoverIsOpen: false,
    });
  };
  render() {
    const style = {
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
    return (
      <div>
        <RaisedButton
          label={this.state.uploadingText}
          labelPosition='after'
          fullWidth={true}
          style={style.button}
          disabled={this.props.disabled}
          icon={this.state.isUploading ?
            <CircularProgress size={25} thickness={2.5} /> :
            <FontIcon className='material-icons'>file_upload</FontIcon>}
          containerElement='label'>
          <input
            disabled={this.props.disabled}
            type='file'
            style={style.fileInput}
            accept='.xml,.json'
            onChange={this.handleUploadFile} />
        </RaisedButton>
        {this.props.destinations.length === 0 ? null : (
          <div>
            <RaisedButton
              label='Download Itinerary'
              labelPosition='after'
              fullWidth={true}
              primary={true}
              disabled={this.props.disabled}
              style={style.button}
              icon={<FontIcon className='material-icons'>file_download</FontIcon>}
              onTouchTap={this.props.disabled ? null : this.handleTouchTap}
              download='trip.xml'/>
            <Popover
              open={this.state.downloadPopoverIsOpen}
              anchorEl={this.state.anchorEl}
              anchorOrigin={{horizontal: 'left', vertical: 'bottom'}}
              targetOrigin={{horizontal: 'left', vertical: 'top'}}
              onRequestClose={this.handleRequestClose}
              >
              <Menu>
                <MenuItem primaryText='JSON'
                  onTouchTap={this.handleDownloadFile.bind(this, 'json')} />
                <MenuItem primaryText='XML'
                  onTouchTap={this.handleDownloadFile.bind(this, 'xml')} />
                <MenuItem primaryText='KML'
                  onTouchTap={this.handleDownloadFile.bind(this, 'kml')} />
              </Menu>
            </Popover>
          </div>
        )}
      </div>
    );
  }
}
