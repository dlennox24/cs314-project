import React, { Component } from 'react';
import RaisedButton from 'material-ui/RaisedButton';
import FlatButton from 'material-ui/FlatButton';
import FontIcon from 'material-ui/FontIcon';
import Drawer from 'material-ui/Drawer';
import {Card, CardActions, CardHeader, CardText} from 'material-ui/Card';
import {Toolbar, ToolbarGroup, ToolbarSeparator, ToolbarTitle} from 'material-ui/Toolbar';
import AutoComplete from 'material-ui/AutoComplete';

import Settings, {UseKm} from './Settings';
import {CloseButton} from './Utils';

import testData from '../json/testData.json';
import './Itinerary.css';

export default class Itinerary extends Component{
  constructor(props) {
    super(props);
    this.state = {
      isOpen: false
    };
  }
  handleOpenToggle = () => this.setState({isOpen: !this.state.isOpen});
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
          icon={(<FontIcon className='material-icons'>flight_takeoff</FontIcon>)}
          style={style.navBtn}
          secondary={true}
          onTouchTap={this.handleOpenToggle}
          />
        <Drawer width={'25%'} open={this.state.isOpen}>
          <div id='itinerary-header'>
            <Toolbar>
              <ToolbarGroup>
                <ToolbarTitle text='Itinerary' />
              </ToolbarGroup>
              <ToolbarGroup lastChild={true}>
                <Settings />
                <ToolbarSeparator style={{'marginLeft':'0'}} />
                <CloseButton
                  onTouchTap={this.handleOpenToggle}
                  tooltipPosition='bottom-left'
                  />
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
          </div>
          <div id='itinerary-body'>
            <ItineraryObj />
            <ItineraryObj />
            <ItineraryObj />
            <ItineraryObj />
            <ItineraryObj />
          </div>
        </Drawer>
      </div>
    );
  }
}

const ItineraryObj = () => (
  <Card>
    <CardHeader
      title='Without Avatar'
      subtitle='Subtitle'
      actAsExpander={true}
      showExpandableButton={true}
      />
    <CardActions>
      <FlatButton label='Action1' />
      <FlatButton label='Action2' />
    </CardActions>
    <CardText expandable={true}>
      Lorem ipsum dolor sit amet, consectetur adipiscing elit.
      Donec mattis pretium massa. Aliquam erat volutpat. Nulla facilisi.
      Donec vulputate interdum sollicitudin. Nunc lacinia auctor quam sed pellentesque.
      Aliquam dui mauris, mattis quis lacus id, pellentesque lobortis odio.
    </CardText>
  </Card>
);
