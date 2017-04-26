import React, { Component } from 'react';
import $ from 'jquery';
import AutoComplete from 'material-ui/AutoComplete'
import {List, ListItem} from 'material-ui/List';
import Avatar from 'material-ui/Avatar';
import Chip from 'material-ui/Chip';
import Subheader from 'material-ui/Subheader';
import FontIcon from 'material-ui/FontIcon';
import IconButton from 'material-ui/IconButton';
import {Toolbar, ToolbarGroup, ToolbarSeparator, ToolbarTitle} from 'material-ui/Toolbar';
import Dialog from 'material-ui/Dialog';
import Snackbar from 'material-ui/Snackbar';

import {CloseButton} from './Utils';

import config from '../json/config.json';

export default class Filters extends Component{
  constructor(props) {
    super(props);
    this.state = {
      isOpen: false,
      snackBarIsOpen: false,
      snackBarMessage: 'Filter added to '+this.props.name+' Filters',
      snackBarBg: config.statusTheme.success,
      searchText: '',
      filters: this.props.filters || []
    };
  }
  handleOpenToggle = () => this.setState({isOpen: !this.state.isOpen});
  handleClearFilters = () => {
    console.log('filters cleared');
    this.setState({
      filters: []
    });
  }
  handleNewRequest = (value) => {
    console.log(this.state);
    if(this.state.filters.includes(value)){
      this.setState({
        searchText: '',
        snackBarIsOpen: true,
        snackBarBg: config.statusTheme.warning,
        snackBarMessage: 'Filter already exists in '+this.props.name+'Filter'
      })
    }else{
      this.setState({
        searchText: '',
        snackBarIsOpen: true,
        snackBarBg: config.statusTheme.success,
        snackBarMessage: 'Filter added to '+this.props.name+' Filters',
        filters: [...this.state.filters, value]
      });
    }
    console.log(this.state);
  }
  handleUpdateInput = (searchText) => {
    console.log(searchText);
    this.setState({
      searchText: searchText
    });
  }
  handleSnackbarClose = () =>{
    this.setState({
      snackBarIsOpen: false
    });
  }
  populateFilterList = () => {
    if(this.state.filters.length === 0){
      return (
        <Subheader style={{textAlign:'center'}}>
          No Filters Applied
        </Subheader>
      );
    }
    return this.state.filters.map((text, i) => (
      <Filter key={i} primaryText={text}/>
    ))
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
            <ToolbarTitle text={this.props.name+' Filters'} />
          </ToolbarGroup>
          <ToolbarGroup lastChild={true}>
            <IconButton
              onTouchTap={this.handleClearFilters}
              tooltip='Clear Filters'
              tooltipPosition='bottom-left'
              >
              <FontIcon className='material-icons'>clear_all</FontIcon>
            </IconButton>
          </ToolbarGroup>
        </Toolbar>
      </div>
    );
    const numFilters = this.state.filters.length;
    return (
      <div>
        <AutoComplete
          searchText={this.state.searchText}
          dataSource={this.props.dataSource}
          floatingLabelText={'Filter by '+this.props.name}
          fullWidth={true}
          filter={AutoComplete.caseInsensitiveFilter}
          maxSearchResults={5}
          onNewRequest={this.handleNewRequest}
          onUpdateInput={this.handleUpdateInput}
          />
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
            {this.populateFilterList()}
          </List>
        </Dialog>
        <Snackbar
          open={this.state.snackBarIsOpen}
          message={this.state.snackBarMessage}
          autoHideDuration={config.tripSettings.snackbarAutoHide}
          onRequestClose={this.handleSnackbarClose}
          bodyStyle={{
            backgroundColor: 'rgba(0,0,0,.5)'
          }}
          style={{
            backgroundColor: this.state.snackBarBg
          }}
          />
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
          <FontIcon className='material-icons'>remove_circle_outline</FontIcon>
        }
        style={{textTransform:'capitalize'}}
        />
    );
  }
}
