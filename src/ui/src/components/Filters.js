import React, { Component } from 'react';
import {List, ListItem} from 'material-ui/List';
import Avatar from 'material-ui/Avatar';
import Chip from 'material-ui/Chip';
import Subheader from 'material-ui/Subheader';
import FontIcon from 'material-ui/FontIcon';
import IconButton from 'material-ui/IconButton';
import {Toolbar, ToolbarGroup, ToolbarSeparator, ToolbarTitle} from 'material-ui/Toolbar';
import Dialog from 'material-ui/Dialog';

import {CloseButton} from './Utils';

export default class Filters extends Component{
  constructor(props) {
    super(props);
    this.state = {
      isOpen: false
    };
  }
  handleOpenToggle = () => this.setState({isOpen: !this.state.isOpen});
  handleClearFilters = () => {
    console.log('filters cleared');
  }
  populateFilterList = (filters) => {
    if(filters == null){
      return (
        <Subheader style={{textAlign:'center'}}>
          No Filters Applied
        </Subheader>
      );
    }
    return filters.map((text, i)=>{
      return <Filter key={i} primaryText={text}/>
    })
  }
  render() {
    const dialogTitle = (
      <div>
        <Toolbar>
          <ToolbarGroup firstChild={true}>
            <CloseButton
              onTouchTap={this.handleOpenToggle}
              tooltipPosition='bottom-right'
              />
            <ToolbarSeparator style={{'margin':'0 10px 0 0'}} />
            <ToolbarTitle text={this.props.name} />
          </ToolbarGroup>
          <ToolbarGroup lastChild={true}>
            <IconButton
              onTouchTap={this.handleClearFilters}
              tooltip='Clear Filters'
              tooltipPosition='bottom-left'
              >
              <FontIcon className='material-icons'>delete_sweep</FontIcon>
            </IconButton>
          </ToolbarGroup>
        </Toolbar>
      </div>
    );
    const numFilters = this.props.filters == null ? 0 : this.props.filters.length;
    return (
      <div>
        <Chip
          onTouchTap={this.handleOpenToggle}
          className='pull-right'
          >
          <Avatar size={32}>{numFilters}</Avatar>
          Applied Filters
        </Chip>
        <Dialog
          title={dialogTitle}
          open={this.state.isOpen}
          onRequestChange={(open) => this.setState({open})}
          titleStyle={{padding:0}}
          autoScrollBodyContent={true}
          >
          <List>
            {this.populateFilterList(this.props.filters)}
          </List>
        </Dialog>
      </div>
    );
  }
}

class Filter extends Component {
  render() {
    return (
      <ListItem
        primaryText={this.props.primaryText}
        rightIcon={
          <FontIcon className='material-icons'>delete</FontIcon>
        }
        style={{textTransform:'capitalize'}}
        />
    );
  }
}
