import React, { Component } from 'react';
import RaisedButton from 'material-ui/RaisedButton';
import FlatButton from 'material-ui/FlatButton';
import FontIcon from 'material-ui/FontIcon';
import Drawer from 'material-ui/Drawer';
import {Card, CardActions, CardHeader, CardText} from 'material-ui/Card';
import IconButton from 'material-ui/IconButton';
import {Toolbar, ToolbarGroup, ToolbarSeparator, ToolbarTitle} from 'material-ui/Toolbar';
import AutoComplete from 'material-ui/AutoComplete';

import Settings, {UseKm} from './Settings';

import testData from '../json/testData.json';
import './Itinerary.css';

export default class Itinerary extends Component{
  constructor(props) {
    super(props);
    this.state = {'open': true, 'openSettings': true};
  }
  handleItineraryToggle = () => this.setState({open: !this.state.open});
  handleSettingsToggle = () => this.setState({openSettings: !this.state.open});
  render() {
    const style = {
      'navBtn': {
        'margin': '10px 5px'
      },
      'itinerary': {
        'width': '25%'
      },
      'addDest':{
        'padding':'0 15px'
      }
    };
    return (
      <div>
        <RaisedButton
          icon={(<FontIcon className="material-icons">flight_takeoff</FontIcon>)}
          style={style.navBtn}
          secondary={true}
          onTouchTap={this.handleItineraryToggle}
          />
        <Drawer width={'25%'} open={this.state.open}>
          <Toolbar>
            <ToolbarGroup>
              <ToolbarTitle text="Itinerary" />
            </ToolbarGroup>
            <ToolbarGroup lastChild={true}>
              <IconButton
                onTouchTap={this.handleSettingsToggle}
                >
                <FontIcon className="material-icons">settings</FontIcon>
              </IconButton>
              <ToolbarSeparator style={{'marginLeft':'0'}} />
              <IconButton
                onTouchTap={this.handleItineraryToggle}
                >
                <FontIcon className="material-icons">close</FontIcon>
              </IconButton>
            </ToolbarGroup>
          </Toolbar>
          <div style={style.addDest}>
            <AutoComplete
              dataSource={testData.airports}
              floatingLabelText='Add a Destination'
              fullWidth={true}
              filter={AutoComplete.caseInsensitiveFilter}
              maxSearchResults={10}
              dataSourceConfig={{'text':'name','value':'code'}}
              style={style.addDest}
              />
            <UseKm />
          </div>
          <ItineraryObj />
          <ItineraryObj />
        </Drawer>
        <Settings open={this.state.openSettings}/>
      </div>
    );
  }
}

const ItineraryObj = () => (
  <Card>
    <CardHeader
      title="Without Avatar"
      subtitle="Subtitle"
      actAsExpander={true}
      showExpandableButton={true}
      />
    <CardActions>
      <FlatButton label="Action1" />
      <FlatButton label="Action2" />
    </CardActions>
    <CardText expandable={true}>
      Lorem ipsum dolor sit amet, consectetur adipiscing elit.
      Donec mattis pretium massa. Aliquam erat volutpat. Nulla facilisi.
      Donec vulputate interdum sollicitudin. Nunc lacinia auctor quam sed pellentesque.
      Aliquam dui mauris, mattis quis lacus id, pellentesque lobortis odio.
    </CardText>
  </Card>
);
