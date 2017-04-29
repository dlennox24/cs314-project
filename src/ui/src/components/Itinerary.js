import React, {
  Component
} from 'react';
import RaisedButton from 'material-ui/RaisedButton';
import FlatButton from 'material-ui/FlatButton';
import FontIcon from 'material-ui/FontIcon';
import Paper from 'material-ui/Paper';
import Drawer from 'material-ui/Drawer';
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
} from './Settings';
import {
  CloseButton
} from './Utils';

import config from '../json/config.json';
import testData from '../json/testData.json'; //TODO: remove


export default class Itinerary extends Component {
  constructor(props) {
    super(props);
    this.state = {
      isOpen: true
    };
  }
  handleOpenToggle = () => this.setState({
    isOpen: !this.state.isOpen
  });
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
        <RaisedButton icon={(
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
                <ToolbarSeparator style={{
                  'marginLeft': '0'
                }}/>
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
                dataSourceConfig={{
                'text': 'name',
                'value': 'code'
              }}
              style={style.addDest}
              />
              <UseKm/>
            </div>
          </div>
          <div id='itinerary-body'>
            {testData.locations.map((destination, i) => (
              <ItineraryObj destination={destination} key={i}/>
            ))}
          </div>
        </Drawer>
      </div>
    );
  }
}


const ItineraryObj = (props) => {
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
  return (
    <Card>
    <CardHeader
      title={props.destination.name}
      subtitle='AIRPORT_CODE'
      avatar={icon}
      actAsExpander={true}
      showExpandableButton={true}
      />
    <CardText expandable={true}>
      <CardActions>
        <FlatButton label='Action1'/>
        <FlatButton label='Action2'/>
      </CardActions>
      Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec mattis pretium massa. Aliquam erat volutpat. Nulla facilisi. Donec vulputate interdum sollicitudin. Nunc lacinia auctor quam sed pellentesque. Aliquam dui mauris, mattis quis lacus id, pellentesque lobortis odio.
    </CardText>
  </Card>
  );
}
